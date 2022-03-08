package com.lagou.dao;

import com.lagou.dao.impl.CourseDaoImpl;
import com.lagou.pojo.Course;
import com.lagou.utils.DateUtils;
import org.junit.Test;

import java.util.List;

public class TestCourseDao {

    CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void testFindByCourseNameAndStatus() {
        List<Course> courseList = courseDao.findByCourseNameAndStatus("微服务", null);
        System.out.println("courseList = " + courseList);
        for (Course course : courseList) {
            System.out.println(course.getId() + " " + course.getCourse_name() + " " + course.getStatus());
        }
    }

    //测试 - 查询课程列表信息
    @Test
    public void testFindCourseList() {

        List<Course> courseList = courseDao.findCourseList();
        System.out.println("courseList = " + courseList);

    }

    /**
     * course_name,
     * brief,
     * teacher_name,
     * teacher_info,
     * preview_first_field,
     * preview_second_field,
     * discounts,
     * price,
     * price_tag,
     * share_image_title,
     * share_title,
     * share_description,
     * course_description,
     * course_img_url,
     * STATUS,
     * create_time,
     * update_time
     *
     * 测试 - 保存课程营销信息
     */
    @Test
    public void testSaveCourseSalesInfo(){
        Course course = new Course();

        course.setCourse_name("疯狂Java讲义");
        course.setBrief("学会做服务端项目");
        course.setTeacher_name("李刚");
        course.setTeacher_info("疯狂Java讲义是2008年9月电子工业出版社出版的书籍，作者是李刚。");
        course.setPreview_first_field("共27讲");
        course.setPreview_second_field("每周三周五更新");
        course.setDiscounts(98.88);
        course.setPrice(136.5);
        course.setPrice_tag("最新款");
        course.setShare_image_title("Java数组");
        course.setShare_title("Java集合");
        course.setShare_description("Java多线程");
        course.setCourse_description("Java反射");
        course.setCourse_img_url("https://www.xx.com.xx.jpg");
        course.setStatus(1);
        String formart = DateUtils.getDateFormart();
        course.setCreate_time(formart);
        course.setUpdate_time(formart);

        int row = courseDao.saveCourseSalesInfo(course);
        System.out.println("row = " + row);
    }

}
