package crabapple.sort;

/*
 * Copyright (c) This is zhaoxubin's Java program.
 * Copyright belongs to the crabapple organization.
 * The crabapple organization has all rights to this program.
 * No individual or organization can refer to or reproduce this program without permission.
 * If you need to reprint or quote, please post it to zhaoxubin2016@live.com.
 * You will get a reply within a week,
 *
 */

public class InsertSort {

    public void sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int j = i;
            while (j > 0) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    j--;
                } else break;
            }
        }
    }


    public void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 6, 9, 12, 67, 13, 4};
        new InsertSort().sort(arr);
        for (int i : arr)
            System.out.print(i + " ");
    }
}
