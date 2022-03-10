package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
import com.lagou.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    //根据条件查询课程信息
    public void findByCourseNameAndStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            //1. 接收参数
            String course_name = request.getParameter("course_name");
            String status = request.getParameter("status");

            //2. 业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findByCourseNameAndStatus(course_name, status);

            //3. 返回结果
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "price", "sort_num", "status");
            String result = JSON.toJSONString(courseList, filter);

            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据ID查询课程信息
    public void findCourseById(HttpServletRequest request, HttpServletResponse response) {

        try {
            String id = request.getParameter("id");

            CourseService courseService = new CourseServiceImpl();
            Course course = courseService.findCourseById(Integer.parseInt(id));

            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "brief", "teacher_name", "teacher_info", "price", "price_tag",
                    "discounts", "preview_first_field", "preview_second_field", "course_img_url", "share_title", "share_description", "course_description", "share_image_title");

            String result = JSON.toJSONString(course, filter);

            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改课程状态
    public void updateCourseStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");

            CourseService courseService = new CourseServiceImpl();
            Course course = courseService.findCourseById(Integer.parseInt(id));

            int status = course.getStatus();

            if (status == 0) {
                course.setStatus(1);
            } else {
                course.setStatus(0);
            }

            String dateFormart = DateUtils.getDateFormart();
            course.setUpdate_time(dateFormart);

            Map<String, Integer> map = courseService.updateCourseStatus(course);

            String result = JSON.toJSONString(map);

            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
