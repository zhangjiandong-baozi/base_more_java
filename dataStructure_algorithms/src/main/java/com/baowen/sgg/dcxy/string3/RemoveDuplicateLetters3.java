package com.baowen.sgg.dcxy.string3;

import javax.swing.text.Position;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Stack;

/**
 * 316
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-06-26
 */
public class RemoveDuplicateLetters3 {

    /**
     * 做题的心路历程
     *
     * 首先解决了 去重问题，保留原始位置 未得到解决
     *
     * 假如要满足以上2个条件，有没有可以解决的策略或者办法？
     *      我想了想，没想出来，然后直接看视频了 （为什么  自己不多想想  很多时候肉眼能看出的答案  是可以总结规律和结论的  为什么不多想想和思考）
     *      ==>
     *      看了视频后，老师直接给了一个解题策略，然后就编码了
     *          策略是   先找到最小的那个 字母， 在它之前出现的字母，在它之后出现了，可以将前面的重复去掉，如果在它之后没有出现则不能去重，然后对比字典序
     *
     *       // 当你 搞不懂解题思路时，你代入一个例子（特例），去还原它整个的解题过程 （所以算法有点像解数学题），即看程序是怎么解决的
     *
     * @param args
     */
    public static void main(String[] args) {

        String s = "cbacdcbc";
        System.out.println(RemoveDuplicateLetters(s));

    }

    /**
     *
     * 栈实现
     * 思路:
     *      当确定好最最左侧字母时，  如何确定第二左的字母？  cdeadaec
     *          即当此字母 比之前字母字典序要小，且在它之前的字母在之后重复出现，可以将其移动到前面
     *
     *       即利用栈存结果，定义好进栈出栈的 规则，最终结果就是栈内元素
     *
     * 时间复杂度分析：
     *
     * for 里面嵌套while   一般来说是n的平方，但是  while  对于 外层的for  的 一个字母  顶多  一次入栈 一次弹栈，所以整体来看还是On 的时间复杂度
     * 因为 while 里面的 字母 不会压栈多次也不出弹栈多次。  和外层的i 不是 乘积关系 是 常数*i
     *
     *
     * @param str
     * @return
     */
    public static String RemoveDuplicateLetters(String str){

        if(str.length()==0){
            return "";
        }

        //存放结果
        Stack<Character> stack = new Stack<>();

        //定义一个map,记录每个字母最后出现的位置
        HashMap<Character, Integer> map = new HashMap<>();

        //由于stack 存最终结果，后来的重复元素没必要压栈，定义一个set 判断元素是否压栈？
        HashSet<Character> set = new HashSet<>();

        //遍历数组存每个字母的最后位置
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i),i);
        }

        //遍历数组，进行入栈出栈操作，保存结果到栈中
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(!set.contains(c)){
                //弹栈 当前字母比栈顶字母小，且在当前字母后栈顶元素还重复了,需要将栈顶元素弹栈，此字母压栈，同时将set中的栈顶元素删除，方便后续栈顶元素在入栈
                //重复上述操作，保证字典序最小
                while(!stack.isEmpty() && c<stack.peek() && map.get(stack.peek())>i){
                    set.remove(stack.pop());
                }
                //入栈 即 首次出现字母入栈
                stack.push(c);
                set.add(c);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : stack) {
            stringBuilder.append(character.charValue());
        }

        return stringBuilder.toString();
    }


    /**
     * 贪心策略（空间换时间） + 递归
     *
     *首先遍历数组，获取所有字符的出现次数 存入 数组或者集合中
     *
     * 接下来 找最左边的字母，即遍历 字符创，遇到一个字母，字母count数减一， 字母减去后如果为0 ，说明当前假定的position是最左侧的
     * 接着去除本字母，开始从它的右边 继续递归调用
     *
     * // 找position 和 count -- 是 2个事 不是强关联
     *         // 例如 cdeadaec 中 position 是 第一个a位置，当时当 第二个d变成哦时，position是 第一个a的位置
     *         // 当你 搞不懂解题思路时，你代入一个例子（特例），去还原它整个的解题过程 （所以算法有点像解数学题），即看程序是怎么解决的
     *
     * @param str
     * @return
     */
    public static String RemoveDuplicateLetters2(String str){

        if(str.length()==0){
            return "";
        }

        int[] chars = new int[26];
        for (int i = 0; i < str.length(); i++) {
            chars[str.charAt(i)-'a']++;
            //++chars[str.charAt(i)-'a'];
        }

        //假定position是最左的字母
        int position = 0;

        // cdeadaec
        // 找position 和 count -- 是 2个事 不是强关联
        // 例如 cdeadaec 中 position 是 第一个a位置，当时当 第二个d变成哦时，position是 第一个a的位置
        // 当你 搞不懂解题思路时，你代入一个例子（特例），去还原它整个的解题过程 （所以算法有点像解数学题），即看程序是怎么解决的
        for (int i = 0; i < str.length(); i++) {

            if(str.charAt(i)<str.charAt(position)){
                position = i;
            }
//            chars[str.charAt(i)-'a']--;
            if(--chars[str.charAt(i)-'a'] == 0 ){
                break;
            }
        }

        return str.charAt(position)+RemoveDuplicateLetters2(str.substring(position+1).replaceAll(str.charAt(position)+"",""));

    }


    /**
     * 贪心策略(暴力法) + 递归
     *
     * 策略是   先找到最小的那个 字母， 在它之前出现的字母，在它之后出现了，可以将前面的重复去掉，如果在它之后没有出现则不能去重，然后对比字典序
     * @param str
     * @return
     */
    public static String RemoveDuplicateLetters1(String str){

        if(str.length()==0){
            return "";
        }

        // position 是我们要找的最左侧的字母
        int position = 0;
        //循环遍历找到 最左侧的字母
        //即循环找到较小的position，直到position 的字母是最小的
        for (int i = 0; i < str.length(); i++) {
            // 也即只有当前字母比已经找到的position位置的字母要小，才有资格继续判断
            if(str.charAt(i)< str.charAt(position)){
                // 定义一个布尔变量，表示当前i位置的字母是否可以替换position位置的字母
                boolean isReplaceable = true;

                //判断i 之前的字母是否会在i之后出现
                //一般从0 开始遍历，也可以从position位置遍历,因为position之前的字母肯定在它之后出现了
                for (int j = position; j < i; j++) {

                    boolean isDuplicate = false;

                    for (int k = i+1; k < str.length() ; k++) {
                        if(str.charAt(j) == str.charAt(k)){
                            isDuplicate = true;
                            break;
                        }
                    }

                    //如果任意字母不重复出现，就不能替换当前position，后面的字母不用判断
                    if(!isDuplicate){
                        isReplaceable = false;
                        break;
                    }
                }
                if(isReplaceable){
                    position = i;
                }

            }
        }

        // 遍历结束 找到做左侧的 字母
        // 然后对position右边的 子串 用此方法递归调用 找到  余下的子串
        return str.charAt(position) + RemoveDuplicateLetters1(str.substring(position+1).replaceAll(str.charAt(position)+"",""));
    }

    // 实现了 将字母 去重，这一步
    public static String RemoveDuplicateLettersonestep(String str){

        char[] chars = str.toCharArray();

        LinkedHashSet<Character> set = new LinkedHashSet<Character>();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {

            set.add(chars[i]);
        }

        for (Character character : set) {
            result.append(character);
        }

        return result.toString();

    }




}
