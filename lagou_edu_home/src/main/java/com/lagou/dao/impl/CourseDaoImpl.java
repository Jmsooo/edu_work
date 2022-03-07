package com.lagou.dao.impl;

import com.lagou.dao.CourseDao;
import com.lagou.pojo.Course;
import com.lagou.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
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

}
