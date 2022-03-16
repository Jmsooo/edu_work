package com.lagou.service.impl;

import com.lagou.dao.CourseContentDao;
import com.lagou.dao.impl.CourseContentDaoImpl;
import com.lagou.pojo.Course_Section;
import com.lagou.service.CourseContentService;

import java.util.List;

public class CourseContentServiceImpl implements CourseContentService {

    CourseContentDao contentDao = new CourseContentDaoImpl();

    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        return contentDao.findSectionAndLessonByCourseId(courseId);
    }
}
