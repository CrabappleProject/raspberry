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

import java.io.*;

public class IO  implements Serializable {

    int id;
    String name;


    public IO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        IO serial=new IO(1,"crabapple");

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serial);
        byte[] re=byteArrayOutputStream.toByteArray();
        ObjectInputStream ins=new ObjectInputStream(new ByteArrayInputStream(re));
        IO ii=(IO)ins.readObject();
        System.out.println(ii.name);
    }

}
