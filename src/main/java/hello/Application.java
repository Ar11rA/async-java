package hello;

import com.mashape.unirest.http.exceptions.UnirestException;

import hello.runner.QuoteAsync;
import hello.runner.SimulatorAsync;

import java.util.concurrent.ExecutionException;

public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException, UnirestException {


        // Comment out methods you don't want to run
        SimulatorAsync.runSync(); // 2 tasks run A(3 secs) and B(10 secs), total time taken is 13: 3 + 10 secs

        // 2 or more tasks run - e.g. A(3 secs) and B(10 secs), total time taken is 10 secs
        SimulatorAsync.runAsyncWithThreads(); // using basic threads
        SimulatorAsync.runAsyncWithThreadPool(); // using thread pool
        SimulatorAsync.runAsyncWithCompletableFuture(); // using competeAble future
        SimulatorAsync.runAsyncWithCompletableChain(); // using competeAble chains
        SimulatorAsync.runAsyncWithCompletableAllOf(); // using competeAble allOf

        // Get Quotes async function
        System.out.println(QuoteAsync.getQuotes(5));
    }
}
