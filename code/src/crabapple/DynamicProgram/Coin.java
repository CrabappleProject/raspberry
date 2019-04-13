package crabapple.DynamicProgram;

/*
 * Copyright (c) This is zhaoxubin's Java program.
 * Copyright belongs to the crabapple organization.
 * The crabapple organization has all rights to this program.
 * No individual or organization can refer to or reproduce this program without permission.
 * If you need to reprint or quote, please post it to zhaoxubin2016@live.com.
 * You will get a reply within a week,
 *
 */


public class Coin {

    /**
     * @return int
     * @Author zhaoxubin2016@live.com
     * @Description //TODO 给出一个硬币面值序列,从中找出一个硬币两两不相邻的序列(C1,C2,...,Cn-2,Cn-1,Cn),使其值的和最大,求该最大值.
     * //TODO 可以推导出公式: MAX(Cn+recode[n-2],recode[n-1])
     * @Date 10:36 19/4/11
     * @Param [values] 第0个元素保持为空,真正的值从下标1算起
     **/
    public static int valueMax(int[] values) {

        int[] recod = new int[values.length];
        recod[1] = values[1];

        for (int i = 2; i < values.length; i++) {
            recod[i] = max(values[i] + recod[i - 2], recod[i - 1]);
        }

        return recod[values.length - 1];
    }


    /**
     * @return int
     * @Author zhaoxubin2016@live.com
     * @Description //TODO 给定一个面值递增的序列values和一个需要找的钱n,求最少需要多少枚硬币
     * //TODO values的第一个值一定是1
     * @Date 12:08 19/4/11
     * @Param [values]
     **/
    public static int getNum(int[] values, int n) {

        int[] record = new int[n + 1];
        record[0] = 0;

        for (int i = 1; i <= n; i++) {

            //因为对于一个即将填充的新record,我都可以先减去任意一个币的值,只要币值小于当前n,然后再去找前面记录的最小值.
            //所以这里需要遍历
            int temp = Integer.MAX_VALUE;
            int j = 0;
            while (j<values.length&&values[j] <= i) {
                temp = min(record[i - values[j]] + 1, temp);
                j++;
            }
            record[i] = temp;
        }
        return record[n];
    }


    /**
     * @return int
     * @Author zhaoxubin2016@live.com
     * @Description //TODO 两数最大值
     * @Date 12:07 19/4/11
     * @Param [a, b]
     **/
    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * @return int
     * @Author zhaoxubin2016@live.com
     * @Description //TODO 两数最小值
     * @Date 12:07 19/4/11
     * @Param [a, b]
     **/
    public static int min(int a, int b) {
        return Math.min(a, b);
    }
}
