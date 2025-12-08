<%@ page import="membership.MemberDTO" %>
<%@ page import="membership.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String userId = request.getParameter("user_id");
String userPwd = request.getParameter("user_pw");



String oracleDriver = application.getInitParameter("OracleDriver");
String oracleURL = application.getInitParameter("OracleURL");
String oracleId = application.getInitParameter("OracleId");
String oraclePwd = application.getInitParameter("OraclePwd");

MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
boolean idExists = dao.isIdExists(userId);

MemberDTO memberDTO = null;
if(idExists) {
    memberDTO = dao.getMemberDTO(userId, userPwd); 
}
dao.close();

if (memberDTO != null && memberDTO.getId() != null ){
	session.setAttribute("UserId", memberDTO.getId());
	session.setAttribute("UserName", memberDTO.getName());
	response.sendRedirect("LoginMain.jsp");
}else if(idExists){
	request.setAttribute("LoginErrMsg", "비밀번호가 일치하지 않습니다.");
	request.getRequestDispatcher("LoginMain.jsp").forward(request, response);
}else{
%>
<script>
	if(confirm("아이디가 존재하지않습니다. 회원가입 하시겠습니까?")){
		location.href = "../Account/NewAccount.jsp"
	}else{
		location.href = "LoginMain.jsp"
	}
</script>	
<%
}
%>
