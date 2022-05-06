package com.baowen.base.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author mangguodong
 * @create 2021-04-05
 */
public class Base08_CyclicBarrierDemo {

    public static void main(String[] args) {

        //主线程要等 所有子线程运行完才能运行。
        //循环栅栏 即 计算器做加法

        //需求：  要集齐7颗龙珠，才能召唤神龙


        CyclicBarrier cyclic = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集了第"+ finalI +"颗龙族");

                try {
                    //收集好7颗龙珠前，我只能等着，收集好以后龙哥出来，即执行自己的线程
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }


    }
}
