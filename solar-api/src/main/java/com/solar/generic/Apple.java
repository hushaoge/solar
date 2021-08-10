package com.solar.generic;

/**
 * @author hushaoge
 * @date 2021/7/15
 */
public class Apple extends Fruit {
    private String name;
    Apple(String name) {
        this.name = name;
    }
    @Override
    void eat() {
        System.out.println(this.name);
    }
}
