package com.baowen.base.moretest;

/**
 * @author mangguodong
 * @create 2021-04-11
 */
public class AVLTreeTest {
    public static void main(String[] args) {

        int[] arr = {10,7,12,6,5,8};
        AVLTree tree = new AVLTree();
        for(int i:arr){
            tree.add(new SortNode(i));
        }

        tree.midForeach();
        System.out.println("tree.root.height() = " + tree.root.height());
        System.out.println("tree.root.leftHeight() = " + tree.root.leftHeight());
        System.out.println("tree.root.rightHeight() = " + tree.root.rightHeight());
    }
}

class AVLTree{

    SortNode root;

    public void add(SortNode sortNode){
        if(root==null){
            root = sortNode;
        }else {
            root.add(sortNode);
        }
    }

    public void midForeach(){
        if(root!=null){
            root.midForeach();
        }else {
            System.out.println("二叉树为空");
        }
    }

}

class SortNode{
    int no;
    SortNode left;
    SortNode right;

    public SortNode(int no){
        this.no = no;
    }

    //求树的高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    //求左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }

    //求右子树的高度
    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }

    //右高左旋转
    public void leftRotation(){
        SortNode newNode = new SortNode(no);

        newNode.left = left;

        newNode.right = right.left;

        no = right.no;

        right = right.right;

        left = newNode;
    }

    //左高右旋转
    public void rightRotation(){
        SortNode newNode = new SortNode(no);
        newNode.right = right;
        newNode.left = left.right;
        no = left.no;
        left = left.left;
        right = newNode;
    }

    public void add(SortNode node){
        if(node.no<no){
            if(left == null){
                left = node;
            }else {
                left.add(node);
            }
        }else {
            if (right == null){
                right = node;
            }else {
                right.add(node);
            }
        }

        if(rightHeight()-leftHeight()>1){
            if(right!=null && right.leftHeight()>right.rightHeight()){
                right.rightRotation();
                leftRotation();
            }else {
                leftRotation();
            }
            return;
        }

        if(leftHeight()-rightHeight()>1){
            if(left!=null && left.rightHeight()>left.leftHeight()){
                left.leftRotation();
                rightRotation();
            }else{
                rightRotation();
            }

        }
    }

    public void midForeach(){

        if(left!=null){
            left.midForeach();
        }
        System.out.println(this);

        if(right!=null){
            right.midForeach();
        }
    }

    public String toString(){
        return "TreeNo: "+no;
    }
}
