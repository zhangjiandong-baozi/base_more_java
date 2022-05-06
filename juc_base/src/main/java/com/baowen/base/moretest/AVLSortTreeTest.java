package com.baowen.base.moretest;

import lombok.Data;

/**
 * @author mangguodong
 * @create 2021-04-11
 */
public class AVLSortTreeTest {

    public static void main(String[] args) {

       // int[] arr = {10,7,12,6,8,5};

       // int [] arr = {10,7,12,6,5,8};

       // int[] arr = {10,7,12,11,13,17};
        int [] arr = {10,7,12,8,9,6};

        AVLSortTree tree = new AVLSortTree();
        for (int i : arr) {
            tree.add(new TreeNode(i));
        }

        tree.midForeach();

        System.out.println("tree.root.height() = " + tree.root.height());
        System.out.println("tree.root.leftHeight() = " + tree.root.leftHeight());
        System.out.println("tree.root.rightHeight() = " + tree.root.rightHeight());
        System.out.println(tree.root);
    }


}


class AVLSortTree{

    TreeNode root;

    public void add(TreeNode treeNode){
        if(root == null){
            root = treeNode;
        }else {
            root.add(treeNode);
        }
    }

    public void midForeach(){
        if(root != null){
            root.midForeach();
        }else {
            System.out.println("二叉树为空");
        }
    }

}


class TreeNode{
    int no;
    TreeNode left;
    TreeNode right;

    public TreeNode(int no){
        this.no = no;
    }

   //计算树的高度的方法
    public int height(){
       return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    //计算左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }


    //左高右旋
    public void rightRotation(){
        TreeNode newNode = new TreeNode(no);

        newNode.right = right;

        newNode.left = left.right;

        no = left.no;

        left = left.left;

        right = newNode;
    }

    //右高左旋
    public void leftRotation(){
        TreeNode newNode = new TreeNode(no);

        newNode.left = left;

        newNode.right = right.left;

        no = right.no;

        right = right.right;

        left = newNode;

    }


    //按顺序添加，左小右大
    public void add(TreeNode node){
        if(node.no<no){
            if(left == null){
                left = node;
            }else{
                left.add(node);
            }
        }else{
            if(right == null){
                right = node;
            }else {
                right.add(node);
            }
        }



        //右高左旋
        if(rightHeight()-leftHeight()>1){
            //如果右节点的左子树高于右节点的左子树 需要先将右节点右旋，在整体左旋
            if(right!=null && right.leftHeight()>right.rightHeight()){
                right.rightRotation();
                leftRotation();
            }else{
                leftRotation();
            }
            return;  //因为是来一个操作一下，下面的步骤 就不必要走了
        }

        //左高右旋
        if(leftHeight()-rightHeight()>1){
            //如果左结点的右子树高度大于左结点左子树高度，需要先将左节点左旋，在整体右旋
            if(left!=null && left.rightHeight()>left.leftHeight()){
                left.leftRotation();
                rightRotation();
            }else{
                rightRotation();
            }

        }

    }

    //中序遍历
    public void midForeach(){
        if(left!=null){
            left.midForeach();
        }
        System.out.println(this);
        if(right!=null){
            right.midForeach();
        }
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                '}';
    }
}
