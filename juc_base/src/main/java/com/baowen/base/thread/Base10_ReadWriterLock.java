package com.baowen.base.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author mangguodong
 * @create 2021-04-05
 */
public class Base10_ReadWriterLock {

    public static void main(String[] args) {

        /**
         * 之前的lock，对共享资源的读和写都是上锁的，为了提高效率，
         * 读可以不上锁即共享读，写为了保证数据的一致性，必须上锁，让当前线程写完，其他线程才可以写。
         *
         * 即 读-读可共享
         *  写-读要独占
         *  写-写要独占
         *
         *  举个例子说明：
         *      CopyonWriterArraylist
         *      读的是原来的容器  即墙上的签到表所有人(线程)都可以读，而写是 复制了一份墙上的签到表自己独占，一个人写。当有新人来写时（新数据），同样独占写。
         *
         *      而对于此例中的hashmap 某个线程操作这个共享资源时，读读共享(多线程共享)，写读独占(单线程)，写写独占（单线程）
         *
         */
        MyCache cache = new MyCache();

        for (int i = 1; i <=5 ; i++) {
            int finalI1 = i;
            new Thread(()->{
                        cache.put(String.valueOf(finalI1),finalI1);
                    },String.valueOf(i)).start();
                }

        for (int i = 1; i <=5 ; i++) {
            int finalI1 = i;
            new Thread(()->{
                cache.get(String.valueOf(finalI1));
            },String.valueOf(i)).start();
        }

    }
}

class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"----写入数据");
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"----写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    public void get(String key){

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t读取数据");
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取完成"+result);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}

/**
 *
 * 未加读写锁之前  1写入时，其他线程也写入了，没能保证1线程写入的一致性，会导致问题。
 * 例:我在签名的时候同时你在我写的地方签名，会导致我的签名不正确。
 * 1----写入数据
 * 4----写入数据
 * 5----写入数据
 * 2----写入数据
 * 3----写入数据
 * 1	读取数据
 * 3	读取数据
 * 2	读取数据
 * 4	读取数据
 * 5	读取数据
 * 4----写入完成
 * 4	读取完成null
 * 5----写入完成
 * 5	读取完成null
 * 1	读取完成1
 * 1----写入完成
 * 3----写入完成
 * 2----写入完成
 * 3	读取完成null
 * 2	读取完成null
 */
