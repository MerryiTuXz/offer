import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static int c = 2;

    public static int INT_MUIT(int y, int z) {
        if (z == 0) {
            return 0;
        } else {
            return INT_MUIT(c * y, z / c) + y * (z % c);
        }
    }

    public static void main(String[] args) {
        int ans = INT_MUIT(4, 3);
        System.out.println(ans);
    }
}
