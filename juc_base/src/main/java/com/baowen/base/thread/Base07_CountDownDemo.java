package com.baowen.base.thread;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author mangguodong
 * @create 2021-04-04
 */
public class Base07_CountDownDemo {

    public static void main(String[] args) throws InterruptedException {

        //主线程要等 所有子线程运行完才能运行，不能主线程先运算，子线程后运算。
        //倒计时计数器  即 计算器做减法

        //需求:  7个人上自习，其中有班长，班长要最后走，关灯和关窗。

        CountDownLatch countDown = new CountDownLatch(6);

        //new CyclicBarrier()

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"离开了教室");
                countDown.countDown(); //计数减一
            },String.valueOf(i)).start();
        }

        countDown.await();//班长要等其他6个同学走完才能走，即计数器要到0，才会触发，不然一直阻塞。
        System.out.println(Thread.currentThread().getName()+"\t"+"离开了教室");

        // ErrorResult();

    }

    private static void ErrorResult() {
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"离开了教室");
                },String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName()+"\t"+"离开了教室");

        /**
         * 执行结果可能不对
         * 2	离开了教室
         * 3	离开了教室
         * 4	离开了教室
         * 1	离开了教室
         * main	离开了教室
         * 5	离开了教室
         * 6	离开了教室
         *
         * 疑问:主线程走完了,为什么还会打印子线程的值？  是因为还有守护线程？ 是因为主线程要等所有子线程结束完才退出。
         */
    }
}
