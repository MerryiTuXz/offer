package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Volatile {
    public static void main(String[] args) {
        visibility();
//        atomicity();
    }

    /**
     * 原子性验证
     */
    private static void atomicity() {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        // '2'的意义: main线程+GC线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // volatile不保证原子性
        System.out.println(Thread.currentThread().getName() + "\t int type finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type finally number value: " + myData.atomicInteger);
    }

    /**
     * 可见性验证
     */
    public static void visibility() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number value: " + myData.number);
        }, "AAA").start();

        // main线程
        while (myData.number == 0) {
            // main线程一直循环,直到number不为0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission over, main get number value = " + myData.number);
    }
}

class MyData {
    // 无可见性,number值改变后不能及时通知main线程
//    int number = 0;

    // volatile可见性,使得number值一旦被更改能及时通知main线程,使main线程中while循环得以退出
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}