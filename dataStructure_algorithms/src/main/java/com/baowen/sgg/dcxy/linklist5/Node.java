package com.baowen.sgg.dcxy.linklist5;

/**
 * @author mangguodong
 * @create 2022-07-04
 */
public class Node {

    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}
