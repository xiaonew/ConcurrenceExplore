package com.high.concurrence.chapter01;

/**
 * Interrupt 和 Stop Demo
 */
public class InterruptAndStopDemo {

    public static void main(String[] args) {

        //不阻塞
        Thread interruptThread = new Thread(() -> {
            int turnIndex = 0;
            while (true) {
                if (turnIndex > 3) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getName() + " 线程结束了");
                    }

                }
                System.out.println(Thread.currentThread().getName() + "运行了 " + (turnIndex++) + " 轮 ,中断状态 " + Thread.currentThread().isInterrupted());
            }
        }, "Interrupt-Thread");
        //        interruptThread.start();
        //        interruptThread.interrupt();

        //阻塞
        Thread interruptWithBlockThread = new Thread(() -> {
            int turnIndex = 0;
            while (true) {
                if (turnIndex > 3) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getName() + " 线程结束了");
                    }
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("线程中断");
                }
                System.out.println(Thread.currentThread().getName() + "运行了 " + (turnIndex++) + " 轮 ,中断状态 " + Thread.currentThread().isInterrupted());
            }
        }, "Interrupt-Thread");
        //        interruptWithBlockThread.start();
        //        interruptWithBlockThread.interrupt();
        //        try {
        //            Thread.sleep(2000L);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //        interruptWithBlockThread.interrupt();

        Thread stopThread = new Thread(() -> {

            int turnIndex = 0;
            while (true) {

                System.out.println(Thread.currentThread().getName() + "运行了 " + (turnIndex++) + " 轮 ,中断状态 " + Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "Stop-Thread");
        stopThread.start();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopThread.stop();
    }

}
