package thread.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition类
 * 使用await()和signalAll()代替wait()和notifyAll()
 * 底层: AQS
 */
public class ConditionDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before() {
        lock.lock();
        try {
            System.out.println("before");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void after() {
        lock.lock();
        try {
            condition.await();
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConditionDemo demo = new ConditionDemo();
        executorService.execute(() -> demo.after());
        executorService.execute(() -> demo.before());
        executorService.shutdown();
    }
}
