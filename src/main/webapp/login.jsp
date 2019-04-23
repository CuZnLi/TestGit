<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LiCuZn
  Date: 2019/3/27
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>二哈论坛登陆</title>
</head>
<body>
    <c:if test="${!empty error}">
        <font color="red"><c:out value="'${error}"/></font>
    </c:if>

    <form action="<c:url value="/loginCheck.html "/>" method="post">
        用户名：
        <input type="text" name="userName">
        <br>
        密码：
        <input type="text" name="passWord">
        <br>
        <input type="submit" value="登陆"/>
        <input type="reset" value="重置"/>

    </form>

</body>
</html>
