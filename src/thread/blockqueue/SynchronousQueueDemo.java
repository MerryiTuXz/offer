package thread.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue没有容量
 * SynchronousQueue不存储元素
 * 每一个put操作必须要等待一个take操作,反之亦然
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "\t put b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "\t put c");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-01").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    TimeUnit.SECONDS.sleep(2L);
                    System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-02").start();
    }
}
