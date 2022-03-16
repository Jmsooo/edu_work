package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course_Section;
import com.lagou.service.CourseContentService;
import com.lagou.service.impl.CourseContentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {

    //展示对应课程的章节与课时信息
    public void findSectionAndLessonByCourseId(HttpServletRequest request, HttpServletResponse response){
        try {
            String courseId = request.getParameter("course_id");

            CourseContentService contentService = new CourseContentServiceImpl();
            List<Course_Section> list = contentService.findSectionAndLessonByCourseId(Integer.parseInt(courseId));

            String result = JSON.toJSONString(list);
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
