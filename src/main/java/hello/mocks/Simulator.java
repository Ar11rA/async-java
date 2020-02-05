package hello.mocks;

public class Simulator {
    public static int heavyTask(int secs) throws InterruptedException {
        Thread.sleep(secs * 1000);
        return secs;
    }
    public static void heavyTask() throws InterruptedException {
        Thread.sleep(10 * 1000);
    }
}
