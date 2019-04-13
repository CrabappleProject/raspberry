java,多线程
## 初识JAVA多线程编程
### 1. 术语辨析
任务和线程是不同的,Java中Thread类本身不执行任何操作,它只驱动赋予它的任务,而Runnable才是定义任务的地方.

### 2. 创建任务的方式有两种
#### 2.1 实现Runnable接口中的run方法
1.查看Runnable源码,可以看到只有一个run()方法
```java
   @FunctionalInterface
public interface Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */
    public abstract void run();
}

```
2.使用Runnable,直接继承即可,这样就创建了一个任务.
````java
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        int i=0;
        while(true)
            System.out.println(i++);
    }
    //暗示调度器该线程可以让出资源了
    Thread.yield();
}
````

#### 2.2  Thread中实现run方法
1.Thread部分源码
````java
/*
 * @see     Runnable
 * @see     Runtime#exit(int)
 * @see     #run()
 * @see     #stop()
 * @since   JDK1.0
 */
public class Thread implements Runnable {
    /* Make sure registerNatives is the first thing <clinit> does. */
    private static native void registerNatives();
    static {
        registerNatives();
    }
`````
2.可以看到Thread继承了Runnable接口,所以Thread创建任务也是用的run方法
3.代码可以这样写
````java
class MyThread extends Thread{
    @Override
    public void run() {
        int count=10;
        while(count--!=0)
            System.out.print(count+" ");
    }
}
````
### 3.  运行任务
1.不要误会,下面的方法并未启动一个线程,而是单纯的调用了实现Runnable接口的MyRunnable的run()方法,所以该任务的运行依旧被分派到该main线程中,不会独立运行在一个新线程里.
````java
public class Main{
    public static void main(String[] args){
        Runnable runnable=new Runnable();
        runnable.run();
    }
}
````
2.调用Thread的start(),Thread中记录了很多线程信息,包括线程名称和优先级等,以MyRunnable的实例作参传入Thread,然后start即可运行.
````java
public static void main(String[] args){

Thread thread=new Thread(new MyRunnable);
thread.start()
}
````
3.在调用start()后,子线程不会因为主线程代码执行结束而停止.

### 4. 使用执行器(Executor)管理Thread.
````java
public static void main(String[] args){

    //常见执行器对象
    ExecutorService executorService= Executors.newCachedThreadPool();
    //向执行器中添加任务
    executorService.execute(new MyRunnable());
    //关闭向执行器中添加任务;
    executorService.shutdown();
````
创建执行器又三种类型可选,分别是newCachedThreadPool,newFixedThreadPool,newSingleThreadPool,区别如下
1. newCachedThreadPool:系统自动调配线程池中的线程数量,并主动复用已完成的线程资源.
2. newFixedThreadPool:可以自定义线程池中线程数量;
3. newSingleThreadPool:单例,线程池中只有一个线程,加载的任务会被排队,任务只能一个个依次完成.

### 5.线程的返回值
1.有些任务执行完后需要返回值,那么创建任务时可以通过实现Callale接口而实现该目的,Callable是一种具有类型参数的泛型,因为返回值是需要定义类型的.
````java
class Task implements Callable<String>{

    @Override
    public String call() throws Exception {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        TimeUnit.MILLISECONDS.sleep(100);
        return "线程执行啦";
    }
}
````
2.然后在main()方法中接受返回值信息,对线程信息的管理可以用Future<String>
````java
public static void main(String[] args){
    ExecutorService executorService=Executors.newCachedThreadPool();
    Future<String> future=executorService.submit(new Callable<String>(){    
        @Override   
        public String call() throws Exception {
        return "执行啦";   
        }
    });
    //判断是否完成
    System.out.println(future.isDone());
    //任务完成后,才会打印词条语句,否则会阻塞.
    System.out.println(future.get());
    //判断是否完成
    System.out.println(future.isDone());
}
----------输出----------
false
执行啦
true
````
3.程序运行到get()方法时会阻塞,当运行完后,主程序才会继续向后执行.
### 6.  程序的优先级
1.优先级的设置在run方法中
````java
public void run(){

    //例如将优先级设置为10
    Thread.currentThread().setPriority(10)
}
````
2.为了方便移植,建议将优先级设置为Thread中的三个常量.

### 7. 后台线程
1.设置后台线程语法 thread.setDaemon(true);要在start()之前.
2.后台线程创建的线程也为后台线程.