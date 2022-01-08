package com.high.concurrence.chapter01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * wait And Notify 案例
 * 一般用于生产 - 消费 模式
 * <p>
 * 生产速率大于消费速度，当队列中个数大于 5 ，producer就wait ,当 队列小于2 ，notify producer 生产
 */
public class WaitAndNotifyDemo {

    private static final Object lock = new Object();

    private static LinkedBlockingQueue<Goods> stores = new LinkedBlockingQueue<>();

    private static AtomicInteger goodsSeq = new AtomicInteger(0);

    public static void main(String[] args) {

        List<Thread> threadList = new ArrayList<>();

        //两个生产者 两个消费者
        for (int i = 1; i <= 1; i++) {
            Thread thread = new Thread(new Producer(), "producer - " + i);
            threadList.add(thread);
            thread.start();
        }

        for (int i = 1; i <= 1; i++) {
            Thread thread = new Thread(new Consumer(), "consumer - " + i);
            threadList.add(thread);
            thread.start();
        }

        //输出线程状态
//        while (true) {
//            for (Thread thread : threadList) {
//                System.out.println("线程状态 ：" + thread.getName() + " - " + thread.getState());
//            }
//            try {
//                Thread.sleep(1000l);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

    static class Goods {

        private String name;

        public Goods(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    //生产者
    static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {

                //当队列数量大于5，则wait
                if (stores.size() >= 5) {
                    try {
                        synchronized (lock) {
                            System.out.println("生产者 ： " + Thread.currentThread().getName() + " 停止生产");
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Goods goods = new Goods("goods - " + goodsSeq.incrementAndGet());
                System.out.println("生产了 : " + goods.name);
                stores.offer(goods);
                try {
                    //每生产一个，休息200ms
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //消费者
    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (stores.size() <= 2) {
                    synchronized (lock) {
                        System.out.println("消费者 ： " + Thread.currentThread().getName() + " 通知生产");
                        lock.notifyAll();
                    }

                }
                while (stores.isEmpty()) {
                    if (!stores.isEmpty()) {
                        break;
                    }
                    //如果为空，则休息400ms
                    try {
                        Thread.sleep(400L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //消费
                Goods goods = stores.poll();
                System.out.println(Thread.currentThread().getName()+" 消费了 : " + goods.name);
                //消费一次休息1S
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
