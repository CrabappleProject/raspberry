package crabapple;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.ObjLongConsumer;

public class ClassLoaderTest extends ClassLoader {


    public static void main(String[] args) throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader("C:/Users\\Think\\Documents\\GitHub\\raspberry\\code\\output\\production\\raspberry\\crabapple");
        Class myClass = myClassLoader.loadClass("crabapple.Fib");
        Object object = myClass.newInstance();
        Method method = myClass.getMethod("fib", int.class);
        System.out.println(method.invoke(object, 4));
    }
    /**
     * 3
     * Process finished with exit code 0
     */

}


class MyClassLoader extends ClassLoader {
    private String classPath;  // 保存的地址

    /**
     * 传入地址构造函数
     * @param classPath
     */
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 读取class文件
     * @param name
     * @return
     * @throws Exception
     */
    private byte[] loadByte(String name) throws Exception {
        String inPath = classPath + "/" + name + ".class";
        FileInputStream fis = new FileInputStream(inPath);
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    /**
     * 重写findClass方法，让加载的时候调用findClass方法
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            // 将字节码载入内存
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}