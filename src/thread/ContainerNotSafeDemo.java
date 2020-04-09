package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类不安全问题
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        // java.util.ConcurrentModificationException, 并发争抢修改导致
        // 解决1: Vector
        // 解决2: Collections.synchronizedList
        // ※解决3: CopyOnWriteArrayList,同理也有CopyOnWriteArraySet
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
