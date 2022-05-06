package com.baowen.base.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author mangguodong
 * @create 2021-04-05
 */
public class Base14_ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //分支合并框架 forkjoin
        //递归分治计算
        /**
         * 一般来说，递归是在一个主线程中，对子任务反复进行递归计算。
         *
         * 我们可以采用这种思想 将子任务给子线程去做
         *
         * 需求:计算0-100的和
         *
         */

        MyTask myTask = new MyTask(0, 100);

        //创建线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //提交任务
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        //获取返回值
        System.out.println(forkJoinTask.get());

        //关闭线程池
        forkJoinPool.shutdown();

    }
}

class MyTask extends RecursiveTask<Integer>{

    private static final int MINCOUNT = 10;

    private int begin;
    private int end;

    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(end-begin<=MINCOUNT){

            for (int i = begin; i <=end ; i++) {
                result = result+i;
            }
        }else{
            int mid = (begin+end)/2;
            MyTask task1 = new MyTask(begin, mid);
            MyTask task2 = new MyTask(mid+1, end);

            //fork 的意思是和递归一样会重新调用compute方法
            //递归问题可以分成2个问题，小问题解决，子问题递归。
            task1.fork();
            task2.fork();

            result = task1.join()+task2.join();
        }

        return result;
    }
}
