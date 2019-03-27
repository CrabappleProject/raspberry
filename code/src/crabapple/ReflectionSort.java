package crabapple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionSort<T> {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(3, "a"));
        personList.add(new Person(7, "b"));
        personList.add(new Person(2, "c"));
        personList.add(new Person(89, "d"));
        personList.add(new Person(12, "e"));
        personList.add(new Person(6, "f"));

        xyz.crabapple.sort.ReflectionSort<Person> reflectionSort = new xyz.crabapple.sort.ReflectionSort<>("id");
        reflectionSort.sortByAsc(personList);
        for(Person p:personList)
            System.out.println(p.id);
    }

}

