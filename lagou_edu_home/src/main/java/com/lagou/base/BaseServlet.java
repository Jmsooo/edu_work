package com.lagou.base;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseServlet extends HttpServlet {
    /**
     * doGet 方法作为一个调度器， 根据请求功能的不同， 调用对应的方法
     *      规定必须传递一个参数 ：
     *          methodName = 功能名
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = null;

        //1. 获取参数 要访问的方法名
        methodName = req.getParameter("methodName");

        String contentType = req.getHeader("Content-Type");

        if ("application/json;charset=utf-8".equals(contentType)){
            String postJSON = getPostJSON(req);
            Map<String,Object> map = JSON.parseObject(postJSON, Map.class);
            methodName = (String) map.get("methodName");
            req.setAttribute("map",map);
        }else {
            methodName = req.getParameter("methodName");
        }

        //2. 判断 执行对应的方法
        if(methodName != null){
            //通过反射优化代码，提升代码的可维护性
            try {
                //1. 获取字节码文件对象 this = TestServlet
                Class c = this.getClass();
                //2. 根据传入的方法名，获取对应的方法对象
                Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                //调用method对象的invoke方法，执行对应的功能
                method.invoke(this,req,resp);

            }catch (Exception e){
                e.printStackTrace();
            }
        }


        /*if ("addCourse".equals(methodName)){
            addCourse(req,resp);
        }else if ("findByName".equals(methodName)){
            findByName(req,resp);
        }else if ("findByStatus".equals(methodName)){
            findByStatus(req,resp);
        }*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * POST 请求格式 ： application/json; charset=utf-8
     * 使用该方法获取
     * @param request
     * @return
     */
    public String getPostJSON(HttpServletRequest request){
        try {
            //从 request 对象中获取缓冲输入流对象
            BufferedReader reader = request.getReader();

            //使用 StringBuffer 保存读取出的数据
            StringBuffer stringBuffer = new StringBuffer();

            String line = null;

            //循环读取
            while ((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }

            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
