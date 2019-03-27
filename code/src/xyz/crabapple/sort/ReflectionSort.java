package xyz.crabapple.sort;
/**
 * Copyright (c) This is zhaoxubin's Java program.
 * Copyright belongs to the crabapple organization.
 * The crabapple organization has all rights to this program.
 * No individual or organization can refer to or reproduce this program without permission.
 * If you need to reprint or quote, please post it to zhaoxubin2016@live.com.
 * You will get a reply within a week,
 **/

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * author:zhaoxubin
 * version: 1.1
 * editLog:2019/03/27 first finished.
 * <p>
 * <p>
 * this is a sort program for list special field.
 * you can edit the program for yourself.
 * use the method,your program class must have Getter and Setter for key field.
 *
 * @param <T>
 */
public class ReflectionSort<T> {


    /**
     * will sort by field,for example: int id,then you can input "id".
     */
    private String field;


    /**
     * getter method.
     */
    Method getter;


    /**
     * setter method.
     */
    Method setter;


    /**
     * list value
     */
    List<T> list;


    /**
     * list length.
     */
    int listLength = 0;


    /**
     * this is A constructor ,Only need field.
     *
     * @param field
     */
    public ReflectionSort(String field) {

        this.field = String.valueOf(field.charAt(0)).toUpperCase() + field.substring(1, field.length());
    }

    /**
     * sort by Asc
     *
     * @param list
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void sortByAsc(List<T> list) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        sortByType(list);
    }

    /**
     * sort by desc
     *
     * @param list
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void sortByDesc(List<T> list) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        sortByType(list);
        desc();
    }

    /**
     * this is core for sort ,you can edit the program for your need.
     *
     * @param list
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void sortByType(List<T> list) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        listLength = list.size();
        if (listLength == 0)
            return;
        this.list = list;
        this.getter = list.get(0).getClass().getMethod("get" + field);
        this.setter = list.get(0).getClass().getMethod("set" + field, int.class);

        sort(0, listLength - 1);

    }

    /**
     * get value to a object
     *
     * @param index
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private int getByIndex(int index) throws InvocationTargetException, IllegalAccessException {

        return (int) getter.invoke(list.get(index));
    }


    /**
     * set value to a object
     *
     * @param index
     * @param value
     * @return
     */
    private boolean setByIndex(int index, int value) {

        try {
            setter.invoke(list.get(index), value);
        } catch (Exception a) {
            return false;
        }

        return true;
    }


    /**
     * this is sort method ,use quickSort thinking.
     *
     * @param left
     * @param right
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void sort(int left, int right) throws InvocationTargetException, IllegalAccessException {

        int leftPoint = left;
        int rightPoint = right;
        int preValue = (int) getter.invoke(list.get(left));

        while (leftPoint < rightPoint) {

            while (leftPoint < rightPoint && getByIndex(rightPoint) >= preValue)
                rightPoint--;
            if (getByIndex(rightPoint) < preValue)
                swap(leftPoint, rightPoint);

            while (leftPoint < rightPoint && getByIndex(leftPoint) <= preValue)
                leftPoint++;
            if (getByIndex(leftPoint) > preValue)
                swap(leftPoint, rightPoint);

            if (left < leftPoint) sort(left, leftPoint - 1);
            if (right > rightPoint) sort(rightPoint + 1, right);

        }
    }


    /**
     * swap for tow value.
     *
     * @param index1
     * @param index2
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void swap(int index1, int index2) throws InvocationTargetException, IllegalAccessException {
        int temp = getByIndex(index1);
        setByIndex(index1, getByIndex(index2));
        setByIndex(index2, temp);
    }


    /**
     * default sort result is asc,so the method is for desc.
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void desc() throws InvocationTargetException, IllegalAccessException {

        int left = 0;
        int right = listLength - 1;
        while (left < right) {
            swap(left, right);
            left++;
            right--;
        }
    }

}