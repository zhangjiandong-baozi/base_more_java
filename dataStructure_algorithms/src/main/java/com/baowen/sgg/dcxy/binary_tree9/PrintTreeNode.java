package com.baowen.sgg.dcxy.binary_tree9;

import java.util.LinkedList;

/**
 * @author mangguodong
 * @create 2022-08-12
 */
public class PrintTreeNode {


    /**
     *
     *              1
     *           2     3
     *              4     5
     *                6
     *
     * @param args
     */
    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;

        node3.left = node4;
        node3.right = node5;

        node4.right = node6;

        PrintProOrder(node1);
        System.out.println();
        PrintMidOrder(node1);
        System.out.println();
        PrintPostOrder(node1);
        System.out.println();
        PrintLevelOrder(node1);

        TreeNode node11 = new TreeNode(1);
        TreeNode node22 = new TreeNode(2);
        TreeNode node33 = new TreeNode(3);
        TreeNode node44 = new TreeNode(4);
        TreeNode node55 = new TreeNode(5);
        TreeNode node66 = new TreeNode(6);
        TreeNode node77 = new TreeNode(7);


        node11.left = node22;
        node11.right = node33;

        node22.left = node44;
        node22.right = node55;

        node33.left = node66;
        node33.right = node77;
        System.out.println();
        PrintLevelOrder(node11);



    }


    /**
     * 前序遍历/先序遍历
     *  先打印根节点、然后左子树、然后右子树
     * @param root
     */
    public static void PrintProOrder(TreeNode root){
        if(root==null)return;

        System.out.print(root.val+"\t");
        PrintProOrder(root.left);
        PrintProOrder(root.right);
    }

    /**
     * 中序遍历
     *  先打印左子树、然后根、然后右子树
     * @param root
     */
    public static void PrintMidOrder(TreeNode root){
        if(root==null)return;

        PrintMidOrder(root.left);
        System.out.print(root.val+"\t");
        PrintMidOrder(root.right);
    }

    /**
     * 后序遍历
     *  先打印左子树、然后右子树、然后根
     * @param root
     */
    public static void PrintPostOrder(TreeNode root){
        if(root==null)return;

        PrintPostOrder(root.left);
        PrintPostOrder(root.right);
        System.out.print(root.val+"\t");
    }

    /**
     * 层序遍历
     * 分层遍历
     *
     *  先入队先打印
     * @param root
     */
    public static void PrintLevelOrder(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();

        //入队
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            System.out.print(curNode.val+"\t");

            if(curNode.left!=null){
                queue.offer(curNode.left);
            }

            if(curNode.right!=null){
                queue.offer(curNode.right);
            }
        }

    }
}
