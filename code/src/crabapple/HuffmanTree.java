package crabapple;


import java.util.*;


public class HuffmanTree {

    //哈夫曼编码记录栈
    static Stack<Integer> huffmanCode = new Stack<>();

    static class Node {

        double weight = 0;
        Node left = null;
        Node right = null;

        public Node(double weight) {
            this.weight = weight;
        }
    }

    //打印哈夫曼编码
    public static void getHafuCode(Node node) {

        if (node.left == null && node.right == null)
            print(huffmanCode, node.weight);
        if (node.left != null) {
            huffmanCode.push(0);

            getHafuCode(node.left);
            huffmanCode.pop();
        }

        if (node.right != null) {
            huffmanCode.push(1);
            getHafuCode(node.right);
            huffmanCode.pop();

        }
    }

    //打印
    public static void print(List<Integer> list, double d) {
        String text = "";
        for (int it : list)
            text += it;
        text += (" " + d);
        System.out.println(text);
    }

    //生成哈夫曼树
    public static Node createHafuman(List<Double> weights) {

        Collections.sort(weights);

        List<Node> nodes = new ArrayList<>();
        for (double item : weights)
            nodes.add(new Node(item));

        while (nodes.size() != 1) {
            Node pre = getMinAndRemove(nodes);
            Node suf = getMinAndRemove(nodes);
            Node newNode = new Node(pre.weight + suf.weight);
            newNode.left = pre;
            newNode.right = suf;
            nodes.add(newNode);

        }
        return nodes.get(0);
    }


    //获取list中权重最小的节点,并删除
    public static Node getMinAndRemove(List<Node> nodes) {

        Node first = nodes.get(0);
        int k = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).weight < first.weight) {
                first = nodes.get(i);
                k = i;
            }
        }
        nodes.remove(k);
        return first;
    }


    //测试
    public static void main(String[] args) {

        List<Double> list = new ArrayList<>();
        Collections.addAll(list, 0.1, 0.3, 0.2, 0.5, 0.7);
        Node node = createHafuman(list);
        getHafuCode(node);
    }

    //测试结果
    /*
    0 0.7
    10 0.5
    110 0.3
    1110 0.1
    1111 0.2
     */

}



