package crabapple;
// 导入必需的 java 库

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1=new Thread(new Volatile());
        Thread thread2=new Thread(new Volatile());

        thread1.start();
        thread2.start();

        Thread.sleep(3000);
        System.out.println("========================");
        Volatile.isCan=false;

    }
}
