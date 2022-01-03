package com.high.concurrence.chapter01;

public class StackAreaDemo {

    public static void main(String[] args) {

        System.out.println("当前线程名称：" +Thread.currentThread().getName());
        System.out.println("当前线程ID：" + Thread.currentThread().getId());
        System.out.println("当前线程优先级："+Thread.currentThread().getPriority());
        System.out.println("当前线程状态："+ Thread.currentThread().getState());


    }


}
