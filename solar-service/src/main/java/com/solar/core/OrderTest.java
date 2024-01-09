package com.solar.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hushaoge
 * @date 2023/4/17 10:09
 */
public class OrderTest {

    public static void main(String[] args) {
        List<Order.Inner> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {

            Order.Inner inner = new Order(100000).createInner();
            list.add(inner);
            System.out.println(i);
        }

    }
}
