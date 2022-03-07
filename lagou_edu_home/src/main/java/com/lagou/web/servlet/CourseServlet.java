package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends BaseServlet {

    //查询课程信息列表
    public void findCourseList(HttpServletRequest request, HttpServletResponse response){

        try {
            //业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findCourseList();

            //响应结果
            String result = JSON.toJSONString(courseList);

            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
