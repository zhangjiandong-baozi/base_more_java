package com.baowen.sgg.dcxy.string3;

/**
 *给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * 示例 1：
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 示例 2：
 *
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 示例 3：
 *
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *  
 * 提示：
 * 1 <= num1.length, num2.length <= 104
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零

 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-05-21
 */
public class AddStrings1 {

    public static void main(String[] args) {
        String a = "77887";
        String b = "89898";

        System.out.println("addStrings(a,b) = " + addStrings(a, b));
    }

    /**
     * 2数的个位相加 保存在结果中，进位 在 下一个  个位中 加进去
     *
     * 即     77887
     *        89898
     *
     *            7
     *            8
     *           15    ==》 5保存   1 在下一个 个位中相加
     *
     *          8
     *         (9+1)
     *        1 8       ==> 8 保存   1在下一个个位中相加
     * @param a
     * @param b
     * @return
     */
    public static String addStrings(String a, String b) {

        StringBuffer result = new StringBuffer();
        // 从低位往高位加
        int i = a.length() - 1;
        int j = b.length() - 1;
        // 代表进位
        int carry = 0;
        // 12345678 + 78   数小的要补0
        // 5+5 在下一次个位相加时  进位不为0 时 个位为0时 都要变成0
        while (i >= 0 || j >= 0 || carry != 0) {

            int s1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int s2 = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = s1 + s2 + carry;

            result.append(sum % 10);
            carry = sum / 10;

            i--;
            j--;
        }
        return result.reverse().toString();
    }

}
