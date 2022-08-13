package com.baowen.sgg.dcxy.binary_tree9;

/**
 * #226
 * 反转二叉树
 * @author mangguodong
 * @create 2022-08-12
 */
public class InvertBinaryTree1 {

    /**
     *              4
     *         2          7
     *      1    3     6     9
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);

        node4.left = node2;
        node4.right = node7;
        node2.left = node1;
        node2.right = node3;
        node7.left = node6;
        node7.right = node9;

        invertBinaryTree2(node4);
        //PrintTreeNode.PrintLevelOrder(node4);
        PrintTreeNode.PrintLevelOrder(node4);



    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public static void invertBinaryTree1(TreeNode root){
        if(root.left==null && root.right==null)return;


        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertBinaryTree1(root.left);
        invertBinaryTree1(root.right);
    }


    /**
     * 后序遍历
     *
     * @param root
     */
    public static void invertBinaryTree2(TreeNode root){
        if(root.left==null && root.right==null)return;



        invertBinaryTree2(root.left);
        invertBinaryTree2(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
