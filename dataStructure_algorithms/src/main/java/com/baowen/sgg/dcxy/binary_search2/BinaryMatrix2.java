package com.baowen.sgg.dcxy.binary_search2;

/**
 * 搜索二维矩阵
 * <p>
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author mangguodong
 * @create 2022-05-18
 */
public class BinaryMatrix2 {

    public static void main(String[] args) {
        int[][] ints = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int key = 30;
        System.out.println("BinaryMatrix(ints) = " + BinaryMatrix(ints, 30));
    }

    /**
     * 我刚开始的思路是把这个变成一维数组，然后二分查找，那么需要遍历二维数据且需要new 一个m*n 的一维数组存数
     * 以上操作空间复杂度和时间复杂度都很大
     * <p>
     * <p>
     * 输入的 m x n 矩阵可以视为长度为 m x n的有序数组
     * 一维有序数组的下标越大值越大，而此二维数组下一行比上一行大，也可以同样处理，需要通过mn求下标index
     * 通过数学变换将二维数据看成一维数组处理
     * 行列坐标为(row, col)的元素，展开之后索引下标为idx = row * n + col；反过来，对于一维下标为idx的元素，对应二维数组中的坐标就应该是：
     * row = idx / n;  col = idx % n;
     * <p>
     * 例如 一个 4行3列的 数组， 他第二行第一列，怎么用mn来表示它的坐标呢？或者mn和坐标有什么关系呢？
     * <p>
     * 例如 30（2,1）   ==》       mn = 4*row+col = 4*2+1   即 走过 2行加1列  即=》 row = mn/n   col = mn%n
     *
     * @param matrix
     * @return
     */
    public static Boolean BinaryMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        if (target < matrix[0][0] && target > matrix[m - 1][n - 1]) {
            return false;
        }

        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (target < matrix[mid / n][mid % n]) {
                right = mid - 1;
            } else if (target > matrix[mid / n][mid % n]) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
