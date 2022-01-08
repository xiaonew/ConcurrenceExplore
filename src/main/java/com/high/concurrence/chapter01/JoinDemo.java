package com.high.concurrence.chapter01;

public class JoinDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {

            int index = 0;
            while (true) {
                if (index++ > 4) {
                    break;
                }
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行了 " + index + " 轮");
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行结束");
    }

}
