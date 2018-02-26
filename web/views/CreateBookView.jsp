<%--
  Created by IntelliJ IDEA.
  User: LukasAlexanderFlørnæ
  Date: 20-02-2018
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link href="${pageContext.request.contextPath}/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
    <link href="${pageContext.request.contextPath}/css/flat-ui.css" rel="stylesheet" >
    <script defer src="${pageContext.request.contextPath}/js/fontawesome-all.js"></script>
</head>
<body>
<% //todo: error on add book %>
<form method="post" action="test" id="form1">
    Title <input class="form-control flat"  placeholder="title" type="text" name="title" >
    Author <input class="form-control flat" placeholder="author" type="text" name="author" >
    ISBN <input class="form-control flat"  placeholder="ISBN" type="text" name="ISBN" >
    <button type="submit" form="form1" class="btn bg-primary" value="submit">Submit</button>
</form>


<button id="BackBtn" class="btn btn-block btn-lg btn-primary" onclick="window.location='/';">Back</button>

<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
<!--<script src="${pageContext.request.contextPath}/js/vendor/jquery.min.js"></script>-->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<!--<script src="${pageContext.request.contextPath}/js/vendor/video.js"></script>-->
<!--<script src="${pageContext.request.contextPath}/js/flat-ui.js"></script>-->
</body>
</html>
