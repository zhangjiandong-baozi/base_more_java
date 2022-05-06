package com.baowen.base.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author mangguodong
 * @create 2021-04-05
 */
public class Base09_SemaphoreDemo {

    public static  void main(String[] args) {

        /**
         * 之前的练习都是多个线程抢一个资源，实际上cpu资源是多核的。即现实场景是多个线程抢多个资源。
         *
         *可以由信号灯Semaphore来控制，共享资源的互斥和线程的并发数的控制。
         *
         * 例如 6部车抢3个车位
         * synchronized 即是此时 1个车位的特殊情况
         *
         */

        Semaphore semaphore = new Semaphore(3); //目前就只有3个资源

        for (int i = 1; i <=6 ; i++) {
                    int finalI = i;
                    new Thread(()->{
                        try {
                            //表明该线程获取到了资源
                            semaphore.acquire();
                            System.out.println(Thread.currentThread().getName()+"\t获取到了车位");
                            //模拟线程运行了3s
                            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                            System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            //该线程运行完后,释放资源
                            semaphore.release();
                        }

                    },String.valueOf(i)).start();
                }

    }
}
