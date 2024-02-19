package com.solar.utils.security;

import com.alibaba.druid.util.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author hushaoge
 * @date 2024/2/19 11:10
 */
public class RSAUtil {

    private static final String PRIVATE_KEY_TEXT = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEApSA7Cs8Lr9GwSB3mcLQzmoh1EqL/DV74vbHFl/+yR0t7WEQBxl3KhcoO5vI28zTV94POqWXaq8sC+9TNG9PxSwIDAQABAkEAm56YhkNCvFxeSlkDiEwMJiNcUkgTH0LWIdCca/eqyMyxyAmn1tT2QgMMyiNLm0rN92lC/UBJBSd/PWmBnt9b4QIhAN1M+dEcQ4IetTSbRKJQ0cayoKfpsyI1Y54P/YtWS9SlAiEAvwRlKj8MFiN/wrcLemltYh94nqwv95+Z5Jgc09EImy8CID34QDx+XakiUDva/u01FIk/nB1pI/CAYtTwRV+vWqzBAiAUrq0lfd4ZJ47Vzmv3vSKS1UWCKLKo5ScMkUvNw3xjEQIgbIbIsz8EJ/W33wNAeDuNEHUHOAmfy5E2EY+lO2UsDZQ=";
    private static final String PUBLIC_KEY_TEXT= "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKUgOwrPC6/RsEgd5nC0M5qIdRKi/w1e+L2xxZf/skdLe1hEAcZdyoXKDubyNvM01feDzqll2qvLAvvUzRvT8UsCAwEAAQ==";

    public static void main(String[] args) throws Exception {
        String[] keyPairs = genKeyPair(1024);

        System.out.println("Private Key: " + keyPairs[0]);
        System.out.println("Public Key: " + keyPairs[1]);
        PublicKey publicKey = getPublicKey(PUBLIC_KEY_TEXT);
        PrivateKey privateKey = getPrivateKey(PRIVATE_KEY_TEXT);
        String cipherText = encrypt(publicKey, "你好");
        System.out.println(cipherText);
        System.out.println(""+decrypt(privateKey, cipherText));

        String cipherText2 = encrypt(privateKey, "你好");
        System.out.println(cipherText2);
        System.out.println(""+decrypt(publicKey, cipherText2));

    }

    public static  byte[][] genKeyPairBytes(int size) throws NoSuchAlgorithmException {
        byte[][] keyPairBytes = new byte[2][];

        // 选择加密算法为RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥长度为2048
        keyPairGenerator.initialize(size);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        keyPairBytes[0] = keyPair.getPrivate().getEncoded();
        keyPairBytes[1] = keyPair.getPublic().getEncoded();
        return keyPairBytes;
    }

    public static String[] genKeyPair(int keySize) throws NoSuchAlgorithmException {
        byte[][] keyPairBytes = genKeyPairBytes(keySize);
        String[] keyPairs = new String[2];

        keyPairs[0] = Base64.byteArrayToBase64(keyPairBytes[0]);
        keyPairs[1] = Base64.byteArrayToBase64(keyPairBytes[1]);

        return keyPairs;
    }

    /**
     * 获取公钥
     * @param publicKeyText
     * @return
     */
    public static PublicKey getPublicKey(String publicKeyText) {
        if (publicKeyText == null || publicKeyText.length() == 0) {
            publicKeyText = PUBLIC_KEY_TEXT;
        }

        try {
            byte[] publicKeyBytes = Base64.base64ToByteArray(publicKeyText);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(x509KeySpec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get public key", e);
        }
    }

    /**
     * 获取私钥
     * @param privateKeyText
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKeyText) {
        if (privateKeyText == null || privateKeyText.length() == 0) {
            privateKeyText = PRIVATE_KEY_TEXT;
        }

        try {
            byte[] privateKeyBytes = Base64.base64ToByteArray(privateKeyText);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get private key", e);
        }
    }

    /**
     * 解密
     * @param key
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String decrypt(Key key, String cipherText)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            // 因为 IBM JDK 不支持私钥加密, 公钥解密, 所以要反转公私钥
            // 也就是说对于解密, 可以通过公钥的参数伪造一个私钥对象欺骗 IBM JDK
            RSAPublicKey rsaPublicKey = (RSAPublicKey) key;
            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent());
            Key fakePrivateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
            cipher = Cipher.getInstance("RSA"); //It is a stateful object. so we need to get new one.
            cipher.init(Cipher.DECRYPT_MODE, fakePrivateKey);
        }

        if (cipherText == null || cipherText.length() == 0) {
            return cipherText;
        }

        byte[] cipherBytes = Base64.base64ToByteArray(cipherText);
        byte[] plainBytes = cipher.doFinal(cipherBytes);

        return new String(plainBytes);
    }

    /**
     * 解密
     * @param keyText
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String decrypt(String keyText, String cipherText)
            throws Exception {
        PublicKey privateKey = getPublicKey(keyText);
        return decrypt(privateKey, cipherText);
    }

    /**
     * 加密
     * @param key
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encrypt(Key key, String plainText)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            //For IBM JDK, 原因请看解密方法中的说明
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) key;
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPrivateExponent());
            Key fakePublicKey = KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, fakePublicKey);
        }

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedString = Base64.byteArrayToBase64(encryptedBytes);

        return encryptedString;
    }

    /**
     * 加密
     * @param privateKeyText
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encrypt(String privateKeyText, String plainText) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyText);
        return encrypt(privateKey, plainText);
    }
}
