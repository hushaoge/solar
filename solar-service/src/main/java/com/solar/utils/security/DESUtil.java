package com.solar.utils.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES对称加密/解密
 * @author hushaoge
 * @date 2023/5/16 10:51
 */
public class DESUtil {
    /**
     * 默认密钥
     */
    public static final String DEF_KEY = "HUSOLAR";

    /**
     * 设置key
     * @param strKey
     * @return
     */
    public static Key setKey(String strKey) {
        Key key = null;
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            // 根据参数生成key
            generator.init(new SecureRandom(strKey.getBytes()));
            key = generator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * 加密
     * @param source  编码内容
     * @param key     密钥
     * @param charSet 编码格式
     * @return
     */
    public static String encrypt(String source, String key, String charSet) {
        String encrypt = null;
        try {
            byte[] ret = encrypt(source.getBytes(charSet), key);
            encrypt = new BASE64Encoder().encode(ret);
        } catch (Exception e) {
            e.printStackTrace();
            encrypt = null;
        }
        return encrypt;
    }

    /**
     * 解密
     * @param encryptedData  解码内容
     * @param key            密钥
     * @param charSet        编码格式
     * @return
     */
    public static String decrypt(String encryptedData, String key, String charSet) {
        String decryptedData = null;
        try {
            byte[] ret = decrypt(new BASE64Decoder().decodeBuffer(encryptedData), key);
            decryptedData = new String(ret, charSet);
        } catch (Exception e) {
            e.printStackTrace();
            decryptedData = null;
        }
        return decryptedData;
    }

    /**
     * 加密
     * @param primaryData  解码内容
     * @param key          密钥
     * @return
     */
    private static byte[] encrypt(byte[] primaryData, String key) {
        Key desKey = setKey(key);
        try {
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密钥初始化Cipher对象(加密)
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            return cipher.doFinal(primaryData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     * @param encryptedData  编码内容
     * @param key            密钥
     * @return
     */
    private static byte[] decrypt(byte[] encryptedData, String key) {
        Key desKey = setKey(key);
        try {
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密钥初始化Cipher对象(解密)
            cipher.init(Cipher.DECRYPT_MODE, desKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String code = "Ewell123";
        String unicode = "utf-8";
        String encrypt = DESUtil.encrypt(code, DEF_KEY, unicode);
        String decrypt = DESUtil.decrypt(encrypt, DEF_KEY, unicode);
        System.out.println("原内容：" + code);
        System.out.println("加密：" + encrypt);
        System.out.println("解密：" + decrypt);
    }
}
