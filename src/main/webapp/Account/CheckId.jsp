<%@ page import="membership.MemberDAO" %>
<%@ page import="jakarta.servlet.ServletContext" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String userId = request.getParameter("user_id");

String driver = application.getInitParameter("OracleDriver");
String url = application.getInitParameter("OracleURL");
String dbId = application.getInitParameter("OracleId");
String dbPw = application.getInitParameter("OraclePwd");

MemberDAO dao = new MemberDAO(driver, url, dbId, dbPw);

boolean exists = dao.isIdExists(userId);
dao.close();

if(exists){
    response.sendRedirect("NewAccount.jsp?idCheck=NO&user_id=" + userId);
} else {
    response.sendRedirect("NewAccount.jsp?idCheck=OK&user_id=" + userId);
}
%>

>