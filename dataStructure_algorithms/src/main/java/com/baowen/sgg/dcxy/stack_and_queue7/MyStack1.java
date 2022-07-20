package com.baowen.sgg.dcxy.stack_and_queue7;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *  
 *
 * 注意：
 *
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author mangguodong
 * @create 2022-07-20
 */
public class MyStack1<T> {

    public static void main(String[] args) {
        MyStack1<Integer> m = new MyStack1<>();

        m.push(1);
        m.push(2);
        m.push(3);
        m.push(4);
        m.push(5);

        System.out.println(m.pop());
        System.out.println(m.top());

    }

    /**
     *     思路:使用双队列实现栈即
     *     1234 -> q1
     *     5来了怎么办
     *     5先放到q2里面,将q1的全部出队在放到q2里面  ，这样最先出队的就是5 然后是4
     *     然后互换引用
     *
     *
     */

    Queue<T> q1;
    Queue<T> q2;

    public MyStack1() {
       q1 = new LinkedList<>();
       q2 = new LinkedList<>();
    }

    public void push(T t){

        //入队
        q2.offer(t);

        while (!q1.isEmpty()){
            q2.offer(q1.poll());
        }
        Queue<T> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public T pop(){
        return q1.poll();
    }

    public T top(){
        return q1.peek();
    }

    public boolean Empty(){
        return q1.isEmpty();
    }

}
