package com.baowen.sgg.dcxy.arrays;

/**
 *给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 *
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author mangguodong
 * @create 2022-05-17
 */
public class RotateImage04 {

    public static void main(String[] args) {

        int [][] image1 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        int [][] image2 = {
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16}
        };

//        rotateImage(image1);
//        print2Arrays(image1);
//        rotateImage(image2);
//        print2Arrays(image2);

        rotateImage2(image1);
        print2Arrays(image1);
        rotateImage2(image2);
        print2Arrays(image2);

    }


    /**
     * 思路求解：数学方法
     * 转置矩阵,每行前后翻转
     *
     * 如何转置矩阵:  沿着对角线对称的2点互换位置，，即循环对角线上半部分的数据，进行交换
     *
     * 如何翻转: 每行循环一半
     *
     * @param matrix
     */
    public static void rotateImage(int[][] matrix){

        int n = matrix.length;
        // 转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int  temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //每行前后翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                int  temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }

    //旋转四分之一的矩阵(假如是左上矩阵)，当前位置是上一个旋转点就能得出结果
    public static void rotateImage1(int[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < n/2+n%2; i++) {
            for (int j = 0; j < n/2; j++) {
                int [] temp = new int[4];

                // 将某数旋转一次后发现  col = newrow  、  row+newcol = n-1
                //将旋转的4个数存起来
                int row = i;
                int col = j;
                for (int k = 0; k < 4; k++) {
                    temp[k] = matrix[row][col];
                    // 这个x特别重要 因为接下来的row 的值会被覆盖
                    int x = row;
                    row = col;
                    col = n-1-x;
                }

                //取上一个旋转的数放在当前位置
                // 例如  即 image2中的14放到2哪里
                for (int k = 0; k < 4; k++) {
                    matrix[i][j] = temp[(k+3)%4];
                    int x = row;
                    row = col;
                    col = n-1-x;
                }

            }
        }
    }

    //2O(n平方)的改进
    public static void rotateImage2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n+1)/2; i++) {
            for (int j = 0; j < n / 2; j++) {

                //将某数旋转一次后发现  col = newrow  、  row+newcol = n-1
                //当前位置获取旋转后的前一个位置  即 image2中的14放到2哪里
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n-1-j][i];
                    matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                    matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
//                    matrix[j][n-1-i] = matrix[i][j];
                    matrix[j][n-1-i] = temp;
            }
        }
    }

    public static void print2Arrays(int[][] image){
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image.length; j++) {
                System.out.print(image[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
