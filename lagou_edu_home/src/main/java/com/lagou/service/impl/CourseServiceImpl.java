package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseDao;
import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程管理模块 Service 层的实现类
 */
public class CourseServiceImpl implements CourseService {

    //创建 CourseDao
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findCourseList() {
        List<Course> courseList = courseDao.findCourseList();
        return courseList;
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        List<Course> courseList = courseDao.findByCourseNameAndStatus(courseName, status);
        return courseList;
    }

    @Override
    public String saveCourseSalesInfo(Course course) {

        String dateFormart = DateUtils.getDateFormart();
        course.setCreate_time(dateFormart);
        course.setUpdate_time(dateFormart);
        course.setStatus(1);

        int row = courseDao.saveCourseSalesInfo(course);

        if (row > 0) {
            return StatusCode.SUCCESS.toString();
        } else {
            return StatusCode.FAIL.toString();
        }
    }

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course course) {

        String dateFormart = DateUtils.getDateFormart();
        course.setUpdate_time(dateFormart);

        int row = courseDao.updateCourseSalesInfo(course);

        if (row > 0) {
            return StatusCode.SUCCESS.toString();
        } else {
            return StatusCode.FAIL.toString();
        }
    }

    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {
        int row = courseDao.updateCourseStatus(course);

        Map<String,Integer> map = new HashMap<>();

        int status = course.getStatus();

        if (row > 0){
            map.put("status",status);
            return map;
        }else {
            return null;
        }
    }
}
