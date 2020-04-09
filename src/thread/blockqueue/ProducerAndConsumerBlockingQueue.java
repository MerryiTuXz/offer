package thread.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProduct() {
        String data = null;
        boolean res;
        try {
            while (flag) {
                data = atomicInteger.incrementAndGet() + "";
                res = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (res) {
                    System.out.println(Thread.currentThread().getName() + "\t insert " + data + " into queue successfully." +
                            "   [flag=true]");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t insert " + data + " into queue failed." +
                            "   [flag = true]");
                }
                TimeUnit.SECONDS.sleep(1L);
            }
            System.out.println(Thread.currentThread().getName() + "\t stop produce.    [flag = false]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void myConsumer() {
        String data = null;
        try {
            while (flag) {
                data = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (data == null || data.equals("")) {
                    flag = false;
                    System.out.println(Thread.currentThread().getName() + "\t stop consume  [flag = false]");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t get " + data + " from queue successfully.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        flag = false;
    }
}


public class ProducerAndConsumerBlockingQueue {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println("start " + Thread.currentThread().getName() + "...");
            myResource.myProduct();
        }, "thread-producer").start();

        new Thread(() -> {
            System.out.println("start " + Thread.currentThread().getName() + "...");
            myResource.myConsumer();
        }, "thread-consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5L);
            myResource.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
