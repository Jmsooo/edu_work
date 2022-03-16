package com.lagou.service;

import com.lagou.pojo.Course_Section;

import java.util.List;

/**
 * 课程内容管理 Service 层
 */
public interface CourseContentService {

    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

}
