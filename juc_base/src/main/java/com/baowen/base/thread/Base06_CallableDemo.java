package com.baowen.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author mangguodong
 * @create 2021-04-04
 */
public class Base06_CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程的第三种方式 实现Callable接口
        // 使用多线程一部分目的是为了，某些复杂的运算交给子线程去算，主线程同时算余下运算，减少阻塞，2边同时算快速得到结果。

        /**
         * 注意get方法放在最后一行
         *
         * get方法的作用是获取子线程call方法的返回值
         * 如果get方法不放在最后一行，而是启动子线程后立马get，那么主线程会一直等待子线程算完结果后返回后才会执行下面的代码，即阻塞了，没有达到减少时间，提高效率的目的。
         *
         */

        /**
         * 例如 主线程有执行3个方法  A方法3s，B方法30s，C方法27s
         * B和AC没有关系  如果是主线程执行A，然后子线程执行B，主线程执行c， 那么用时是 3+27s
         *
         * 如果全是主线程执行 则是 3+30+27  是60s，都阻塞在主线程了,耗时久
         *
         */

        //思想是多态  即面向接口编程  子接口的实现类 既有Runable，又有callable的功能

        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallThread());

        //futureTask 运行完的结果会缓存在对象中,第二个线程调用，会将上次线程算好的结果直接返回。
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        System.out.println("futureTask.get() = " + futureTask.get());
    }
}

class MyCallThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("come in call");
        //暂停线程
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}
