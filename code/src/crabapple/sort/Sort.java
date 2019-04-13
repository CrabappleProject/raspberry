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
