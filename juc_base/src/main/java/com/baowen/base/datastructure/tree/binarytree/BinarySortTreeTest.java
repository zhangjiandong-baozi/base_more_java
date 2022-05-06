package com.baowen.base.datastructure.tree.binarytree;

import lombok.Data;
import lombok.Value;

/**
 * @author mangguodong
 * @create 2021-04-10
 */
public class BinarySortTreeTest {

    public static void main(String[] args) {
        //int[] arr = {10,5,15,3,9,12,17};
       // int[] arr = {4,3,6,5,7,8};  //右高左旋
        //int[] arr = {10,8,12,7,9,6};  //左高右旋
        int[] arr = {10,7,12,8,9,6};  //  左节点先左旋，然后在整体右旋

        BinarySortTree sortTree = new BinarySortTree();
        for (int i : arr) {
            sortTree.add(new SortNode(i));
        }

        //sortTree.midForeach();
        System.out.println("sortTree.root.height() = " + sortTree.root.height());//4
        System.out.println("sortTree.root.leftHeight() = " + sortTree.root.leftHeight()); //1
        System.out.println("sortTree.root.rightHeight() = " + sortTree.root.rightHeight());//3
        System.out.println("sortTree.root = " + sortTree.getRoot());

    }
}

class BinarySortTree{

    SortNode root;

    public SortNode getRoot() {
        return root;
    }

    public void add(SortNode node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    public void midForeach(){
        if(root == null){
            System.out.println("二叉树为空");
        }else {
            root.midForeach();
        }
    }



}

@Data
class SortNode{

    int no;
    //String value;
    SortNode left;
    SortNode right;

    public SortNode(int no){
        this.no = no;
       // this.value = value;
    }


    //计算树的高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    //计算左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }
    //计算右子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }

    //左旋转
    public void leftRotation(){
        //创建一个新节点
        SortNode newNode = new SortNode(this.no);
        //新节点的左子树指向原来节点的左子树
        newNode.left = left;
        //新节点的右子树指向右节点的左子树
        newNode.right = right.left;
        //将当前节点的值换成右节点的值
        no = right.no;
        //当前右子树 指向下下个右节点 （即跳一个节点）
        right = right.right;
        //当前节点的左子树指向新节点
        left = newNode;

    }


    public void rightRotation(){
        //创建新节点
        SortNode newNode = new SortNode(no);
        //新节点的右子树指向当前节点的右子树
        newNode.right = right;
        //新节点的左子树指向当前节点左节点的右子树
        newNode.left = left.right;
        //将当前节点的值替换成左子节点的值
        no = left.no;
        //当前节点指向下下个左子数(即跳一个节点,把自己值的节点跳过)
        left = left.left;
        //当前右节点指向新节点
        right = newNode;

    }

    //顺序添加元素
    public void add(SortNode node){
        if(node.no<this.no){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }

        //以下操作来一个节点操作一次

        //当右子树高度减去左子树高度大于1时,左旋
        if(rightHeight()-leftHeight()>1){

            if(right!=null && right.leftHeight()>right.rightHeight()){
                right.rightRotation();
                leftRotation();
            }else{
                leftRotation();
            }
            return; //必须要  ，因为是来一个节点处理一个节点，不需要走下面了
        }

        //当左子树高度大于右子树高度，右旋转
        if(leftHeight()-rightHeight()>1){
            //当左节点的右子树大于左结点的左子树高度，要先对 左结点行左旋，整个树在右旋转
            //不满足上述条件直接右旋转
            if(left != null && left.rightHeight()>left.leftHeight()){
                left.leftRotation();
                rightRotation();
            }else{
                rightRotation();
            }

        }
    }

    /**
     * 中序遍历
     */
    public void midForeach(){

        if(this.left!=null){
            this.left.midForeach();
        }
        System.out.println(this);

        if(this.right != null){
            this.right.midForeach();
        }
    }

}