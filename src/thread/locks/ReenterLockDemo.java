package thread.locks;

/**
 * 可重入锁==递归锁
 *
 * 指的是同意线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 *
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSMS();
        }, "thread-01").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "thread-02").start();
    }
}

class Phone {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t invoke sendSMS().");
        // 同一个线程访问两个synchronized方法, 持有的是同一把锁
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t invoke sendEmail().");
    }
}
