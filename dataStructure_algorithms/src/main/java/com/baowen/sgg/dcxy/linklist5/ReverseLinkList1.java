package com.baowen.sgg.dcxy.linklist5;

/**
 *
 * 反转链表
 *
 * @author mangguodong
 * @create 2022-07-04
 */
public class ReverseLinkList1 {

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

        TestLinkList.printList(node1);
        System.out.println();
        TestLinkList.printList(ReverseLinkList(node1));
    }

    public static  int count= 0;

    /**
     * 递归的编码思想:
     *      可以分解成一个小问题和子问题，小问题直接解决，子问题递归 (小问题一般是递归的结束条件+ 弹栈后的某个节点的处理)
     *          判断什么情况进入子问题，什么情况是小问题
     *
     * 递归实现反转链表
     * 方法的调用一般通过栈实现 ,不停压栈直到遇到终止条件，在弹栈，  即 它是从 链表末尾的元素开始反转
     * 思路：
     *      什么情况下进入子问题？
     *          每一节点都要操作，没有判判断条件
     *
     *      子问题:
     *
     *      小问题：
     *          即弹栈后某个节点的处理
     *              以倒数第二个元素为例    最后一个节点的指向它，它自己指向null(为什么指向null  ，因为需要反转后的链表的第一个节点指向null)
     *
     *          递归条件结束
     *              返回最后一个节点自己
     *
     *
     * @param head
     * @return
     */
    public static Node ReverseLinkList(Node head){

        //最后一个节点和特殊情况怎么处理？
        //小问题: 节点为空   和 ( 只有一个节点 或剩下一个节点) 返回自己    解决
        System.out.println("递归次数count"+ (++count));
        if(head==null || head.next==null){
            return head;
        }

        Node endNode = head.next;

        // 子问题 ： 要反转 node1 节点后的元素  ，可以先反转node2 节点之后的元素   ==> 递归
        Node reverseLinkList = ReverseLinkList(endNode);
        //小问题 :从倒数第二个元素起开始弹栈，即 该如何处理呢？    ==>解决
        // 即4
        //根据最后的条件 head.next == null   即 endNode(head.next)就是最后一个元素   head 当前元素

        //最后那个节点指向它,以此类推其他节点
        // 即5 指向了4
        //head.next.next = head;
        System.out.println("head=="+head.val);
        endNode.next = head;
        //当前节点指向null,以此类推其他节点
        // 自己指向空  即4指向空
        head.next = null;

        System.out.println("reverseLinkList=="+reverseLinkList.val);
        //每次都返回此链表最后一个元素即5，上面则是改变 循环改变链表的指向
        return reverseLinkList;
    }

    /**
     * 迭代实现反转列表即 循环链表从头结点开始实现
     *
     * 思考过程: 一直在纠结指针在 链表内改怎么指，把自己搞晕了， 对于链表 应该 画图，或者 对着元素思索，不要过多与纠结指针，而应该思考把链表看成是实体，我该怎么操作
     *
     * 思路：
     *      就是将每个节点的next 指向上一个节点
     *
     *      1->2->3->4->5->null
     *
     * @param head
     * @return
     */
    public static Node ReverseLinkList1(Node head){

        Node curr = head;
        //1 反转后指向null
        Node prev = null;

        while (curr!=null){

            //保留当前节点的下一个节点
            Node tempNext = curr.next;
            curr.next = prev;

            //当前节点的上一个节点向下移动，且当前节点向下移动,
            prev = curr;
            curr = tempNext;
        }
        //链表最后一个节点是空，所以反转后的 头结点是 最后的prev
        return prev;

    }





}
