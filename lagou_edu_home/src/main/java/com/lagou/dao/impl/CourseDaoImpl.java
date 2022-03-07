package com.lagou.dao.impl;

import com.lagou.dao.CourseDao;
import com.lagou.pojo.Course;
import com.lagou.utils.DateUtils;
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

}
