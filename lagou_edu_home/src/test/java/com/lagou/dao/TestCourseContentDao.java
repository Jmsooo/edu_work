package com.lagou.dao;

import com.lagou.dao.impl.CourseContentDaoImpl;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import org.junit.Test;

import java.util.List;

public class TestCourseContentDao {

    CourseContentDao contentDao = new CourseContentDaoImpl();

    @Test
    public void testFindSectionAndLessonByCourseId(){
        List<Course_Section> list = contentDao.findSectionAndLessonByCourseId(59);

        for (Course_Section section : list) {
            System.out.println(section.getId() + " = " + section.getSection_name());

            List<Course_Lesson> lessonList = section.getLessonList();
            for (Course_Lesson lesson : lessonList) {
                System.out.println(lesson.getId() + " = " + lesson.getTheme() + " = " + lesson.getSection_id());
            }
        }
    }

}
