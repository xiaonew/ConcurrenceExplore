package com.high.concurrence.chapter01;

public class ThreadGroupDemo {

    public static void main(String[] args) {

        ThreadGroup threadGroup = new ThreadGroup("threadgroup-demo");

        for (int i = 1; i < 5; i++) {
            int finalI = i;
            Thread thread = new Thread(threadGroup, () -> {
                while (true) {
                    System.out.println("Thread - " + finalI);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "ThreadDemo - " + i);
            thread.start();
        }

        System.out.println("当前线程组" + threadGroup.getName() + "共有多少线程 :" + threadGroup.activeCount());
        System.out.println("线程组[threadgroup-demo] 父线程组 " + threadGroup.getParent() + " 共有多少线程 :" + threadGroup.getParent().activeCount());

        //复制线程组里线程的信息
        Thread[] threads = new Thread[threadGroup.activeCount()];
        Thread[] parentThreads = new Thread[threadGroup.getParent().activeCount()];
        threadGroup.enumerate(threads);
        threadGroup.getParent().enumerate(parentThreads);
        //打印复制的线程的信息
        for (Thread thread : threads) {
            System.out.println("线程组: " + threadGroup.getName() + "线程名称:" + thread.getName() + ", 线程状态:" + thread.getState());
        }

        //打印复制的线程的信息
        for (Thread thread : parentThreads) {
            System.out.println("线程组: " + threadGroup.getParent().getName() + "线程名称:" + thread.getName() + ", 线程状态:" + thread.getState());
        }

    }

}
