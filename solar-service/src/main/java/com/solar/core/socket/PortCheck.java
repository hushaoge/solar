package com.solar.core.socket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hushaoge
 * @date 2023/4/18 9:33
 */
public class PortCheck {
    public static void main(String[] args) {
        String ip = "proxy-78ko.onrender.com";

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final AtomicInteger count = new AtomicInteger(1);
        for (int i = 1000; i < 10000;i++) {
            final int port = i;
            executorService.submit(()->{
                try {
                    Socket client = new Socket();
                    client.connect(new InetSocketAddress(ip, port), 300);
                    client.close();
                    System.out.println("开放端口："+port);
                } catch(Exception e){
                    // do nothing
                }
                if (count.getAndIncrement() % 100 == 0) {
                    System.out.println("==================计数："+port+"================");
                }

            });


        }

        executorService.shutdown();


    }
}
