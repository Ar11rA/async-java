package hello.runner;

import hello.mocks.Simulator;

import java.util.Arrays;
import java.util.concurrent.*;

public class Example {

    public static void runSync() {
        try {
            int delay = Simulator.heavyTask(3);
            System.out.println(delay);
            Simulator.heavyTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runAsyncWithThreads() {
        Thread newThread1 = new Thread(() -> {
            try {
                int delay = Simulator.heavyTask(3);
                System.out.println(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread newThread2 = new Thread(() -> {
            try {
                Simulator.heavyTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread newThread3 = new Thread(() -> {
            try {
                Simulator.heavyTask(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        newThread1.start();
        newThread2.start();
        newThread3.start();
    }

    public static void runAsyncWithThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Integer> futureTask1 = threadPool.submit(() -> Simulator.heavyTask(3));
        threadPool.submit(() -> {
            try {
                Simulator.heavyTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        int result = futureTask1.get();
        System.out.println(result);
        threadPool.shutdown();
    }

    public static void runAsyncWithCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            int delay;
            try {
                delay = Simulator.heavyTask(3);
                return delay;
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return null;
        });
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Simulator.heavyTask();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return null;
        });
        int result = completableFuture1.get();
        completableFuture2.get();
        System.out.println(result);
    }

    public static void runAsyncWithCompletableChain() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            int delay1;
            try {
                delay1 = Simulator.heavyTask(3);
                return delay1;
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return null;
        }).thenCompose(delay1 -> CompletableFuture.supplyAsync(()->{
            int delay2;
            try {
                delay2 = Simulator.heavyTask(5);
                return delay1 + delay2;
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return null;
        }));
        System.out.println(completableFuture.get());
    }

    public static void runAsyncWithCompletableAllOf() {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            int delay1;
            try {
                delay1 = Simulator.heavyTask(3);
                return delay1;
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return null;
        });
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            int delay2;
            try {
                delay2 = Simulator.heavyTask(5);
                return delay2;
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return null;
        });
        CompletableFuture<Integer> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            int delay3;
            try {
                delay3 = Simulator.heavyTask(1);
                return delay3;
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return null;
        });
        CompletableFuture<Integer>[] promises = new CompletableFuture[] {completableFuture1, completableFuture2, completableFuture3};
        CompletableFuture.allOf(promises).join();
        Integer[] results =  Arrays.stream(promises).map(CompletableFuture::join).toArray(Integer[]::new);
        for(int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }
}
