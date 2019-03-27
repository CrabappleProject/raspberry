package crabapple.sort;

public class QuickSort {


    public static void sort(int[] arr, int left, int right) {

        int leftPoint = left;
        int rightPoint = right;
        int preValue = arr[left];

        while (leftPoint < rightPoint) {

            while (leftPoint < rightPoint && arr[rightPoint] >= preValue)
                rightPoint--;
            if (arr[rightPoint] < preValue) {
                int temp = arr[rightPoint];
                arr[rightPoint] = arr[leftPoint];
                arr[leftPoint] = temp;
            }
            while (leftPoint < rightPoint && arr[leftPoint] <= preValue)
                leftPoint++;
            if (arr[leftPoint] > preValue) {
                int temp = arr[rightPoint];
                arr[rightPoint] = arr[leftPoint];
                arr[leftPoint] = temp;
            }
            if (left < leftPoint) sort(arr, left, leftPoint - 1);
            if (right > rightPoint) sort(arr, rightPoint + 1, right);

        }
    }

}
