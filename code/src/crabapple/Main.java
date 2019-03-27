package crabapple;


public class Main {

    public static void main(String[] args) {
        //测试
        SkipTable skipTable=new SkipTable();
        skipTable.insert(1);
        skipTable.insert(2);
        skipTable.insert(3);
        skipTable.insert(4);
        skipTable.insert(5);
        skipTable.insert(6);
        skipTable.insert(7);
        skipTable.insert(8);
        skipTable.insert(9);
        skipTable.insert(10);

        //边界值测试
        System.out.println(skipTable.search(0));
        System.out.println(skipTable.search(1));
        System.out.println(skipTable.search(2));
        System.out.println(skipTable.search(5));
        System.out.println(skipTable.search(9));
        System.out.println(skipTable.search(10));
        System.out.println(skipTable.search(11));
    }
}

//output:
/**
 * false
 * true
 * true
 * true
 * true
 * true
 * false
 *
 * Process finished with exit code 0
 */
