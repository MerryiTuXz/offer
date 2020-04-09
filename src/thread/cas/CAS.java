package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/*
CAS: Compare And Swap
CPU并发原语,判断内存某个位置的值是否为预期值,如果是则更改为新的值,这个过程是原子的
体现为sun.misc.Unsafe类中的各个方法,Unsafe类可以直接操作特定内存的数据
调用Unsafe类中的CAS方法,JVM会帮我们实现出CAS汇编指令,这是一种完全依赖于硬件的功能,通过它实现原子操作
CAS是一条CPU的原子指令,不会造成所谓的数据不一致问题
 */
public class CAS {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(6);

//        System.out.println(atomicInteger.compareAndSet(5, 2019));
        System.out.println(atomicInteger.compareAndSet(6, 2019));

        System.out.println("current data: " + atomicInteger.get());
    }
}
