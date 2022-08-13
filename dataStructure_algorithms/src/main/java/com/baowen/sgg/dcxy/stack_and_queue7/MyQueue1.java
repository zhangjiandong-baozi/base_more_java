package com.baowen.sgg.dcxy.stack_and_queue7;

import java.util.Stack;

/**
 * 实现 MyQueue 类：
 *
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * 说明：
 *
 * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 1, 1, false]
 *
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author mangguodong
 * @create 2022-07-24
 */
public class MyQueue1<T> {
    /**
     * 思路:
     *  使用2个栈进行翻转，实现队列的先进先出
     *  即
     *
     *      栈1
     *
     *      1
     *      2
     *
     * 来了3之后   先把栈1元素弹到栈2，把3压入栈3，然后再把 栈2弹到栈1
     *
     *      栈1        栈2                栈1    栈2
     *                          ==>       1
     *                  2                 2
     *      3           1                 3
     *
     *
     */

    public static void main(String[] args) {

        MyQueue1<Integer> myQueue = new MyQueue1<>();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    }

    Stack<T> s1;
    Stack<T> s2;
    public MyQueue1() {
        s1 = new Stack<T>();
        s2 = new Stack<T>();
    }

    public void push(T t){

        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }

        s1.push(t);

        while (!s2.isEmpty()){
            s1.push(s2.pop());
        }

    }

    public T pop(){
        return s1.pop();
    }

    public T peek(){

        return s1.peek();
    }

    public boolean empty(){
        return s1.isEmpty();
    }


}
