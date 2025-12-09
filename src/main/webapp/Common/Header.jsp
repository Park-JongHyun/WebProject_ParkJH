<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header id="header">
	<a href="<%= request.getContextPath() %>/index.jsp" class="logo"><strong>Editorial</strong> by HTML5 UP</a>
		<ul class="icons">
			<% if (session.getAttribute("UserId")==null) { %>
				<li><a href="<%= request.getContextPath() %>/Login/LoginMain.jsp" ><span class="label">로그인</span></a></li>
				<li><a href="<%= request.getContextPath() %>/Account/NewAccount.jsp" ><span class="label">회원가입</span></a></li>
			<% } else { %>
				<li><a href="<%= request.getContextPath() %>/Login/Logout.jsp">로그아웃</a></li>
				<li><a href="<%= request.getContextPath() %>/Account/EditAccout.jsp">회원정보편집</a></li>
			<% } %>
		</ul>
</header>




