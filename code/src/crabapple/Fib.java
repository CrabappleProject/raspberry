package crabapple;

public class Fib {
    public static int fib(int num) {
        if (num < 2)
            return num;
        else
            return fib(num - 2) + fib(num - 1);
    }

    public static int fib2(int count, int pre, int result) {
        if (count == 1)
            return result;
        else
            return fib2(--count, result, result + pre);
    }

    public static int fib3(int n) {
        if (n < 2)
            return n;
        else {
            int pre = 0;
            int suf = 1;
            for (int i = 2; i <= n; i++) {
                int temp = suf;
                suf += pre;
                pre = temp;
            }
            return suf;
        }
    }
}
