package com.baowen.sgg.dcxy.binary_tree9;

import javax.lang.model.element.VariableElement;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;

/**
 * 验证二叉搜索树
 * @author mangguodong
 * @create 2022-08-13
 */
public class ValidateBST3 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        //System.out.println(isBST(node1));

        ValidateBST3 bst3 = new ValidateBST3();
        System.out.println(bst3.isBST1(node1));

    }

    public ArrayList<Integer> arrayList = new ArrayList<Integer>();

    /**
     * 对于一个二叉搜索树，中序遍历出来后是排好序的数组
     * 思路：
     *      中序遍历二叉树，放入list中，遍历list，如果前面的数大于等于后面的数，则不是二叉搜索树
     *
     *时间复杂度：2次遍历所有元素  2倍 O(n)
     * 空间复杂度：
     *      arraylist 存 O(n)    方法调用栈空间logn   取较大值O(n)
     *
     *
     * @param root
     * @return
     */
    public  boolean isBST1(TreeNode root){

        inOrder(root);

        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i)>=arrayList.get(i+1)){
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历 将节点加入list中
     * @param root
     */
    private void inOrder(TreeNode root) {
        if(root==null)return;

        inOrder(root.left);
        arrayList.add(root.val);
        inOrder(root.right);

    }


    /**
     * 先序遍历
     * 思路：
     *      左边的都比它小，右边的都比它大
     *
     *时间复杂度： 对于每个n都进行了一次判断 时间复杂度O(n)
     *
     * 空间复杂度： 方法的调用 logn
     *
     * @param root
     * @return
     */
    public static boolean isBST(TreeNode root){
        if(root==null)return true;

        return validator(root,null,null);


    }

    /**
     *
     * 先序遍历
     *
     *  所有左节点小于首根的值   所有右节点大于首根的值
     *
     *时间复杂度：
     *
     * @param root
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean validator(TreeNode root,Integer lowerBound,Integer upperBound){
        if(root==null) return true;

        System.out.println("root.val = " + root.val+" lowerBound= "+ lowerBound +" upperBound="+upperBound);
        if(lowerBound!=null && root.val<=lowerBound){
            return false;
        }

        if(upperBound!=null && root.val>=upperBound){
            return false;
        }

        return validator(root.left,lowerBound,root.val)&&validator(root.right,root.val,upperBound);
    }

    /**
     * 思路：
     *      先序遍历
     *
     *          因为51749 遍历就是bst 实际上它不是
     *
     * @param root
     * @return
     */
//    public static boolean isBST(TreeNode root){
//
//        if(root.left==null && root.right==null && root!=null)return true;
//
//        return root.val>root.left.val && root.val<root.right.val&&isBST(root.left)&&isBST(root.right);
//
//    }

}
