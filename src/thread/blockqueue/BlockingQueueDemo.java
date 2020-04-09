package thread.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 阻塞队列空时,试图从队列中获取元素的线程会被阻塞,直达其他线程向空队列中插入新的元素
 * 阻塞队列满时,试图从队列中添加元素的线程会被阻塞,直到其他线程从队列中移除元素
 * 好处: 无需关心什么时候需要阻塞线程,什么时候需要唤醒线程
 * <p>
 * ArrayBlockingQueue(重点): 有界,数组
 * LinkedBlockingQueue(重点,几乎不用): 有界(但大小默认为Integer.MAX_VALUE), 链表
 * PriorityBlockingQueue: 优先队列, 无界
 * DelayQueue: 优先队列实现, 无界, 延迟阻塞队列
 * SynchronousQueue(重点): 不存储元素的阻塞队列, 即单个元素的队列
 * LinkedTransferQueue: 链表 无界阻塞队列
 * LinkedBlockingQueue: 链表 双向阻塞队列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
//        throwExceptionMethod();
//        specialValueMethod();
//        blockingMethod();
        timeLimitBlockingMethod();
    }

    /*
    超时限制的阻塞队列
        插入: boolean offer(E e, long timeout, TimeUnit unit)
        移除: E poll(long timeout, TimeUnit unit)
        队首: 无
     */
    private static void timeLimitBlockingMethod() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("x", 2L, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }

    /*
    阻塞队列:
        插入: void put(e)
        移除: E take()
        队首: 无
     */
    private static void blockingMethod() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("x"); 此时再put会阻塞main线程

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
//        blockingQueue.take(); 此时再take会阻塞main线程
    }

    /*
    返回特殊值:
        插入: offer(e)
        移除: poll()
        队首: peek()
     */
    private static void specialValueMethod() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a")); // true
        System.out.println(blockingQueue.offer("b")); // true
        System.out.println(blockingQueue.offer("c")); // true
        System.out.println(blockingQueue.offer("x")); // false

        System.out.println(blockingQueue.peek()); // a

        System.out.println(blockingQueue.poll()); // a
        System.out.println(blockingQueue.poll()); // b
        System.out.println(blockingQueue.poll()); // c
        System.out.println(blockingQueue.poll()); // null
    }

    /*
    抛出异常:
        插入: add(e)
        移除: remove()
        队首: element()
     */
    private static void throwExceptionMethod() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a")); // true
        System.out.println(blockingQueue.add("b")); // true
        System.out.println(blockingQueue.add("c")); // true
//        System.out.println(blockingQueue.add("x")); java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.element()); // a

        System.out.println(blockingQueue.remove()); // a
        System.out.println(blockingQueue.remove()); // b
        System.out.println(blockingQueue.remove()); // c
//        System.out.println(blockingQueue.remove()); java.util.NoSuchElementException
    }
}
