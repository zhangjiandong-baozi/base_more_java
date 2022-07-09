package com.baowen.sgg.dcxy.linklist5;

import java.util.Stack;

/**
 * 链表删除倒数第N个节点
 * @author mangguodong
 * @create 2022-07-06
 */
public class RemoveNthNodeFromEnd3 {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        int length = getLength(node1);
        System.out.println("length = " + length);

        TestLinkList.printList(RemoveNthNodeFromEnd(node1,2));
    }

    /**
     * 栈思路删除n节点
     *
     * 思路：
     *      栈是 先进后出,开始弹栈的时候 弹第N个就是 要删的元素
     *
     * @param head
     * @param n
     * @return
     */
    public  static Node RemoveNthNodeFromEnd(Node head,int n) {

        //哨兵节点  结果就返回哨兵节点的下一个节点
        Node sentinel  = new Node(-1,head);

        //找到curr节点，即倒数第N 节点的前一个节点，来删除 倒数第N个节点
        Node curr = sentinel;

        Stack<Node> stack = new Stack<>();

        while (curr!=null){

            stack.push(curr);
            curr = curr.next;
        }

        //由于是xianpush 了哨兵节点所以这里是 n次循环 ，刚好是 n的前一个节点
        for (int i = 0; i <n ; i++) {
            stack.pop();
        }
        Node node = stack.peek();
        node.next = node.next.next;
        return sentinel.next;
    }



    /**
     * 思路：
     *      2遍循环 找到倒数N节点
     *
     *
     *
     * @param head
     * @param n
     * @return
     */
    public  static Node RemoveNthNodeFromEnd1(Node head,int n){
        int len = getLength(head);

        //哨兵节点  结果就返回哨兵节点的下一个节点
        Node sentinel  = new Node(-1,head);
        //自己正序和倒数   即 1 + len = len+1   所以 倒数n 的正数是 len+1-n
        int count = 0;
        //找到curr，curr 即 倒数n的前一个节点，来删除倒数N 节点
        Node curr = sentinel;
        while (curr!=null && count<len-n){
            count++;
            curr = curr.next;
        }

        curr.next = curr.next.next;

        return sentinel.next;
    }

    public static int getLength(Node head){

        int count = 0;
        while (head!=null){
            count++;
            head=head.next;
        }
        return count;
    }

}
