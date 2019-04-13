package crabapple;

/*
 * Copyright (c) This is zhaoxubin's Java program.
 * Copyright belongs to the crabapple organization.
 * The crabapple organization has all rights to this program.
 * No individual or organization can refer to or reproduce this program without permission.
 * If you need to reprint or quote, please post it to zhaoxubin2016@live.com.
 * You will get a reply within a week,
 *
 */

public class StringTest {
    public static void main(String[] args) {

        b b1=new b();
        a a1=b1;
        a1.print();

        System.out.println(a1.getClass().getName());
        a1.haha();
    }
}


class a{

    public void print(){
        System.out.println("A");
    }

  final   public void haha(){
        System.out.println("hahah");
    }
}

class b extends a{
    @Override
    public void print() {
        System.out.println("B");
    }


}