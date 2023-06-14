package com.fastcampus.clickstream;

import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.UUID;
import java.util.Random;


public class Main
{
    static int userNum = 15; //사용자 수 15명
    static int durationSeconds = 300; // 5분 주기
    static Set<String> ipSet = new HashSet<>();
    static Random rand = new Random();

    public static void main( String[] args ) {
        CountDownLatch latch = new CountDownLatch(userNum);
        ExecutorService executor = Executors.newFixedThreadPool(userNum);
        IntStream.range(0, userNum).forEach(i -> {
            String ipAddr = getIpAddr();
            executor.execute(new LogGenerator(latch, ipAddr, UUID.randomUUID().toString(), durationSeconds));
        });
        executor.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    // 192.168.x로 시작하는 ip 주소를 생성
    private static String getIpAddr() {
        while (true) {
            String ipAddr = "192.168.0." + rand.nextInt(255);
            if (!ipSet.contains(ipAddr)) {
                ipSet.add(ipAddr);
                return ipAddr;
            }
        }
    }
}
