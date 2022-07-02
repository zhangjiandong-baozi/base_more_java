package com.baowen.sgg.dcxy.string3;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author mangguodong
 * @create 2022-05-21
 */
public class MultiplyStrings2 {

    public static void main(String[] args) {

        String a = "123";
        String b = "456";

        String c = "999";
        String d = "999";

        System.out.println(MultiplyStrings(c, d));
    }

    //A数与B数相乘
    //A 数与B数的每一位 相乘   的结果相加 (A的每一位数 与B的某位数 相乘)
    public static String MultiplyStrings1(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        String result = "0";
        //A 数与B数的每一位 相乘   ，从最后一位数开始相乘
        for (int i = num2.length() - 1; i >= 0; i--) {

            //保留结果乘积
            StringBuffer stringBuffer = new StringBuffer();
            int carry = 0;

            int n2 = num2.charAt(i) - '0';

            for (int j = 0; j < num2.length() - 1 - i; j++) {
                stringBuffer.append("0");
            }
            //从个位开始遍历num1中的每一位，与n2相乘，并叠加
            for (int j = num1.length() - 1; j >= 0; j--) {

                int n1 = num1.charAt(j) - '0';
                int tmp = n1 * n2 + carry;

                stringBuffer.append(tmp % 10);
                carry = tmp / 10;
            }

            // 所有数位乘法计算完毕，如果有进位，需要将进位单独作为一位保存下来
            if (carry != 0) {
                stringBuffer.append(carry);
            }

            //利用2数相加方法，叠加A数与B每一位的结果
            result = AddStrings1.addStrings(result, stringBuffer.reverse().toString());

        }

        return result;
    }


    /**
     * //优化
     * // 123*45  < 1000*100(100000)  即 ab相乘位数 只能是 5位数（a的位数+b的位数）
     * <p>
     * // 又最小位的  3*5 = 15  只能放在最低位 15   那么 2*5 呢  1*5呢  1*4  呢 这些数  应该放在哪些位数上呢
     * <p>
     * 123
     * 45
     * ------------------------------
     * 15
     * 10
     * 05
     * 12
     * 08
     * 04
     * <p>
     * <p>
     * 总位数 是 l1(3) + l2(2)  =5
     * 对于a和b  如何补0     l1 -1-i  (即  123 中的2 与别的数相乘 结果都要补 （3-1-1） 个0) 和 l2-1-j
     * <p>
     * l1+l2(多少位数) - （  l1-1-i） -(l2-1-i) = i+j+2
     * <p>
     * 又 索引是从0 开始的    0,1,,,i+j+1   ,所以 i*j 的最低位数是 i+j+1，它的进位是i+j
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String MultiplyStrings(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        // 定义一个数组，保存计算结果的每一位
        int[] resultArray = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {

            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {

                int n2 = num2.charAt(j) - '0';
                int tmp = n1 * n2;
                int sum = tmp + resultArray[i + j + 1];

                resultArray[i + j + 1] = sum % 10;
                //注意还要加上他之前的
                resultArray[i + j] += sum / 10;

            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        //判断最高位是不是 0
        int start = resultArray[0] == 0 ? 1 : 0;
        for (int j = start; j < resultArray.length; j++) {
            stringBuffer.append(resultArray[j]);
        }
        return stringBuffer.toString();
    }


}
