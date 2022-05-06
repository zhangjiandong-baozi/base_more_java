package com.baowen.base.moretest;

/**
 * @author mangguodong
 * @create 2022-04-15
 */
public class ParamTest {

    public static void main(String[] args) {

        Student student = new Student();

        Student s = new Student(99,"pp",18,"F");

        student.t1(s);
        student.t2(s);
        student.t3(s);

        System.out.println("s = " + s);

    }


}
