package com.test;

public class TestThis {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
    }
}

class Person{
    int num = 10;
    public Person() {
        System.out.println("num="+this.num);
        System.out.println("父类中的This ：" + this);
        Class c = this.getClass();
        System.out.println(c);
    }
}

class A extends Person{
    int num = 11;
}

class B extends Person{
    int num = 12;
}

class C extends Person{
    int num = 13;
}

