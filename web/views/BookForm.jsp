<%--
  Created by IntelliJ IDEA.
  User: LukasAlexanderFlørnæ
  Date: 20-02-2018
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="PackageFoo.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link href="${pageContext.request.contextPath}/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
    <link href="${pageContext.request.contextPath}/css/flat-ui.css" rel="stylesheet" >
    <script defer src="${pageContext.request.contextPath}/js/fontawesome-all.js"></script>

</head>
<body>
<div align="center">

    <%

        Book book = new Book();
        try{
            BookDao dao = new BookDao();
            String  id = request.getParameter("id");
            String webRootPath = application.getRealPath("").replace('\\', '/');
            String getWebPath = webRootPath.substring(0, 34);
            String path = getWebPath+"\\web\\tmp\\";
            dao.ConnectionDB(path);
            book = dao.getBookById(Integer.parseInt(id));
        }catch (SQLException e){
            e.printStackTrace();
        }

        if(book != null){

    %>

        <form action="update" method="post" id="form2">
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                           Edit Book
                        </h2>
                    </caption>
                        <input type="hidden" name="id" class="form-control flat" value="<%=book.getId()%>" />
                    <tr>
                        <th>Title: </th>
                        <td>
                            <input type="text" name="title" class="form-control flat" size="45"
                                   value="<%=book.getTitle()%>"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Author: </th>
                        <td>
                            <input type="text" name="author" class="form-control flat" size="45"
                                   value="<%=book.getAuthor()%>"
                            />
                        </td>
                    </tr>

                    <tr>
                        <th>ISBN: </th>
                        <td>
                            <input type="text" name="isbn" class="form-control flat" size="45"
                                   value="<%=book.getIsbn()%>"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Create at: </th>
                        <td>
                            <input type="text" name="createAT" class="form-control flat"  size="45"
                                   value="<%=book.getCreated_at()%>"
                            />
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                            <button type="submit" form="form2" class="btn bg-primary" value="Save">Submit</button>
                        </td>
                    </tr>
                </table>
            </form>

        <button id="BackBtn" class="btn btn-block btn-lg btn-primary" onclick="window.location='/';">Back</button>
   <% }else{
            out.println("<p>Error!</p>");
   }%>


</div>


<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
<!--<script src="${pageContext.request.contextPath}/js/vendor/jquery.min.js"></script>-->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<!--<script src="${pageContext.request.contextPath}/js/vendor/video.js"></script>-->
<!--<script src="${pageContext.request.contextPath}/js/flat-ui.js"></script>-->
</body>
</html>
