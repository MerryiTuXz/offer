package classload;

import java.io.*;

public class MyClassLoader extends ClassLoader {
    private String path;
    private String classLoadName;

    public MyClassLoader(String path, String classLoadName) {
        this.path = path;
        this.classLoadName = classLoadName;
    }

    // 寻找class文件
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        System.out.println("name: " + name);
        return defineClass(name, b, 0, b.length);
    }

    // 加载class文件
    private byte[] loadClassData(String name) {
        name = path + name + ".class";
//        System.out.println("name: " + name);
        try (InputStream inputStream = new FileInputStream(new File(name));
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            int i = 0;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }
            return outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
