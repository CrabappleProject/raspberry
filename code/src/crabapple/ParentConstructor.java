package crabapple;

public class ParentConstructor {

    public static void main(String[] args) throws CloneNotSupportedException {

        Parents p1=new Parents(1,new Child("HAHA"));
        Parents p2=new Parents(p1);

        System.out.println("p1 HashCode: " + p1.hashCode() + "  p1.child HashCode: " + p1.child.hashCode());
        System.out.println("p2 HashCode: " + p2.hashCode() + "  p2.child HashCode: " + p2.child.hashCode());

        //output
        /**
         * p1 HashCode: 1163157884  p1.child HashCode: 1956725890
         * p2 HashCode: 356573597  p2.child HashCode: 1735600054
         */

    }
}


class Parents {

    public int id;
    public Child child;

    public Parents(int id, Child child) {
        this.id = id;
        this.child = child;
    }
    //实现对象的复制
    public Parents(Parents parents) {
        id = parents.id;
        child = new Child(parents.child);
    }
}


class Child {
    public String name;

    public Child(String name) {
        this.name = name;
    }
    //实现对象的复制
    public Child(Child child) {
        name = child.name;
    }
}
