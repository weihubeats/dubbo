package org.apache.dubbo.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : wh
 * @date : 2021/5/2 9:57 下午
 * @description:
 */
public class CompletableFutureTest {


    public static void main(String[] args) throws InterruptedException {
        System.setProperty(
                "java.util.concurrent.ForkJoinPool.common.parallelism", "5");
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Integer> list = IntStream.range(0, 20).boxed().collect(Collectors.toList());
        list.parallelStream().forEach(s -> {
            // 业务处理
            System.out.println("thread:" + Thread.currentThread().getName() + "value" + s);
            countDownLatch.countDown();

        });
        countDownLatch.await();
        int poolSize = ForkJoinPool.commonPool().getPoolSize();
        System.out.println("Pool size: " + poolSize);



       /* CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("t1: 洗水壶中....");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t1: 烧开水。。。。");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("t2: 洗水壶。。。");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t2: 洗茶杯。。。。");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("t2: 拿茶叶。。。。");
                TimeUnit.SECONDS.sleep(1);
                return "普洱";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        // 任务3：在任务一和任务2完成后执行，泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("t1: 拿到茶叶：" + tf);
            System.out.println("t1: 泡茶...");
            return "上茶: " + tf;
        });
        // 等待任务执行完成
        System.out.println(f3.join());
*/
    }
}
