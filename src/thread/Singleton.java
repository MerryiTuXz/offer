package thread;

public class Singleton {
    /*
    ourInstance = new Singleton();  可能的指令顺序
        1.分配内存空间
        2.初始化对象
        3.设置ourInstance指向刚分配的内存地址,此时ourInstance != null
    由于指令重排序的原因,当一条线程访问ourInstance不为null时
    ourInstance实例未必已经初始化完成,也就造成了线程安全问题
    所以加volatile消除指令重排序
     */
    private static volatile Singleton ourInstance;

    // DCL: Double Check Lock 双端锁
    public static Singleton getInstance() {
        if (ourInstance == null) {
            synchronized (Singleton.class) {
                if (ourInstance == null) {
                    ourInstance = new Singleton();
                }
            }
        }
        return ourInstance;
    }

    private Singleton() {

    }
}
