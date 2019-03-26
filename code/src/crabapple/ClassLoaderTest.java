package crabapple;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.ObjLongConsumer;

public class ClassLoaderTest extends ClassLoader {


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        MyClassLoader myClassLoader=new MyClassLoader("C:\\Users\\Think\\Documents\\GitHub\\raspberry\\code\\output\\production\\raspberry\\crabapple");
        Class myClass=myClassLoader.loadClass("crabapple.Fib");
        Object object=myClass.newInstance();
        Method method=myClass.getMethod("fib",int.class);
        System.out.println(method.invoke(object,4));
    }

}


class MyClassLoader extends ClassLoader {
    private String classPath;  // 获取当前类的在磁盘中保存的地址

    // 通过构造函数将地址注入
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    // 将文件内容加载进入内存
    private byte[] loadByte(String name) throws Exception {
        String inPath=classPath + "/" + name + ".class";
        // 获取一个输入流,
        FileInputStream fis = new FileInputStream(inPath);
        // 获取长度
        int len = fis.available();
        // 定义byte数组
        byte[] data = new byte[len];
        // 加载进入内存
        fis.read(data);
        // 关闭流
        fis.close();
        return data;
    }

    // 重写findClass方法，让加载的时候调用findClass方法
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 读取文件到数组中
            byte[] data = loadByte(name);
            // 将字节码加载进入内存当中
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}