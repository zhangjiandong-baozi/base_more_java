package com.baowen.base.datastructure.tree.binarytree;

import lombok.Data;



/**
 * @author mangguodong
 * @create 2021-04-10
 */
public class BinaryTreeTest {

    /**
     *
     * 创建二叉树，并实现它的前序、中序、后续遍历
     *
     */
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        TreeNode root = new TreeNode(1,"t1");
        TreeNode t2 = new TreeNode(2,"t2");
        TreeNode t3 = new TreeNode(3,"t3");
        TreeNode t4 = new TreeNode(4,"t4");
        TreeNode t5 = new TreeNode(5,"t5");
        TreeNode t6 = new TreeNode(6,"t6");
        TreeNode t7 = new TreeNode(7,"t7");

        /**
         *                         1
         *                    2          3
         *                4     5     6    7
         */
        tree.setRoot(root);
        root.setLeft(t2);
        root.setRight(t3);
        t2.setLeft(t4);
        t2.setRight(t5);
        t3.setLeft(t6);
        t3.setRight(t7);

       // tree.preOrder(); 前序遍历
      //  tree.midOrder();中序遍历
       // tree.endOrder();后续遍历

       // TreeNode result = tree.preTreeNodeSearch(6);  //前序查找比较  6次比较
       // System.out.println("result = " + result);

        //System.out.println(tree.midTreeNodeSearch(6)); //中序查找比较 5次

        System.out.println(tree.endTreeNodeSearch(6)); //后序查找比较 4次


    }



}


class BinaryTree{
    private TreeNode root;
    public BinaryTree (){

    }
    public BinaryTree (TreeNode root){
        this.root = root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void midOrder(){
        if(this.root!=null){
            this.root.midOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void endOrder(){
        if(this.root!=null){
            this.root.endOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public TreeNode preTreeNodeSearch(int no){

        if(this.root!=null){
            return root.preTreeNodeSearch(no);
        }else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    public TreeNode midTreeNodeSearch(int no){

        if(this.root!=null){
            return root.midTreeNodeSearch(no);
        }else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    public TreeNode endTreeNodeSearch(int no){

        if(this.root!=null){
            return root.endTreeNodeSearch(no);
        }else {
            System.out.println("二叉树为空");
            return null;
        }
    }

}

@Data
class TreeNode{
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序遍历
     * 先输出自己，在递归遍历左边，在递归遍历右边
     *抽象模型可以看高度为1 的二叉树是怎样的
     *对于一个高度为1的二叉树  ，先输出自己，在输出左子树节点的值，在输出右子节点的值
     *如下图 是1->2->3
               1
             2   3
     *对于高度为2的满二叉树,先输出自己，在递归遍历左边，在递归遍历右边
     *如下图  1->2->4->5->3->6->7
                        1
                   2          3
               4     5     6    7
     */
    public void preOrder(){
        System.out.println(this);

        if(this.left != null){
            this.left.preOrder();
        }

        if(this.right!=null){
            this.right.preOrder();
        }
    }


    /**
     * 中序遍历
     *
     * 先递归遍历左子树，在输出自己，在递归遍历右子树
     *
     * 抽象模型可以看高度为1 的二叉树是怎样的
     * 对于一个高度为1的二叉树
     *如下图 是2->1->3
     *           1
     *         2   3
     *
     *  ··对于高度为2的满二叉树,先输出左子树所有的值，在输出自己，在输出右子树所有的值
     *  如下图  4->2->5->1->6->3->7
     *                    1
     *               2          3
     *           4     5     6    7
     *
     */
    public void midOrder(){
        if(this.left!=null){
            this.left.midOrder();
        }
        System.out.println(this);

        if(this.right!=null){
            this.right.midOrder();
        }
    }

    /**
     * 后续遍历
     *
     *
     * 先递归遍历左子树,接下来遍历右子树，然后输出自己
     *
     * 抽象模型
     * ··对于高度为1的二叉树 ,先输出左边的值，在输出右边的值,在输出父结点
     * 如下图 是2->3->1
     *              1
     *            2   3
     * ··对于高度为2的满二叉树,先输出左子树上所有的值，在输出右子树上所有的值，在输出根节点
     *   如下图  4->5->2->6->7->3->1
     *              1
     *         2          3
     *     4     5     6    7
     */

    public void endOrder(){
        if(this.left!=null){
            this.left.endOrder();
        }

        if(this.right!=null){
            this.right.endOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     * 先比较当前节点是不是，是则返回，
     * 没查到，向左递归遍历查找,查找到后返回，
     *没查到，向右子树递归查找， 最后有没有值都返回。
     *
     * @param no
     * @return
     */
    public TreeNode preTreeNodeSearch(int no){

        //先比较当前节点是不是，是则返回，
        System.out.println("前序查找比较了");
        if(this.no == no){
            return this;
        }

        //当前节点不是,向左子树递归查找,查找到后返回
        TreeNode resultNode = null;
        if(this.left != null){
            resultNode = this.left.preTreeNodeSearch(no);
        }
        if(resultNode !=null){
            return resultNode;
        }

        // 递归左子树没有查到，则向右子树递归查找
        if(this.right!=null){
            resultNode = this.right.preTreeNodeSearch(no);
        }

        //不管有没有找到，都返回
        return resultNode;

    }


    /**
     * 中序查找
     * 判断左节点是否为空，递归查找左子树，
     * 如果找到节点，就返回，
     *
     * 比较当前节点是不是和查找节点一致，是则返回
     *
     * 没找到，判断右结点是否为空，不为空，递归查找右子树
     * 最后不管查没查到，都进行返回
     *
     * @param no
     * @return
     */
    public TreeNode midTreeNodeSearch(int no){

        TreeNode resultNode = null;

        //判断左节点是否为空，递归查找左子树，
        if(this.left!=null){
            resultNode = this.left.midTreeNodeSearch(no);
        }
        //如果找到节点，就返回 (下层栈帧)
        if(resultNode!=null){
            return resultNode;
        }

        //判断是不是要找的节点 是则返回 (上层栈帧-当前栈帧)
        System.out.println("中序查找比较了");
        if(this.no == no){
            return this;
        }

        //判断右节点是否为空,递归查找右子树
        if(this.right!=null){
            resultNode = this.right.midTreeNodeSearch(no);
        }
        return resultNode;
    }

    /**
     * 后续查找
     *
     * 先判断左结点是否为空，不为空则递归查找左子树，找到了就返回结果(下层栈帧)
     * 没找到，判断右结点是否为空，不为空则递归查找右子树，找到了就返回结果(下层栈帧)
     * 判断当前节点和查找节点是否一致，是则返回当前节点。(上层栈帧-当前栈帧)
     * 不管找没找到结果节点都要返回。
     *
     *
     * @param no
     * @return
     */
        public TreeNode endTreeNodeSearch(int no){

            TreeNode resultNode = null;
            //先判断左结点是否为空，不为空则递归查找左子树，找到了就返回结果(下层栈帧)
            if(this.left!=null){
                resultNode = this.left.endTreeNodeSearch(no);
            }

            if(resultNode!=null){
                return resultNode;
            }

            //没找到，判断右结点是否为空，不为空则递归查找右子树，找到了就返回结果(下层栈帧)
            if(this.right!=null){
                resultNode = this.right.endTreeNodeSearch(no);
            }
            if(resultNode!=null){
                return resultNode;
            }
            //判断当前节点和查找节点是否一致，是则返回当前节点。(上层栈帧-当前栈帧)
            System.out.println("后续查找比较了");
            if (this.no == no){
                return this;
            }
            return resultNode;
    }

}
