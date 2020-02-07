package com.itheima;

import java.util.ArrayList;

public class Demo02 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        int a = 10000;
//        int sum = 0 ;
        ArrayList<Integer> list = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
            if (i >= 10000) {
               // System.out.println(sum);
            }
//            if (sum <= 10000) {
//                break;
//            }
        }

        long end = System.currentTimeMillis();
//        System.out.println(sum);
//        System.out.println(list);

        System.out.println(end-start);
    }
}
