package com.baowen.sgg.dcxy.linklist5;

/**
 *
 * 合并2个有序链表
 * @author mangguodong
 * @create 2022-07-05
 */
public class MergeTwoSortedLists2 {

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

        Node node1_ = new Node(2);
        Node node2_ = new Node(4);
        Node node3_ = new Node(7);
        node1_.next = node2_;
        node2_.next = node3_;
        node3_.next = null;


        Node node = MergeTwoSortedLists(node1, node1_);
        TestLinkList.printList(node);
    }

    public static int count = 0;

    /**
     * 递归是方法执行实现的  即 栈  的先进后出 也即 方法的调用  先调用 后弹栈
     *
     * 递归的编码思想:
     *      可以分解成一个小问题和子问题，小问题直接解决，子问题递归 (小问题一般是递归的结束条件 + 弹栈后的操作)
     *  我的下一步操作完成了，方法弹栈弹到我时，我的操作也就完成了
     *
     *  思路:
     *      什么情况下进入子问题？
     *          n1的头结点小于n2 的头结点
     *      子问题 :某n1节点 之后的 ，2个子链表合并完了，那么 弹栈到我  我也合并完了
     *
     *      一般是弹栈或者递归结束进入小问题
     *      小问题： 某个节点 该如何处理
     *                  直接返回  即 由于n1之后的 2个子链表都合并好了，直接返回n1 就行
     *
     *              递归条件结束
     *                  返回另一个不为空的节点
     *
     * @param n1
     * @param n2
     * @return
     */
    public static Node MergeTwoSortedLists(Node n1,Node n2){

        // 考虑递归结束条件  或者是最后一个方法的退出条件
        //2个子链表  最后一个没有节点 一个有节点返回 有节点那个
        if(n1==null){
            System.out.println(" 结束条件 n1 "+ n1);
            return n2;
        }
        if(n2==null){
            System.out.println(" 结束条件 n2 "+ n2);
            return n1;
        }

        // 比较头节点
        if(n1.val<=n2.val){
            // l1头节点较小，直接提取出来，剩下的l1和l2继续合并，接在l1后面
            System.out.println(++count+" ==> n1 ");

            n1.next = MergeTwoSortedLists(n1.next,n2);
            System.out.println("n1 ==>"+ n1.val + " n2==>"+ n2.val);

            //由于n1之后的 2个子链表都合并好了，直接返回n1 就行
            return n1;
        }else {

            System.out.println(++count+" ==> n2");

            n2.next = MergeTwoSortedLists(n1,n2.next);
            System.out.println("n2 ==>"+ n2.val + " n1==>"+ n1.val);
            return n2;
        }

    }

    /**
     *
     * 思路：
     *      循环2 个链表   ，依次比较 当前最小的节点 ，把小的放在前面
     *      又不能形成新的链表，所以需要借助一个哨兵节点  连接  合并 的链表，返回的结果就是哨兵节点的下一个节点
     *
     *      还需要一个临时节点prev  保存合并链表的最后一个节点，以便再次合并的时候指向新的 节点
     *
     *
     * @param n1
     * @param n2
     * @return
     */
    public static Node MergeTwoSortedLists1(Node n1,Node n2){

        Node sentinel = new Node(-1);

        Node prev = sentinel;

        while (n1!=null && n2!=null){

            if(n1.val<=n2.val){
                //合并链表指向 2个链表最小的节点
                prev.next = n1;
                //prev 合并链表的最后一个节点 下移
                prev = n1;
                // n1 链表下移
                n1 = n1.next;

            }else{

                prev.next = n2;
                prev = n2;
                n2 = n2.next;
            }

        }
        //退出循环后，最多还有一个链表未遍历完，需要将未遍历完的子链表合并
        prev.next = (n1==null)?n2:n1;


        return sentinel.next;
    }


}
