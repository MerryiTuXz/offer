package thread.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用来控制一个或多个线程等待多个线程
 * 让主线程等待一组事件发生后继续执行
 * 构造器: public CountDownLatch(int count)
 * countDown(): 让计数器减1
 * 计数器减为0时，因调用await()方法而在等待的线程会被唤醒
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.println("run");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end");
        executorService.shutdown();
    }
}
