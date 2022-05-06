package com.baowen.base.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mangguodong
 * @create 2021-04-04
 */
public class Base04_AccurateAwakenByLock {

    public static void main(String[] args) {

        //线程通信之线程精准唤醒
        /**
         * 原生 notifyAll  是唤醒所有线程，比较消耗资源
         * lock对这方面进行了加强
         * 例如 电商 订单支付业务
         * A 线程下单后,然后B线程减商品库存，然后C线程调用支付接口
         * 这是要顺序，有事务的唤醒
         *
         */

        /**
         * 多线程之间按照顺序调用，实现A->B->C
         * 三个线程启动，要求如下：
         * AA打印5次，BB打印10次，CC打印15次
         * 接着
         * AA打印5次，BB打印10次，CC打印15次
         * 来10轮
         */

        PrintSource source = new PrintSource();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                source.Print5();
            }

        },"A").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                source.Print10();
            }
            },"B").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                source.Print15();
            }
            },"C").start();

    }
}

//线程操作资源类
class PrintSource{

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int flag = 1;

    public void Print5(){

        lock.lock();
        try {
            //判断
            if(flag!=1){
                condition1.await();
            }
            //干活
            for (int i = 1; i <= 5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void Print10(){

        lock.lock();
        try {
            //判断
            if(flag!=2){
                condition2.await();
            }
            //干活
            for (int i = 1; i <= 10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void Print15(){

        lock.lock();
        try {
            //判断
            if(flag!=3){
                condition3.await();
            }
            //干活
            for (int i = 1; i <= 15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}