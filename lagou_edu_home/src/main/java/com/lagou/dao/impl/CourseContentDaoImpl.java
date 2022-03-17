package com.lagou.dao.impl;

import com.lagou.dao.CourseContentDao;
import com.lagou.pojo.Course;
import com.lagou.pojo.Course_Lesson;
import com.lagou.pojo.Course_Section;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CourseContentDaoImpl implements CourseContentDao {
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {


        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT\n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_section WHERE course_id = ?";

            List<Course_Section> sectionList = queryRunner.query(sql, new BeanListHandler<Course_Section>(Course_Section.class), courseId);

            //根据章节ID查询课时信息
            for (Course_Section section : sectionList) {
                List<Course_Lesson> lessonList = findLessonBySectionId(section.getId());
                section.setLessonList(lessonList);
            }

            return sectionList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course_Lesson> findLessonBySectionId(int sectionId) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT\n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_id,\n" +
                    "theme,\n" +
                    "duration,\n" +
                    "is_free,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_lesson WHERE section_id = ?";

            List<Course_Lesson> lessonList = queryRunner.query(sql, new BeanListHandler<Course_Lesson>(Course_Lesson.class), sectionId);

            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            String sql = "SELECT id,course_name FROM course WHERE id = ?";
            Course course = queryRunner.query(sql, new BeanHandler<Course>(Course.class), courseId);
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int saveSection(Course_Section section) {
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            String sql = "INSERT INTO course_section(\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS,\n" +
                    "create_time,\n" +
                    "update_time\n" +
                    ") VALUES(?,?,?,?,?,?,?)";
            Object[] params = {
                    section.getCourse_id(),
                    section.getSection_name(),
                    section.getDescription(),
                    section.getOrder_num(),
                    section.getStatus(),
                    section.getCreate_time(),
                    section.getUpdate_time()
            };
            int row = queryRunner.update(sql, params);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSection(Course_Section section) {
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            String sql = "UPDATE course_section SET\n" +
                    "section_name = ?,\n" +
                    "description = ?,\n" +
                    "order_num = ?,\n" +
                    "update_time = ?\n" +
                    "WHERE id = ?";
            Object[] params = {
                    section.getSection_name(),
                    section.getDescription(),
                    section.getOrder_num(),
                    section.getUpdate_time()
            };
            int row = queryRunner.update(sql, params);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
