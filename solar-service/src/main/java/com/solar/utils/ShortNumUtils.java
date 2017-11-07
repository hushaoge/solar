package com.solar.utils;

/**
 * 长数字转换成短字符串
 * @author hushaoge
 * @date 2016/11/30
 */
public class ShortNumUtils {

    public static final String ALPHABET = "7UiI0jCeZsPF5wyH432LboS9BaEpKtqNudgDAQflnxJ6XGvzhR81OrYWcMTkVm";

    public static final int BASE = ALPHABET.length();

    /**
     * 编码
     * @param num
     * @return
     */
    public static String encode(Long num) {
        StringBuilder str = new StringBuilder();
        do {
            str.insert(0, ALPHABET.charAt((int) (num % BASE)));
            num = num / BASE;
        } while (num > 0);
        return str.toString();
    }

    /**
     * 解码
     * @param str
     * @return
     */
    public static Long decode(String str) {
        Long num = 0L;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(ShortNumUtils.encode(888888888L));
        System.out.println(ShortNumUtils.decode("IGyc"));
        /*String x = "1234567890qwertyuiopasdfghjklzxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP";
        List<String> list = new ArrayList<>();
        for (char c : x.toCharArray()) {
            list.add(String.valueOf(c));
        }
        Collections.shuffle(list);
        for (String s:list ) {
            System.out.print(s);
        }
        System.out.println();*/
    }


}
