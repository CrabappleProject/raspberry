package crabapple;

public class Sort {

    //插入排序
    public static int[] insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int k = arr[i];
            for (; j > 0; j--) {
                if (arr[j - 1] < k) {
                    arr[j] = arr[j - 1];
                    arr[j - 1] = k;
                }
            }
        }
        return arr;
    }
}
