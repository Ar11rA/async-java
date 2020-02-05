package hello.runner;

import hello.mocks.Simulator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        newThread1.start();
        newThread2.start();
    }

    public static void runAsyncWithThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Integer> futureTask1 = threadPool.submit(() -> Simulator.heavyTask(3));
        Future<Void> futureTask2 = (Future<Void>) threadPool.submit(() -> {
            try {
                Simulator.heavyTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        int result = futureTask1.get();
        futureTask2.get();
        System.out.println(result);
        threadPool.shutdown();
    }
}
