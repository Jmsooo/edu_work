package com.lagou.service;

import com.lagou.pojo.Course;

import java.util.List;

/**
 * 课程管理模块 Service 层接口
 */
public interface CourseService {

    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName,String status);

    public String saveCourseSalesInfo(Course course);

    public Course findCourseById(int id);

    public String updateCourseSalesInfo(Course course);


}
