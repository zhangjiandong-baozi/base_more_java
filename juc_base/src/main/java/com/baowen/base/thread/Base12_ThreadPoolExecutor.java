package com.baowen.base.thread;

import java.util.concurrent.*;

/**
 * @author mangguodong
 * @create 2021-04-05
 */
public class Base12_ThreadPoolExecutor {

    public static void main(String[] args) {

        /**
         * 线程创建的第4种方式 线程池
         * 可用Executors 创建的3类线程池方式，分别是固定的线程数，单个线程数，可扩容的线程数(默认可以扩容至21亿,来几个请求，我就创建几个线程处理)
         * 但是一般是自定义线程池 7大参数
         *
         * 线程池的好处: 少new 且复用，性能进一步提高，控制最大并发数，同时管理线程。
         *
         *
         */

        //ExecutorService threadPool = Executors.newFixedThreadPool(5); //5个银行窗口
       //ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newCachedThreadPool();

        // 一般使用自定义的线程池，自己控制线程数
        /**
         * public ThreadPoolExecutor(int corePoolSize,  核心线程数
         *                               int maximumPoolSize, 最大线程数
         *                               long keepAliveTime,  空闲多久时间,多余核心线程的的线程注销
         *                               TimeUnit unit,     空闲时间的单位
         *                               BlockingQueue<Runnable> workQueue,  核心线程满负荷工作，在过来的线程会放到阻塞队列中 或 线程池所有线程都工作时，在过来的线程放在阻塞队列中
         *                               ThreadFactory threadFactory,  创建线程的工厂，默认即可
         *                               RejectedExecutionHandler handler)  当所有线程和阻塞队列中的线程都满了时，会触发拒绝处理请求线程的操作，这里有几种拒绝策略
         */
        /**
         * 线程的工作原理:
         *  例：核心线程数2个,最大线程数5个,阻塞队列容量为3
         *
         *  当有2个请求过来时,由核心线程a、b工作，
         *  再来3个或3个以内的请求，会暂时放在阻塞队列中cde
         *  当阻塞队列满了,又来3个请求fgh，此时线程池会创建线程直到最大线程数，处理之前放在阻塞队列中的请求cde,将最新的请求放在阻塞队列中。
         * 如果还有请求,线程池会启用拒绝策略，来处理再过来的请求。
         *
         * 即线程池可默认处理 5(最大线程)+3(阻塞线程)  个线程
         *
         * 拒绝策略有几种：
         *  终止策略 AbortPolicy  ,大于默认处理线程数且max个线程都在工作，在过来的请求就会中止程序并抛异常
         *  回退策略 CallerRunsPolicy  ， 大于默认处理的线程且max个线程都在工作，在过来的请求就交给调用者自己处理，即 如果是main发过来的请求，有main方法自己处理
         *  丢弃策略 DiscardPolicy    ， 大于默认处理的线程且max个线程都在工作，再过来的请求会丢弃
         *  丢弃策略 DiscardPolicy    ， 大于默认处理的线程且max个线程都在工作，再过来的请求会丢弃
         *  DiscardOldestPolicy       大于默认处理的线程且max个线程都在工作, 会丢弃等待最久的任务，将任务加到队列中。
         */

        //如何设置最大线程数？
        // 如果是cpu密集型 则是cpu核数加1
        // 如果是io密集型？ 一般是核心数的2倍   即cpu的核数 除以阻塞系数
        System.out.println("最大线程数"+(Runtime.getRuntime().availableProcessors()+1));

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );


        //10个人办理业务
        try {
            for (int i = 1; i <=9 ; i++) {

                threadPool.submit(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理了业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
