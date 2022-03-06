<%--
  Created by IntelliJ IDEA.
  User: Soul
  Date: 2022/2/8
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/Test?methodName=addCourse">新建课程</a>
    <a href="${pageContext.request.contextPath}/Test?methodName=findByName">根据课程名称查询</a>
    <a href="${pageContext.request.contextPath}/Test?methodName=findByStatus">根据课程状态查询</a>

    <a href="${pageContext.request.contextPath}/test2?methodName=show">show</a>

</body>
</html>
