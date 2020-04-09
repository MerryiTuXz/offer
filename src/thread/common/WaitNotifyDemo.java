package thread.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 方法中的synchronizd关键字用来说明after中的wait方法释放了锁
 */
public class WaitNotifyDemo {
    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotifyDemo demo = new WaitNotifyDemo();
        executorService.execute(() -> demo.after());
        executorService.execute(() -> demo.before());
        executorService.shutdown();
    }
}
