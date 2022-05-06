package com.solar.utils;

import java.util.Random;

/**
 * @author hushaoge
 * @date 2022/5/6
 */
public class RandomHsgUtil {
    public static void main(String[] args) {
        // 8   21  19   8 1 15 7 5
//        String a = "hushaoge";
//        for (int i=0; i < 8; i++) {
//
//            System.out.println(a.charAt(i) -  '`');
//        }
        long seed = 0L;
        while (true) {
            seed ++;
            Random r = new Random(seed);
            int k = r.nextInt(27);
            if(k != 8){
                continue;
            }
            k = r.nextInt(27);
            if(k != 21){
                continue;
            }
            k = r.nextInt(27);
            if(k != 19){
                continue;
            }


            k = r.nextInt(27);
            if(k != 7){
                continue;
            }
            k = r.nextInt(27);
            System.out.println(k);
            System.out.println("=====种子==="+ seed+ "k="+k);

            break;

        }
        System.out.println(randomString(985716));
        System.out.println(randomString2(372716));

    }

    public static String randomString(int i) {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int k = ran.nextInt(27);
            if (k == 0)
                break;

            sb.append((char) ('`' + k));
        }
        return sb.toString();
    }

    public static String randomString2(int i) {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int k = ran.nextInt(27);
            if (k == 11)
                break;

            sb.append((char) ('`' + k));
        }
        return sb.toString();
    }
}
