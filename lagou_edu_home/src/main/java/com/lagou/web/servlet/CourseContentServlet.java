package com.lagou.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lagou.base.BaseServlet;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Section;
import com.lagou.service.CourseContentService;
import com.lagou.service.impl.CourseContentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

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

    //根据课程ID 回显课程信息
    public void findCourseById(HttpServletRequest request,HttpServletResponse response){
        try {
            String courseId = request.getParameter("course_id");

            CourseContentService contentService = new CourseContentServiceImpl();
            Course course = contentService.findCourseByCourseId(Integer.parseInt(courseId));

            SimplePropertyPreFilter filter =new SimplePropertyPreFilter(Course.class,"id","course_name");
            String result = JSON.toJSONString(course, filter);

            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //保存&修改章节信息
    public void saveOrUpdateSection(HttpServletRequest request,HttpServletResponse response){
        try {
            Map<String,Object> map = (Map<String, Object>) request.getAttribute("map");

            Course_Section section = new Course_Section();

            BeanUtils.populate(section,map);

            CourseContentService contentService = new CourseContentServiceImpl();
            String result = contentService.saveSection(section);

            response.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
