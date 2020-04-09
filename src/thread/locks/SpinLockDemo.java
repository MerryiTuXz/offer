package thread.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现一个自旋锁
 * 通过 while+CAS
 */
public class SpinLockDemo {
    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in.");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t unlock.");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "thread-01").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnlock();
        }, "thread-02").start();
    }
}
