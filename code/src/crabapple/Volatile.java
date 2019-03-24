
package crabapple;

public class Volatile implements Runnable {

    public static volatile boolean isCan = true;


    @Override
    public void run() {

        int k = 0;
        while (isCan)
            System.out.println(++k);


    }
}