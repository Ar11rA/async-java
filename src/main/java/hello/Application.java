package hello;

import hello.runner.Example;

public class Application {

    public static void main(String[] args) {
        // Comment out methods you don't want to run
        Example.runSync(); // 2 tasks run A(3 secs) and B(10 secs), total time taken is 13: 3 + 10 secs
        Example.runAsyncWithThreads(); // 2 tasks run A(3 secs) and B(10 secs), total time taken is 10 secs
    }
}
