package com.baowen.sgg.dcxy.binary_tree9;

/**
 * 判断一个树是不是AVL树
 * @author mangguodong
 * @create 2022-08-13
 */
public class BalanceBinaryTree2 {

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        //System.out.println(isBalanceBinaryTree(node1));
        System.out.println(isBalanceBinaryTree(node1));


    }


    /**
     * 后续遍历
     * 思路:
     *      先序遍历时，每次计算自己的高度，还要重复计算子节点的高度， 其实在底层的方法栈中已经算过 ，重复算导致时间复杂度变高
     *
     *      每次算当前树的高度时，同时返回此树是不是平衡二叉树
     *
     * @param root
     * @return
     */
    public static boolean isBalanceBinaryTree1(TreeNode root){
        return heightBalance(root)>-1;
    }


    /**
     * 返回树的高度且判断是否为平衡二叉树
     *
     * 是的话返回高度，否则返回-1
     * 思路:
     *      方法弹栈的时候处理 即上层树得到一个结果需要依赖下层树的计算结果
     *
     *      代码顺向思维写
     *
     * @param root
     * @return
     */
    public static int heightBalance(TreeNode root){

        if(root==null) return 0;

        // 代码顺向思维写   即 如何判定当前树是不是平衡二叉树
        int leftHeight = heightBalance(root.left);
        int rightHeight = heightBalance(root.right);

        //向上层树返回的时候，下层的左子树不平衡，自己的左子树肯定也不平衡
        if(leftHeight==-1||rightHeight==-1||Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }

        //计算树的高度  叶子节点的高度为1，后续随着树的高度依次递增
        return Math.max(leftHeight,rightHeight)+1;

    }

    /**
     * 先序遍历
     *
     * 思路:
     *      计算自己左子树和右子树的高度差是不是相差小于等于1，是返回true，否则返回false
     *          且递归调用它的左子树和右子树 是否高度差是否差1
     *
     * 时间复杂度:
     *      一个节点自己要计算高度,它的左右子节点也需要计算高度，即复杂度为树的高度 logn
     *      对树的N个节点来说就是 O(nlogn)
     *
     * 空间复杂度：调用方法栈的深度(树的深度) logn  ( 即每次方法调用就处理了2个元素) 需要多少次处理呢 当然是logn次
     *
     * @return
     */
    public static boolean isBalanceBinaryTree(TreeNode root){
        if(root ==null) return true;

        return Math.abs(height(root.left)-height(root.right))<=1
                && isBalanceBinaryTree(root.left)
                && isBalanceBinaryTree(root.right);

    }

    public static int height(TreeNode root) {
        if(root==null)return 0;

        //自己的左子树高度和右子树高度 最大的在加上根的高度
        return Math.max(height(root.left),height(root.right))+1;

    }




}
