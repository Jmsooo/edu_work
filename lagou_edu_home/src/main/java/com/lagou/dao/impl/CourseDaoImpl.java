package com.lagou.dao.impl;

import com.lagou.dao.CourseDao;
import com.lagou.pojo.Course;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程模块 DAO 层的实现类
 */
public class CourseDaoImpl implements CourseDao {

    @Override
    public List<Course> findCourseList() {

        try {
            //1. 创建 QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            //2. 编写SQL 判断是否删除 is_del = 0 的数据,未删除的数据
            String sql = "SELECT \n" +
                    "\tid,\n" +
                    "\tcourse_name,\n" +
                    "\tprice,\n" +
                    "\tsort_num,\n" +
                    "\tSTATUS\n" +
                    "FROM course WHERE is_del = ?";

            //3. 执行查询
            List<Course> courseList = queryRunner.query(sql, new BeanListHandler<Course>(Course.class), 0);

            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {

        try {
            //1. 创建 QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            //2. 编写SQL 当前的查询为多条件不定项查询
            //2.1 创建 StringBuffer 对象, 将 SQL 字符串添加到缓存去
            String sql = "select id,course_name,price,sort_num,status from course where 1=1 and is_del = ?";
            StringBuffer buffer = new StringBuffer(sql);
            //2.2 创建 List 集合 用于保存参数
            List<Object> list = new ArrayList<>();
            list.add(0);

            //2.3 判断传入的参数是否为空
            if (courseName != null && courseName != "") {
                buffer.append(" and course_name like ?");
                //模糊查询 %
                courseName = "%" + courseName + "%";
                //将条件放入list集合中
                list.add(courseName);
            }
            if (status != null && status != "") {
                buffer.append(" and status = ?");
                int i = Integer.parseInt(status);
                list.add(i);
            }

            //3. 执行查询
            List<Course> courseList = queryRunner.query(buffer.toString(), new BeanListHandler<Course>(Course.class), list.toArray());

            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int saveCourseSalesInfo(Course course) {

        try {
            //1. 创建 QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            //2. 编写 SQL
            String sql = "INSERT INTO course(\n" +
                            "course_name,\n" +
                            "brief,\n" +
                            "teacher_name,\n" +
                            "teacher_info,\n" +
                            "preview_first_field,\n" +
                            "preview_second_field,\n" +
                            "discounts,\n" +
                            "price,\n" +
                            "price_tag,\n" +
                            "share_image_title,\n" +
                            "share_title,\n" +
                            "share_description,\n" +
                            "course_description,\n" +
                            "course_img_url,\n" +
                            "STATUS,\n" +
                            "create_time,\n" +
                            "update_time\n" +
                            ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //3. 准备参数
            Object[] params = {course.getCourse_name(),course.getBrief(),course.getTeacher_name(),course.getTeacher_info(),
                               course.getPreview_first_field(),course.getPreview_second_field(),course.getDiscounts(),course.getPrice(),
                               course.getPrice_tag(),course.getShare_image_title(),course.getShare_title(),course.getShare_description(),
                               course.getCourse_description(),course.getCourse_img_url(),course.getStatus(),course.getCreate_time(),course.getUpdate_time()};

            //4. 执行插入操作
            int row = queryRunner.update(sql, params);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
