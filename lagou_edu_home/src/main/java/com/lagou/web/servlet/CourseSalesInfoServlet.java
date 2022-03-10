package com.lagou.web.servlet;

import com.lagou.base.Constants;
import com.lagou.pojo.Course;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
import com.lagou.utils.DateUtils;
import com.lagou.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存课程营销信息
 * 收集表单数据,封装到Course对象中
 */
@WebServlet("/courseSalesInfo")
public class CourseSalesInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //1. 创建 Course 对象
            Course course = new Course();

            //2. 创建 Map 集合, 用来收集数据
            Map<String, Object> map = new HashMap<>();

            //3. 创建磁盘对象工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //4. 创建文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            //5. 解析 request 对象, 获取表单项集合
            List<FileItem> list = fileUpload.parseRequest(req);

            //6. 遍历集合, 判断哪些是普通的表单项,哪些是文件表单项
            for (FileItem item : list) {
                boolean formField = item.isFormField();
                if (formField) {
                    //是普通表单项,获取表单项中的数据,并保存到map中
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString("utf-8");
                    System.out.println(fieldName + " : " + fieldValue);
                    //使用map收集数据
                    map.put(fieldName, fieldValue);
                } else {
                    //文件上传项
                    //获取文件名
                    String fileName = item.getName();
                    String newFileName = UUIDUtils.getUUID() + "_" + fileName;

                    //获取输入流
                    InputStream in = item.getInputStream();

                    String realPath = this.getServletContext().getRealPath("/");
                    String wabappsPath = realPath.substring(0, realPath.indexOf("lagou_edu_home"));
                    System.out.println(wabappsPath + "upload\\" + newFileName);
                    //创建输出流
                    FileOutputStream out = new FileOutputStream(wabappsPath + "upload\\" + newFileName);

                    IOUtils.copy(in, out);

                    //关闭流
                    out.close();
                    in.close();

                    //将图片路径进行保存
                    map.put("course_img_url", Constants.LOCAL_URL + "/upload/" + newFileName);
                }
            }
            //使用 BeanUtils 将map中的数据封装到course对象中
            BeanUtils.populate(course, map);

            String dateFormart = DateUtils.getDateFormart();
            course.setCreate_time(dateFormart);
            course.setUpdate_time(dateFormart);
            course.setStatus(1);

            //业务处理
            CourseService courseService = new CourseServiceImpl();
            String result = courseService.saveCourseSalesInfo(course);

            //响应结果
            resp.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
