package com.jdk;

/**
 * @author: huan.liu
 * @since : 2022-01-03
 */
public class InnerStaticClassDemo {

    private String name;

    private static int age = 5;

    static class InnerStaticClass {

        public int getAge() {
            return age;
        }

        //静态内部类拥有外部类所有静态成员的访问权限，非静态成员无法访问
//        public String getName(){
//            return name;
//        }

    }

    public static void main(String[] args) {
        InnerStaticClass innerStaticClass = new InnerStaticClass();
        System.out.println(innerStaticClass.getAge());
    }

}
