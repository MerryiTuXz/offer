package thread.aqs;

import java.util.concurrent.Semaphore;

public class SemaphoneDemo {
    private volatile int count = 0;
    private Semaphore producer, consumer, mutex;

    public SemaphoneDemo() {
        producer = new Semaphore(10);
        consumer = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    class Producer extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    producer.acquire();
                    mutex.acquire();
                    System.out.printf("%s produce product[%d]\n", Thread.currentThread().getName(), count++);
                    mutex.release();
                    consumer.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    consumer.acquire();
                    mutex.acquire();
                    System.out.printf("%s consume product[%d]\n", Thread.currentThread().getName(), --count);
                    mutex.release();
                    producer.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SemaphoneDemo demo = new SemaphoneDemo();
        for (int i = 0; i < 3; i++) {
            demo.new Producer().start();
            demo.new Consumer().start();
        }
    }
}
