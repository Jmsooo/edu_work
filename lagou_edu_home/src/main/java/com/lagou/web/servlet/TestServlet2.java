package com.lagou.web.servlet;

import com.lagou.base.BaseServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/test2")
public class TestServlet2 extends BaseServlet {

    public void show(){
        System.out.println("TestServlet show方法执行了");
    }

}
