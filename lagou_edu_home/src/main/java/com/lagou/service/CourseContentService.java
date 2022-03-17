package com.lagou.service;

import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Section;

import java.util.List;

/**
 * 课程内容管理 Service 层
 */
public interface CourseContentService {

    //根据课程ID查询课程相关信息
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    //根据课程ID 回显课程信息
    public Course findCourseByCourseId(int courseId);

    //保存章节信息
    public String saveSection(Course_Section section);

    //修改章节信息
    public String updateSection(Course_Section section);

    public String updateSectionStatus(int id,int status);

}
