package com.lagou.dao;

import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import org.junit.Test;

import java.util.List;

public class TestCourseDao {

    CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void testFindByCourseNameAndStatus() {
        List<Course> courseList = courseDao.findByCourseNameAndStatus("微服务", null);
        System.out.println("courseList = " + courseList);
        for (Course course : courseList) {
            System.out.println(course.getId() + " " + course.getCourse_name() + " " + course.getStatus());
        }
    }

    //测试 - 查询课程列表信息
    @Test
    public void testFindCourseList() {

        List<Course> courseList = courseDao.findCourseList();
        System.out.println("courseList = " + courseList);

    }

}
