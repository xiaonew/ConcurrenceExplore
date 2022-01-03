package com.high.concurrence.chapter01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: huan.liu
 * @since : 2022-01-03
 */
public class SalesDemo {

    public static final int MAX_AMOUNT = 5;

    //继承Thread类
    static class StoreGoods extends Thread {

        private int goodAmount = MAX_AMOUNT;

        public StoreGoods(String name) {
            super(name);
        }

        @Override
        public void run() {

            for (int i = 1; i <= MAX_AMOUNT; i++) {
                System.out.println(Thread.currentThread().getName() + " 卖出一件，还剩：" + --goodAmount);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");

        }
    }

    //实现Runnable接口
    static class MallGoods implements Runnable {

        private AtomicInteger goodsAmount = new AtomicInteger(MAX_AMOUNT);

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + " 卖出一件，还剩：" + goodsAmount.decrementAndGet());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //StoreGoods Thread
        for (int i = 1; i <= 2; i++) {
            new StoreGoods("商店 店员 - " + i).start();
        }

         Thread.sleep(10000L);
        System.out.println("商场=========================");

        //MallGoods Runnbale
        MallGoods mallGoodsTarget = new MallGoods();
        for (int i = 1; i <= 5; i++) {
            new Thread(mallGoodsTarget, "商场 店员 - " + i).start();
        }

    }

}
