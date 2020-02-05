package hello;

import hello.runner.Example;

import java.util.concurrent.ExecutionException;

public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//      Comment out methods you don't want to run
        Example.runSync(); // 2 tasks run A(3 secs) and B(10 secs), total time taken is 13: 3 + 10 secs

        // 2 tasks run - A(3 secs) and B(10 secs), total time taken is 10 secs
        Example.runAsyncWithThreads(); // using basic threads
        Example.runAsyncWithThreadPool(); // using thread pool
        Example.runAsyncWithCompletableFuture(); // using competeAble future
        Example.runAsyncWithCompletableChain(); // using competeAble chains
    }
}
