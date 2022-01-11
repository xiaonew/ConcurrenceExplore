package com.high.concurrence.chapter01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * 实验 val 不用volatile修饰，数据安不安全
 * 数据是安全的耗时 ，也没有增加没好
 */
public class VolatileAndCasDemo {

    private static Unsafe UNSAFE = null;

    private static long valOffset = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    static {

        try {
            Class klass = Unsafe.class;
            Field field = klass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
            valOffset = UNSAFE.objectFieldOffset(IncrementVal.class.getDeclaredField("val"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        IncrementVal incrementVal = new IncrementVal(0l);

        long before = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            new Thread(incrementVal).start();
        }
        countDownLatch.await();
        long after = System.currentTimeMillis();

        System.out.println("耗时: " + (after - before) + ", val: " + incrementVal.getVal());

    }

    static class IncrementVal implements Runnable {

        private  volatile long val;

        public IncrementVal(long val) {
            this.val = val;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {

                //自旋
                for (; ; ) {
                    if (UNSAFE.compareAndSwapLong(this, valOffset, getVal(), getVal() + 1)) {
                        break;
                    }
                }

            }
            countDownLatch.countDown();
        }

        public long getVal() {
            return val;
        }
    }

}
