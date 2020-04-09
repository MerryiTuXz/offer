package thread.common;

public class JoinDemo {
    private static class A extends Thread{
        @Override
        public void run() {
            System.out.println("Class A");
        }
    }

    private static class B extends Thread {
        private A a;
        public B(A a) {
            this.a = a;
        }
        @Override
        public void run() {
//            try {
//                a.join();
//                System.out.println("Class B [use a.join()]");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("Class B");
        }
    }

    /**
     * B先start，但由于B调用了A的join方法，B线程会等A线程运行结束后再继续执行
     * @param args
     */
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }
}

