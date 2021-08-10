package com.solar.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hushaoge
 * @date 2021/7/15
 */
public class GenericTest {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple("1"));
        apples.add(new Apple("2"));
        List<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit());
        eatFruit(apples);
        eatFruit(fruits);
    }

    public static void eatFruit(List<? extends Fruit> fruits) {
        fruits.stream().forEach(Fruit::eat);
    }

}
