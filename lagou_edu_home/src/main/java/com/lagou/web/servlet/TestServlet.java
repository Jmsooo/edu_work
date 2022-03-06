package com.lagou.web.servlet;

import com.lagou.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 当前 Servlet 对应的是课程管理模块
 */
@WebServlet("/Test")
public class TestServlet extends BaseServlet {

    //添加课程
    public void addCourse(HttpServletRequest request,HttpServletResponse response){
        System.out.printf("新建课程");
    }

    //根据课程名查询课程
    public void findByName(HttpServletRequest request,HttpServletResponse response){
        System.out.printf("根据课程名进行查询");
    }

    //根据课程状态查询课程
    public void findByStatus(HttpServletRequest request,HttpServletResponse response){
        System.out.printf("根据课程状态查询课程");
    }
}
