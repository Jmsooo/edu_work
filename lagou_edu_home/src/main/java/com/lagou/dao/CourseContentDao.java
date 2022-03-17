package com.lagou.dao;

import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;

import java.util.List;

/**
 * 课程内容管理 DAO 层
 */
public interface CourseContentDao {

    //根据课程ID查询课程相关信息
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    //根据章节ID查询章节相关的课时信息
    public List<Course_Lesson> findLessonBySectionId(int sectionId);

    //根据课程ID 回显课程信息
    public Course findCourseByCourseId(int courseId);

    //保存章节信息
    public int saveSection(Course_Section section);

    //修改章节信息
    public int updateSection(Course_Section section);

    //修改章节状态
    public int updateSectionStatus(int id,int status);


}
