package crabapple;

public class ParentClone implements Cloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
        ParentsClone p1 = new ParentsClone(1, new ChildClone("HAHA"));
        ParentsClone p2 = (ParentsClone) p1.clone();
        p2.childClone = (ChildClone) p1.childClone.clone();
        System.out.println("p1 HashCode: " + p1.hashCode() + "  p1.child HashCode: " + p1.childClone.hashCode());
        System.out.println("p2 HashCode: " + p2.hashCode() + "  p2.child HashCode: " + p2.childClone.hashCode());

        /**
         * p1 HashCode: 1163157884  p1.child HashCode: 1956725890
         * p2 HashCode: 356573597  p2.child HashCode: 1735600054
         */
    }

}


class ParentsClone implements Cloneable {

    public int id;
    public ChildClone childClone;

    public ParentsClone(int id) {
        this.id = id;
    }

    public ParentsClone(int id, ChildClone childClone) {
        this.id = id;
        this.childClone = childClone;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


class ChildClone implements Cloneable {
    public String name;

    public ChildClone(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
