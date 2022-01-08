package com.high.concurrence.chapter01;

public class DaemonDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread daemonThread = new Thread(new DaemonExe(), "daemonthread");
        daemonThread.setDaemon(true);
        Thread userThread = new Thread(new UserExe(), "userthread");
        daemonThread.start();
        userThread.start();

        Thread.sleep(3000L);
        userThread.interrupt();
        Thread.sleep(1000L);
        System.out.println("userThread 状态 : " + userThread.getState());
        System.out.println("daemonThread 状态 : " + daemonThread.getState());

    }

    //守护线程
    static class DaemonExe implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("守护线程 ：" + Thread.currentThread().getName() + " 正在运行...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //用户线程
    static class UserExe implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("用户线程 ：" + Thread.currentThread().getName() + " 正在运行...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
