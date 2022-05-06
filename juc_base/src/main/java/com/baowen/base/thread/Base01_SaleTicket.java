package com.baowen.base.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mangguodong
 * @create 2021-04-03
 */
public class Base01_SaleTicket {

    public static void main(String[] args) {
        //多线程基本模板口诀1: 线程操作资源类
        //在高内聚低耦合的前提下，线程操作资源类
        //高内聚: 空调制冷和制热的功能应该在 空调这个资源类上 ; 即卖票的这个功能也应该在票这个类中有。
        //低耦合：数据可以共享，但是操作不一样。 你卖你的票，我卖我的票。 你 对空调调制冷，我对空调调制热。

        Ticket ticket = new Ticket();

        new Thread(()->{for (int i = 0; i < 40; i++) ticket.saleTicket(); },"A").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.saleTicket(); },"B").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.saleTicket(); },"C").start();

        /**
        new Thread(new Runnable() {
            @Override
            public void run() {
                ticket.saleTicket();
            }
        },"A");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ticket.saleTicket();
            }
        },"B");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ticket.saleTicket();
            }
        },"C");
         */
    }

}

class Ticket{
    private int num = 30;

    private Lock lock = new ReentrantLock();

    public  void saleTicket(){

        lock.lock();
        try {
            if(num>0){
                System.out.println(Thread.currentThread().getName()+" 卖出了第 "+num--+" 张票,还剩"+num+"张");
            }
        } finally {
            lock.unlock();
        }
    }
}


