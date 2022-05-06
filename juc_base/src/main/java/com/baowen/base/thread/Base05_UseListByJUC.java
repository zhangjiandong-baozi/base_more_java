package com.baowen.base.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author mangguodong
 * @create 2021-04-04
 */
public class Base05_UseListByJUC {


    public static void main(String[] args) {

        //多线程问题 Arraylist的add方法是线程不安全的 多线程下会出现 并发修改异常
        /**
         * 解决办法：
         *   1 Vector
         *   2 Collections.synchronizedList
         *   3 使用并发包下的集合类
         *      原理:CopyOnWriteArrayList  读写分离  写是复制一个容器，将新元素加进去后，重新设置。而读是原来那个容器。
         *      源码解释在最底下
         */

        MapSafe();

        //SetSafe();

        //ListSafe();
    }

    private static void MapSafe() {
        Map map = new ConcurrentHashMap();

        for (int i = 1; i <=31 ; i++) {
            int finalI = i;
            new Thread(()->{
                //写
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                //读
                System.out.println(map);
            },i+": ").start();
        }
    }

    private static void SetSafe() {
        Set set = new CopyOnWriteArraySet();//Collections.synchronizedSet(new HashSet<>());//new HashSet();

        for (int i = 1; i <=31 ; i++) {
            new Thread(()->{
                //写
                set.add(UUID.randomUUID().toString().substring(0,8));
                //读
                System.out.println(set);
            },i+": ").start();
        }
    }

    private static void ListSafe() {

        //多线程问题 Arraylist的add方法是线程不安全的 多线程下会出现 并发修改异常
        /**
         * 解决办法：
         *   1 Vector
         *   2 Collections.synchronizedList
         *   3 CopyOnWriteArrayList  读写分离  写是复制一个容器，将新元素加进去后，重新设置。而读是原来那个容器。
         */
        List list = new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());//new Vector();//new ArrayList();

        for (int i = 1; i <=31 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
                },i+": ").start();
        }
    }
}


/**
 *
 * public boolean add(E e) {
 *         final ReentrantLock lock = this.lock;
 *         lock.lock();
 *         try {
 *             Object[] elements = getArray(); //获取原来的数组
 *             int len = elements.length;
 *             Object[] newElements = Arrays.copyOf(elements, len + 1); // 重新copy一个 原来容量加1的数组
 *             newElements[len] = e;  //添加新元素
 *             setArray(newElements); // 将新元素添加后 ，将新数组替换成老数组
 *             return true;
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 *
 */