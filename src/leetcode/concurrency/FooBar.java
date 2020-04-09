package leetcode.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
    private int n;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                condition.await();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            condition.await();
            lock.lock();
            try {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
