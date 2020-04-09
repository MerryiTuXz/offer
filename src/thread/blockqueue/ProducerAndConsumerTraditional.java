package thread.blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PublicData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t increase number to " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t decrease number to " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class ProducerAndConsumerTraditional {
    public static void main(String[] args) {
        PublicData publicData = new PublicData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                publicData.increment();
            }
        }, "thread-01").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                publicData.decrement();
            }
        }, "thread-02").start();
    }
}
