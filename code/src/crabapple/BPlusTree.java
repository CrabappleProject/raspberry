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

import java.awt.dnd.DropTarget;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BPlusTree {

    /**
     * the B tree's rank,it must bigger than 3.
     */
    private int rank = 1;

    /**
     * root about the B tree.
     */
    private Node root;

    /**
     * port
     */
    static class Port {

        //child port
        Node child;

        //right port
        Port next;

        private boolean hasNext() {
            return !(next == null);
        }
    }

    /**
     * data
     */
    static class Data {
        //value
        int key;

        Data next;

        public Data(int key) {
            this.key = key;
        }

        private boolean hasNext() {
            return !(next == null);
        }
    }

    /**
     * node covers(port and data)
     */
    static class Node {

        //data count
        int itemCount = 0;

        //port of child list first
        Port portfirst;

        //port of data list first
        Data dataFirst;


        Node parent;
    }

    /**
     * initialization the B tree at first.
     *
     * @param rank
     */
    public BPlusTree(int rank) {
        this.rank = rank;
        this.root = new Node();
    }


    public Data get(int key) {


        if (root.itemCount == 0) return null;
        else {
            Node node = root;

            Data dataPrim = node.dataFirst;
            Port portPrim = node.portfirst;

            do {
                do {
                    if (key == dataPrim.key)
                        return dataPrim;
                    else if (key < dataPrim.key) {

                        node = portPrim.child;
                        if (node == null)
                            return null;
                        break;
                    } else if (key > dataPrim.key && dataPrim.next != null) {
                        dataPrim = dataPrim.next;
                        portPrim = portPrim.next;
                    } else {
                        node = portPrim.next.child;
                        if (node == null)
                            return null;
                        break;
                    }
                } while (dataPrim != null);

                dataPrim = node.dataFirst;
                portPrim = node.portfirst;
            } while (dataPrim != null);

        }
        return null;
    }

    public Data insert(Data data) {
        Data primResult = get(data.key);
        if (primResult != null) {
            primResult.key = data.key;
        } else {
            if (root == null) {
                root = new Node();
                root.itemCount = 1;
                root.dataFirst = new Data(data.key);
                Port port2 = new Port();
                Port port1 = new Port();
                port1.next = port2;
                root.portfirst = port1;
            } else {

                int key = data.key;
                Node node = root;
                Node parent = null;
                while (true) {

                    Data dataPrim = node.dataFirst;
                    Port portPrim = node.portfirst;


                    boolean isAdd = false;
                    int local = 0;
                    while ((++local) < rank) {

                        if (key < dataPrim.key) {
                            if (portPrim.child == null) {

                                Port temp = portPrim;
                                portPrim = new Port();
                                portPrim.next = temp;

                                Data tempData = dataPrim;
                                dataPrim = data;
                                data.next = tempData;

                                node.itemCount++;
                                isAdd = true;
                            } else {
                                parent = node;
                                node = portPrim.child;
                            }

                            break;
                        } else if (portPrim.next.child == null && key > dataPrim.key && (dataPrim.next == null)
                                || key < dataPrim.next.key) {
                            if (portPrim.next.child == null) {
                                Port temp = portPrim.next;
                                portPrim.next = new Port();
                                portPrim.next.next = temp;

                                Data tempData = dataPrim.next;
                                dataPrim.next = data;
                                data.next = tempData;
                                node.itemCount++;
                                isAdd = true;
                            } else {
                                parent = node;
                                node = portPrim.next.child;
                            }
                            break;
                        }
                        dataPrim = dataPrim.next;
                        portPrim = portPrim.next;

                    }
                    if (isAdd) {
                        if (node.itemCount < rank)
                            return data;
                        else {
                            List<Node> nodes = split(node);

                            Node first=nodes.get(0);
                            Node left=nodes.get(1);
                            Node right=nodes.get(2);


                            Port portP=parent.portfirst;
                            for(int i=1;i<=node.itemCount+1;i++){
                                if(portP.child==node){

                                }
                            }

                        }
                    }


                }

            }

        }

return null;
    }

    private static List<Node> split(Node node) {
        int middle = node.itemCount / 2;

        Data middleData = new Data(1);
        Port portSuf = node.portfirst;
        Data dataSuf = node.dataFirst;

        for (int i = 1; i <= middle; i++) {

            Data temp = dataSuf;
            dataSuf = dataSuf.next;
            if (i == middle) {
                temp.next = null;
                middleData = dataSuf;
                middleData.next = null;
                dataSuf = dataSuf.next;
            }

            Port portTemp = portSuf;
            portSuf = portSuf.next;
            if (i == middle) {
                portTemp = portSuf;
                portSuf = portSuf.next;
                portTemp.next = null;
            }
        }

        Node left = new Node();
        left.itemCount = middle;
        left.portfirst = node.portfirst;
        left.dataFirst = node.dataFirst;

        Node right = new Node();
        right.itemCount = node.itemCount - middle;
        right.portfirst = portSuf;
        right.dataFirst = dataSuf;


        Port port2 = new Port();
        port2.child = right;
        Port port1 = new Port();
        port1.child = left;
        port1.next = port2;
        Node prime = new Node();
        prime.itemCount = 1;
        prime.portfirst = port1;
        prime.dataFirst = middleData;

        List<Node> nodes = new ArrayList<>();
        Collections.addAll(nodes, prime, left, right);

        return nodes;
    }

}
