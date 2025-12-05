<%@ page import="common.JDBConnect" %>
<%@ page import="common.DBConnPool" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		JDBConnect jdbc1 = new JDBConnect();
		jdbc1.close();
	%>
	
	<%
		String driver = application.getInitParameter("OracleDriver");
		String url = application.getInitParameter("OracleURL");
		String id = application.getInitParameter("OracleId");
		String pwd = application.getInitParameter("OraclePwd");
		
		JDBConnect jdbc2 = new JDBConnect(driver,url,id,pwd);
		jdbc2.close();
	%>
	
	<%
	JDBConnect jdbc3 = new JDBConnect(application);
	jdbc3.close();
	%>
	
	<%
	DBConnPool pool = new DBConnPool();
	pool.close();
	%>
</body>
</html>