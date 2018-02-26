<%@ page import="java.util.ArrayList" %>
<%@ page import="PackageFoo.Book" %>
<%@ page import="PackageFoo.*"%>
<%@ page import="java.sql.SQLException" %>


<%--
Created by IntelliJ IDEA.
User: LukasAlexanderFlørnæ
Date: 19-02-2018
Time: 10:18
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>It works!</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link type="text/css" href="${pageContext.request.contextPath}/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
    <link type="text/css" href="${pageContext.request.contextPath}/css/flat-ui.css" rel="stylesheet" >
      <script defer src="${pageContext.request.contextPath}/js/fontawesome-all.js"></script>
      <style>
      table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
      }

      td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
      }

      tr:nth-child(even) {
        background-color: #dddddd;
      }
    </style>
  </head>
  <body>

  <div class="Info">

    <div align="center">
        <button id="CreateBookBtn" class="btn btn-block btn-lg btn-primary" onclick="window.location='/CreateBook';">
            <i class="far fa-plus-square fa-lg"></i>
            <i class="fas fa-book fa-lg"></i>
        </button>

        <!-- /rest/hello -->
        <button id="RestButton" class="btn btn-block btn-lg btn-primary" onclick="window.location='/rest/hello';">
            <i class="fas fa-code fa-lg"></i>
        </button>

        <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr>
          <th>ID</th>
          <th>Title</th>
          <th>Author</th>
          <th>Created at</th>
          <th>Actions</th>
        </tr>


        <%

          ArrayList<Book> books = new ArrayList<Book>();
          try{
              BookDao dao = new BookDao();

              String webRootPath = application.getRealPath("").replace('\\', '/');
              String getWebPath = webRootPath.substring(0, 34);
              String path = getWebPath+"\\web\\tmp\\";
              dao.ConnectionDB(path);
              dao.CreateBOOKTable();
            books = dao.getallBook();
          }catch (SQLException e){
              e.printStackTrace();
          }

          if(books != null){

        %>
          <tr>
            <%

              for(Book b:books) {
            %>
            <td><%=b.getId()%> </td>
            <td><%=b.getTitle()%>  </td>
            <td><%=b.getAuthor()%> </td>
            <td><%=b.getCreated_at()%> </td>
            <td>
              <button class="btn btn-info" onclick="window.location='/edit?id=<%=b.getId()%>';"><i class="far fa-edit"></i></button>
              &nbsp;&nbsp;&nbsp;&nbsp;
                <button class="btn btn-danger" onclick="window.location='/delete?id=<%=b.getId()%>';"><i class="fas fa-ban"></i></button>
            </td>
          </tr>

<% }
}else{
%>
<p>Banana</p>
          <% }
          %>
      </table>
    </div>

  </div>

<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
<!--<script src="${pageContext.request.contextPath}/js/vendor/jquery.min.js"></script>-->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<!--<script src="${pageContext.request.contextPath}/js/vendor/video.js"></script>-->
<!--<script src="${pageContext.request.contextPath}/js/flat-ui.js"></script>-->
  </body>
</html>