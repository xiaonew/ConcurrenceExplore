package com.high.concurrence.chapter01;

/**
 * 线程状态
 */
public class StateDemo {

    public static void main(String[] args) throws InterruptedException {

        StatePrinter statePrinter = new StatePrinter();
        //线程NEW 状态
        Thread thread1 = new Thread(statePrinter, "thread-1");
        Thread thread2 = new Thread(statePrinter, "thread-2");

        System.out.println("创建线程(NEW)状态 ：" + thread1.getName() + " -" + thread1.getState());
        System.out.println("创建线程(NEW)状态 ：" + thread2.getName() + " -" + thread2.getState());

        // ================================start & waiting_timeout & blocking状态===================================

        thread1.start();
        Thread.sleep(1000L);
        thread2.start();
        Thread.sleep(1000L);
        System.out.println("创建线程(NEW)状态 ：" + thread1.getName() + " -" + thread1.getState());
        System.out.println("创建线程(进入synchronized)状态 ：" + thread2.getName() + " -" + thread2.getState());

        Thread.sleep(10000L);
        System.out.println("创建线程运行结束状态 ：" + thread1.getName() + " -" + thread1.getState());
        System.out.println("创建线程运行结束状态 ：" + thread2.getName() + " -" + thread2.getState());

        thread1.join();
        thread2.join();
        System.out.println("创建线程运行结束状态 ：" + thread1.getName() + " -" + thread1.getState());
        System.out.println("创建线程运行结束状态 ：" + thread2.getName() + " -" + thread2.getState());






    }

    static class StatePrinter implements Runnable {

        @Override
        public void run() {
            System.out.println("Start线程后状态 ：" + Thread.currentThread().getName() + " -" + Thread.currentThread().getState());
            synchronized (this) {
                try {
                    //sleep  10S
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
