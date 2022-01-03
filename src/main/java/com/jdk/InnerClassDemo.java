package com.jdk;

/**
 * @author: huan.liu
 * @since : 2022-01-03
 */
public class InnerClassDemo {

    private String name;

    private static int age = 5;

    class InnerClass {

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setAge(int age) {
        InnerClassDemo.age = age;
    }

    public static void main(String[] args) {
        InnerClassDemo innerClassDemo = new InnerClassDemo();
        innerClassDemo.setName("InnerName");
        InnerClass innerClass = innerClassDemo.new InnerClass();
        System.out.println("inner Class name = " + innerClass.getName() + ", age = " + innerClass.getAge());
    }

}
