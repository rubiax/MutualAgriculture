<%--
  Created by IntelliJ IDEA.
  User: Kui
  Date: 2016/7/19
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>


        <form action="TaskServlet" method=post>

          name:<input type="text" name="username"/><br>
          password:<input type="password" name="password"/><br>
          submit:<input type="submit" name="method" value="getTaskInfo"/><br>


        </form>

  </body>
</html>
