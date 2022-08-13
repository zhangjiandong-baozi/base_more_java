package com.baowen.sgg.dcxy.stack_and_queue7;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-07-24
 */
public class ValidParentheses3 {
    /**
     * 思路：
     *      我原来的思路是  左括号的map的value =  右括号map的key
     *
     *      经典思路是 栈 后进先出的匹配
     *
     * @param args
     */

    public static void main(String[] args) {

        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";
        String s5 = "{[]}";

        System.out.println(ValidParentheses(s1));
        System.out.println(ValidParentheses(s2));
        System.out.println(ValidParentheses(s3));
        System.out.println(ValidParentheses(s4));
        System.out.println(ValidParentheses(s5));

    }

    public static boolean ValidParentheses(String s){
        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='('){
                stack.push(')');
            }else if(c=='{'){
                stack.push('}');
            }else if(c=='['){
                stack.push(']');
            }else {
                if(stack.isEmpty()||c!=stack.pop()){
                    return false;
                }
            }
        }
        //"{()"   这种情况 括号没有闭合
        return stack.isEmpty();

    }
}
