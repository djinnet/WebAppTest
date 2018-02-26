<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URL" %>
<%@ page import="javax.xml.namespace.QName" %>
<%@ page import="javax.xml.ws.Service" %>
<%@ page import="PackageFoo.soapws.SOAPHelloWorld" %>
<%@ page import="PackageFoo.Book" %>
<%@ page import="com.thoughtworks.xstream.XStream" %>
<%@ page import="com.thoughtworks.xstream.io.xml.DomDriver" %>
<%@ page import="com.thoughtworks.xstream.io.xml.StaxDriver" %>
<%@ page import="org.w3c.dom.CDATASection" %>
<%@ page import="javax.xml.soap.SOAPPart" %><%--
  Created by IntelliJ IDEA.
  User: LukasAlexanderFlørnæ
  Date: 22-02-2018
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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


<%
    URL url = new URL("http://localhost:8080/soap?wsdl");
    QName qname = new QName("http://soapws.PackageFoo/", "HelloWorldImplService");

    Service service = Service.create(url, qname);

    SOAPHelloWorld hello = service.getPort(SOAPHelloWorld.class);

    //out.println(hello.getXMLMessage());

%>

<pre>
    <c:out value="<%=hello.getXMLMessage()%>" />
</pre>

<a href="/downloading">download a xml file</a>

</body>
</html>
