package com.solar.core;

/**
 * @author hushaoge
 * @date 2023/4/17 10:10
 */
public class Order {
    private String userName = "123456";
    private byte[] bytes;
    class Inner {
        private String name;
        public Inner() {
            this.name = userName;
        }
    }

    public Order (int size) {
        this.bytes = new byte[size];
    }

    Inner createInner () {
        return new Inner();
    }

}
