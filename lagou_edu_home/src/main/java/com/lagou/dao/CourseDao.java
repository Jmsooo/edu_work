package com.lagou.dao;

import com.lagou.pojo.Course;

import java.util.List;

/**
 * 课程模块 Dao 层的实现类
 */
public interface CourseDao {

    //查询课程列表信息
    public List<Course> findCourseList();

}
