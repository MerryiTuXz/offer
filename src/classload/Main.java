package classload;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader("C:\\Users\\Administrator\\IdeaProjects\\offer\\", "myClassLoader");
        Class c = myClassLoader.loadClass("TestClassLoader");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        System.out.println(c.getClassLoader().getParent().getParent().getParent());
        c.newInstance();
    }
}
