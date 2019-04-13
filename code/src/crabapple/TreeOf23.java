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
public class TreeOf23{







}



































//
//public class TreeOf23 {
//
//    public Node root;
//
//
//    public TreeOf23() {
//        root = new Node();
//    }
//
//    static class Value<V> {
//
//        Integer key;
//        V value;
//
//    }
//
//    static class Node<V> {
//
//        int childCount;
//
//        Value<V> keyFirst;
//        Value<V> keySecond;
//
//        Value<V> keyPlus;
//
//        Node parent;
//
//        Node nodeLeft;
//        Node nodeMiddle;
//        Node nodeRight;
//        Node nodePlus;
//
//
//        public Node(Value<V> keyFirst) {
//            this.keyFirst = keyFirst;
//        }
//    }
//
//    /**
//     * 通过关键字查询节点
//     *
//     * @param key
//     * @return
//     */
//    public Value search(int key) {
//
//        Node result = toSearch(key, root);
//        if (isSearchValue(result.keyFirst, key))
//            return result.keyFirst;
//        if (isSearchValue(result.keySecond, key))
//            return result.keySecond;
//        return null;
//    }
//
//    private boolean isSearchValue(Value value, int key) {
//        if (value == null)
//            return false;
//        else {
//            if (value.key == key)
//                return true;
//            else return false;
//        }
//    }
//
//    private Node toSearch(Integer key, Node node) {
//        if ((node.keyFirst != null && node.keyFirst.key == key)
//                || (node.keySecond != null && node.keySecond.key == key))
//            return node;
//        else {
//            if (node.keyFirst != null && key < node.keyFirst.key && node.nodeLeft != null)
//                return toSearch(key, node.nodeLeft);
//            else if (node.keySecond != null && key < node.keySecond.key && node.nodeMiddle != null)
//                return toSearch(key, node.nodeMiddle);
//            else if (node.nodeRight != null)
//                return toSearch(key, node.nodeRight);
//            return node;
//        }
//    }
//
//
//    public boolean insert(Value value) {
//
//        int key=value.key;
//
//        Node result = toSearch(key, root);
//
//        //有这个值,返回false即不插入
//        if (isSearchValue(result.keyFirst, key)||isSearchValue(result.keySecond, key))
//            return false;
//
//        else {
//
//            if(result.childCount==0){
//                result.keyFirst=value;
//                result.childCount=2;
//            }
//
//            else if(result.childCount==2){
//                if(key<result.keyFirst.key){
//                    result.keySecond=result.keyFirst;
//                    result.keyFirst=value;
//                }
//                else
//                    result.keySecond=value;
//                result.childCount=3;
//            }
//            else if(result.childCount==3){
//
//                result.keyFirst=min(result.keyFirst,result.keySecond,value);
//                result.keySecond=max(result.keyFirst,result.keySecond,value);
//                result.keyPlus=middle(result.keyFirst,result.keySecond,value);
//
//                if(result.parent==null){
//                    Node node=new Node(result.keyPlus);
//                    Node child1=new Node(result.keyFirst);
//                    child1.nodeLeft=result.nodeLeft;
//                    child1.nodeMiddle=result.nodeMiddle;
//                    child1.parent=node;
//                    child1.childCount=2;
//                    Node child2=new Node(result.keySecond);
//                    child2.parent=node;
//                    child2.nodeLeft=result.nodeRight;
//                    child2.childCount=2;
//                    node.nodeLeft=child1;
//                    node.nodeMiddle=child2;
//                    root=node;
//                    return true;
//                }
//
//
//                else if(result.parent.childCount==2){
//
//                    Node parent=result.parent;
//                   if(parent.nodeLeft==result){
//                       parent.keySecond=parent.keyFirst;
//                       parent.nodeRight=parent.nodeMiddle;
//
//                       Node child1=new Node(result.keyFirst);
//                       child1.nodeLeft=result.nodeLeft;
//                       child1.nodeMiddle=result.nodeMiddle;
//                       child1.parent=parent.nodeLeft;
//
//                       Node child2=new Node(result.keySecond);
//                       child2.nodeLeft=result
//
//
//                       parent.nodeMiddle=result
//                   }
//
//                }
//
//
//            }
//
//
//        }
//
//    }
//
//    private Value max(Value v1,Value v2,Value v3){
//        return v1.key>v2.key?(v1.key>v3.key?v1:v3):(v2.key>v3.key?v2:v3);
//    }
//    private Value min(Value v1,Value v2,Value v3){
//        return v1.key<v2.key?(v1.key<v3.key?v1:v3):(v2.key<v3.key?v2:v3);
//    }
//    private Value middle(Value v1,Value v2,Value v3){
//        Value min=min(v1,v2,v3);
//        Value max=max(v1,v2,v3);
//
//        return v1==min?(v2==max?v3:v2):(v2==min?(v3==max?v1:v3):v2==max?v1:v2);
//    }
//
//
//}
