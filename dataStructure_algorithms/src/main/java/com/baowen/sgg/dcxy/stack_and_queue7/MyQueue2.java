package com.baowen.sgg.dcxy.stack_and_queue7;

import java.util.Stack;

/**
 * @author mangguodong
 * @create 2022-07-24
 */
public class MyQueue2<T> {
    /**
     * 思路:
     *      在MyQueue1的基础上 进行的优化，一样的是利用2次入栈弹栈的 翻转操作
     *
     *      不过 是在 队列的pop方法的时候进行翻转
     *      即：  push 元素都到s1，当第一次 pop 时 , 将s1 弹栈到 s2 ，并且将s2的 最顶层弹栈
     *           假如s2为空，在一次将s1弹栈到s2
     *           重复以上操作
     *
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        MyQueue2<Integer> myQueue = new MyQueue2<>();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    }

    Stack<T> s1;
    Stack<T> s2;

    public MyQueue2() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(T t){
        s1.push(t);
    }

    public T pop(){
        if (s2.isEmpty()){
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }

        return s2.pop();
    }


    public T peek(){
        if (s2.isEmpty()){
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }

        return s2.peek();
    }

    public boolean empty(){
        return s1.isEmpty()&&s2.isEmpty();
    }



}
