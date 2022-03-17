package com.lagou.service.impl;

import com.lagou.base.StatusCode;
import com.lagou.dao.CourseContentDao;
import com.lagou.dao.impl.CourseContentDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Section;
import com.lagou.service.CourseContentService;
import com.lagou.utils.DateUtils;

import java.util.List;

public class CourseContentServiceImpl implements CourseContentService {

    CourseContentDao contentDao = new CourseContentDaoImpl();

    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        return contentDao.findSectionAndLessonByCourseId(courseId);
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        return contentDao.findCourseByCourseId(courseId);
    }

    @Override
    public String saveSection(Course_Section section) {
        //补全章节信息
        section.setStatus(2);
        String dateFormart = DateUtils.getDateFormart();
        section.setCreate_time(dateFormart);
        section.setUpdate_time(dateFormart);

        int row = contentDao.saveSection(section);

        if (row > 0){
            //添加成功
            String result = StatusCode.SUCCESS.toString();
            return result;
        }else {
            //添加失败
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }

    @Override
    public String updateSection(Course_Section section) {

        return null;
    }
}
