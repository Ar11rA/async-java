package hello.mocks;

public class Simulator {
    public static int heavyTask(int secs) throws InterruptedException {
        Thread.sleep(secs * 1000);
        System.out.println("Function called which takes " + secs + " seconds");
        return secs;
    }
    public static void heavyTask() throws InterruptedException {
        Thread.sleep(10 * 1000);
        System.out.println("Function called which takes 10 seconds");
    }
}
