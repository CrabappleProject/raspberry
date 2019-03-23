package crabapple;

public class Search {

    //二分查找
    public static boolean search(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int middle = (low + high) / 2;

        while (low <= high) {
            if (arr[middle] == key)
                return true;
            else {
                if (key < arr[middle])
                    high = middle - 1;
                else
                    low = middle + 1;
            }
            middle = (low + high) / 2;
        }
        return false;
    }
}
