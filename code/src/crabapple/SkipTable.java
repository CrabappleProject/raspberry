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

import java.util.Random;

/**
 * 节点类定义
 */
class Node {

    //值
    public int value = 0;

    //当前层下一个节点
    public Node right;

    //下一层,直连的节点
    public Node down;

    //构造函数
    public Node() {

    }

    public Node(int value) {
        this.value = value;
    }
}

/**
 * 跳表定义
 */
public class SkipTable {

    //表层数
    private int levelCount;

    //表的头指针
    private Node firstNode;

    //初始化:层数为1,共前后两个节点,一个最小值,一个最大值
    private void init() {
        levelCount = 1;
        firstNode=new Node();
        firstNode.value = Integer.MIN_VALUE;
        firstNode.right = new Node(Integer.MAX_VALUE);
    }

    public SkipTable() {
        init();
    }

    /**
     * 查找值
     * @param value
     * @return
     */
    public boolean search(int value) {

        Node current = firstNode;
        return toSearch(current, value);
    }

    private boolean toSearch(Node node, int value) {
        if (node.value == value)
            return true;
        else if (node.right!=null&&value >= node.right.value)
            return toSearch(node.right, value);
        else if (node.down != null)
            return toSearch(node.down, value);
        return false;
    }



    /**
     * 插入值
     * @param value
     * @return
     */
    public boolean insert(int value) {

        //判断是否有这个元素
        if (search(value))
            return false;

        //随机获取一个层数
        int willLevel = updateLevelCount();

        //判断是否添加新层
        if (willLevel > levelCount) {
            Node newFirstNode = new Node();
            addLevel(firstNode, newFirstNode);
            firstNode=newFirstNode;
            levelCount = willLevel;
        }

        //插入新元素
        Node port = firstNode;
        int skipLevel = levelCount - willLevel;

        //迭代到指定层
        while ((skipLevel--) > 0)
            port = port.down;

        //上下层新节点的桥梁
        Node insertNode = null;

        while (port != null) {

            //获取当前层第一个节点指针
            Node curPort = port;

            //迭代到右边的节点值比自己大为止
            while (port.right.value < value)
                port = port.right;

            //准备插入的新节点
            Node curInNode = new Node(value);

            //更新当前节点和前节点指针指向
            curInNode.right = port.right;
            port.right = curInNode;

            //将当前节点引用给上层节点
            if (insertNode != null)
                insertNode.down = curInNode;

            //将新插入的节点指针更新到insertNode,以备在下一层建立指向
            insertNode = curInNode;

            //行头指针向下迭代
            port = port.down;
        }
        return true;

    }

    /**
     * 添加新层
     *
     * @param oldFirst
     * @param newFirst
     */
    private void addLevel(Node oldFirst, Node newFirst) {

        newFirst.value = oldFirst.value;
        newFirst.down = oldFirst;
        if (oldFirst.right != null) {
            Node newRightNode = new Node();
            newFirst.right = newRightNode;
            addLevel(oldFirst.right, newRightNode);
        }
    }

    /**
     * 以一定概率使得获取一个和老层数差值不超过1的新层数
     * @return
     */
    private int updateLevelCount() {

        Random random=new Random();
        int v = random.nextInt(10);
        return v ==0 ? random.nextInt(levelCount) + 2 : random.nextInt(levelCount)+1 ;
    }
}
