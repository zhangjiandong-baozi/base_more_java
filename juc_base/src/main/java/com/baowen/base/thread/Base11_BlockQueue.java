package com.baowen.base.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author mangguodong
 * @create 2021-04-05
 */
public class Base11_BlockQueue {

    public static void main(String[] args) {

        //阻塞队列
        //为什么 会用到阻塞队列？
        // 多线程的情况下，某些请求申请不到资源，只能将它暂时放到阻塞队列中，等前面的请求处理完了，在来处理队列中的请求
        /**
         * 阻塞队列是集合的一种
         *
         */

        //
        BlockingQueue<Integer> integers = new ArrayBlockingQueue<>(3);
        //BlockingQueue<Integer> inte = new LinkedBlockingQueue<>(3);

        /**
         * 添加、取出、检查头部元素
         *
         * 4中类型的 添加和取出
         *
         * add的是报异常
         *
         * put的是阻塞
         *
         */

        //添加
        System.out.println(integers.offer(1));
        System.out.println(integers.offer(2));
        System.out.println(integers.offer(3));
        System.out.println(integers.offer(4));

        // 取出
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        //System.out.println(integers.poll());


    }
}
