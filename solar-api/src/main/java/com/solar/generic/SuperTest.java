package com.solar.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hushaoge
 * @date 2021/7/15
 */
public class SuperTest {
    public static void main(String[] args) {
        List<? super Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple("1"));
        fruits.add(new Apple("2"));
        fruits.add(new Fruit());
    }
}
