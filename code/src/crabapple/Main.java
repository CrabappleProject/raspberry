package crabapple;


public class Main {

    public static void main(String[] args) {


        System.out.println(5.0/4);
    }

    public static int getNum(int m) {
        int[] arr = new int[m + 1];

        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        if (m < 4)
            return m + 1;

        else
            for (int i = 4; i <= m; i++) {

                arr[i] = arr[i - 2] + arr[i - 3];
            }

        return arr[m];

    }
}
