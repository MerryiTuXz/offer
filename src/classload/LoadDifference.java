package classload;

/*
隐式加载: new
显示加载: loadClass, forName等
    Class.forName得到的class是已经初始化完成的[比如会执行static方法]
    Classloader.loadClass得到的class是还没有链接的
 */

/**
 * 类装载过程
 * 加载: 通过ClassLoader加载class字节码文件,生成Class对象
 * 链接:
 *     校验: 检查加载的class的正确性和安全性
 *     准备: 为类变量分配存储空间并设置类变量初始值
 *     解析: JVM将常量池内的符号引用转换为直接引用
 * 初始化: 执行类变量赋值和静态代码块
 */
public class LoadDifference {
    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader classLoader = Robot.class.getClassLoader();
//        Class c = Class.forName("reflection.Robot");
    }
}
