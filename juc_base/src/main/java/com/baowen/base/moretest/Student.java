package com.baowen.base.moretest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

/**
 * @author mangguodong
 * @create 2022-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    public Integer id;

    public String name;

    public Integer age;

    public String sex;



    public void t1(Student s){

        s = new Student(1,"t1",1,"M");
        System.out.println(s);
    }

    public void t2(Student s){
        s = new Student(2,"t2",1,"M");
        System.out.println(s);
    }

    public void t3(Student s){
        s = new Student(3,"t3",1,"M");
        System.out.println(s);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
