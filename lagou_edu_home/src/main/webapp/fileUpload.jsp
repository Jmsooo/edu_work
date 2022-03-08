<%--
  Created by IntelliJ IDEA.
  User: Jia.M
  Date: 2022/3/8
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--
        文件上传三要素:
            1. 表单提交方式必须是 post
            2. 表单的 enctype 属性必须为 : multipart/from-data
            3. 表单中必须有文件上传项
    --%>
    <form action="" method="post" enctype="multipart/form-data">
        <input type="file" name="upload">
        <br>
        <input type="text" name="name">
        <input type="text" name="password">
        <input type="submit" value="文件上传">
    </form>

</body>
</html>
