package com.baowen.base.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mangguodong
 * @create 2021-04-04
 */
public class Base03_ProducerConsumerByLock {

    public static void main(String[] args) {

        //线程通信
        //生产者消费者模式口诀2: 判断、干活、通知
        //生产者消费者模式口诀3：多线程交互，为了防止虚假唤醒，判断要用while，不能用if

        //需求: 两个线程，操作一个初始值为0的变量，一个线程加1，一个线程减1，来10轮，最后初始值为0.

        //lock 新写法

        AirConditionerByLock airConditioner = new AirConditionerByLock();

        new Thread(()->{
            try {
                for (int i = 1; i <=10 ; i++) {
                    airConditioner.increase();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {

                for (int i = 1; i <=10 ; i++) {
                    airConditioner.decrease();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread(()->{
            try {
                for (int i = 1; i <=10 ; i++) {
                    airConditioner.increase();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();

        new Thread(()->{
            try {

                for (int i = 1; i <=10 ; i++) {
                    airConditioner.decrease();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();

    }
}

//操作资源类
//空调加一度减一度的方法要高类聚在资源类上,并暴露给外部
class AirConditionerByLock{

    private int num = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    //线程同步问题，要加锁
    //使用lock替代synchronized的
    public  void increase() throws InterruptedException {
        //判断
        //对于生产者，我生产了很多，就应该等待
        //例： 蛋糕店里有一个蛋糕的时候，就不要生产了，怕卖不出去，等着别人先买
        //生产者判断条件不知道怎么选时这样想==> 有蛋糕的时候我就先等等，没有蛋糕的时候就干活生产蛋糕

        //多线程交互,判断不能用if，要用while
        //api说明:为了防止虚假唤醒和打断，需要用while在判断一次条件
        // AC 线程是生产 ，BD线程是消费，当用if判断的时候，
        // 如果AB都在等待，此时唤醒，如果 B 抢到cpu资源（锁）进行加1操作，如果A抢到资源(锁)也会进行一次加1操作，会出现2的情况，不符合预期，
        // 应该是B执行完代码之后，如果A抢到cpu资源，在判断一下条件，是否继续加1
        //if(num !=0){

        lock.lock();
        try {

            while(num !=0){
               condition.await();// this.wait();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName()+" 的值为 "+num+"");
            //通知
            condition.signalAll();//this.notifyAll();
        } finally {
            lock.unlock();
        }

//        while(num !=0){
//            this.wait();
//        }
//        //干活
//        num++;
//        System.out.println(Thread.currentThread().getName()+" 的值为 "+num+"");
//        //通知
//        this.notifyAll();
    }

    //对于消费者,不能消费的时候应该等待即初始值为0时等待
    public  void decrease() throws InterruptedException {

        //判断
        //对于消费者,不能消费的时候应该等待 即初始值为0时等待
        //例： 有人来买蛋糕，店子里没有蛋糕买时，我应该等待厨师生产
        //消费者判断条件不知道怎么选择的时候 这样想==> 如果没有蛋糕(0)我就等待，有蛋糕我就干活吃蛋糕。

        //多线程交互，判断不能用if，而要用while在判断一下条件
        //if(num == 0){

        lock.lock();
        try {
            while (num == 0){
                condition.await();//this.wait();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName()+" 的值为 "+num+"");
            //通知
            condition.signalAll();//this.notifyAll();
        } finally {
            lock.unlock();
        }


    }

}
