package com.baowen.base.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @author mangguodong
 * @create
 */
public class Base02_JMM {

    public static void main(String[] args) {

        // JMM java内存模型
        /**
         * java 内存模型要满足 可见性(通知机制)，原子性，有序性
         *
         * 即 2个线程 对共享资源要都是可见的，他们 由主内存来通知修改自己的资源
         *
         * int age = 15
         *
         * age 是放在主内存()  A，B2个线程拷贝一份到自己的工作内存，再自己的工作修改age的值后，回刷到主存，主存在通知其他线程同步
         * 例 A 将age 改成18  ，18 会回刷到主存，  主存会通知B来同步自己。
         *
         */

        MyNumber myNumber = new MyNumber();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"come in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            myNumber.add218();

            System.out.println("myNumber.age = " + myNumber.age);
        },"AAA").start();

        while (myNumber.age == 26){
           // try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Thread.currentThread().getName()+"main update");
    }
}

class MyNumber{

    volatile int age = 26;

    public void add218(){
        age = 18;
    }
}

