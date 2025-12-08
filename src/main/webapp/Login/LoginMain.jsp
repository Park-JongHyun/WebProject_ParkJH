<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>세션</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<span style = "color: red; font-size: 1.2m">
		<%= request.getAttribute("LoginErrMsg") == null?"" : request.getAttribute("LoginErrMsg") %>
	</span>
	<%
	if (session.getAttribute("UserId")==null){
	%>
	<script>
	function validateForm(form){
		if (!form.user_id.value){
			alert("아이디를 입력하세요");
			return false;
		}	
		if(form.user_pw.value == ""){
			alert("패스워드를 입력하세요");
			return false;
		} 
	}
	</script>
	
	<form action="LoginProcess.jsp" method="post" name="loginMain" onsubmit="return validateForm(this);">
	아이디:<input type="text" name="user_id" /> <br />
	패스워드:<input type="password" name="user_pw" /> <br />
	<input type="submit" value="로그인하기" />
	</form>
	
	<%
	}else {//로그인된 상태
	%>
		<!--session 영역에 속성값이 있다면 로그인에 성공한 상태이므로 회원의 이름과 로그아웃 버튼을 출력한다.  -->
		<%=session.getAttribute("UserName")%> 회원님, 로그인 하셨습니다.<br />
		<a href="Logout.jsp">[로그아웃]</a>
	<% 
	}
	%>
</body>
</html>