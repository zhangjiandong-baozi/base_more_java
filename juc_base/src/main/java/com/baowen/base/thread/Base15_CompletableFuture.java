package com.baowen.base.thread;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;

/**
 * @author mangguodong
 * @create
 */
public class Base15_CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步回调

        // 需要子线程运行完后将结果返回给主线程 使用，不管报错还是正确结果都需要返回

        //没有返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("我们村我最帅"));
        completableFuture.get();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        //
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("豹纹是我，豹纹最帅");
            int i = 10/0;
            return 1024;
        },threadPoolExecutor);

        Integer ins = integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("t = " + t);
            System.out.println("u = " + u); // u表示出现了异常

        }).exceptionally(f -> {
            // exceptionally 说明程序出了异常
            System.out.println("f.getMessage() = " + f.getMessage());
            return 444;
        }).get();

        System.out.println("ins = " + ins);

    }
}
