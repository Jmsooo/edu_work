package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
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
    public void findCourseList(HttpServletRequest request, HttpServletResponse response) {

        try {
            //业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findCourseList();

            //使用 SimplePropertyPreFilter 指定要转换的JSON字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "price", "sort_num", "status");
            //响应结果
            String result = JSON.toJSONString(courseList, filter);

            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
