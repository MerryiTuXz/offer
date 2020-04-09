package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RobotReflect {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class rc = Class.forName("reflection.Robot");
        Robot robot = (Robot) rc.newInstance();
        System.out.println("Class name is: " + rc.getName());
        Method getHello = rc.getDeclaredMethod("throwHello", String.class);
        getHello.setAccessible(true);
        Object str = getHello.invoke(robot, "Bob");
        System.out.println("getHello result is: " + str);
        Method sayHi = rc.getDeclaredMethod("sayHi", String.class);
        sayHi.invoke(robot, "Welcome");
        Field name = rc.getDeclaredField("name");
        name.setAccessible(true);
        name.set(robot, "Merryituxz");
        sayHi.invoke(robot, "Welcome");
    }
}
