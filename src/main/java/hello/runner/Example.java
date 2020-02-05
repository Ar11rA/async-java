package hello.runner;

import hello.mocks.Simulator;

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

}
