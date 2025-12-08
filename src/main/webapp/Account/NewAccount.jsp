<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String idCheck = request.getParameter("idCheck"); 
    if(idCheck == null) idCheck = "N";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
function validateForm(form){
    if (!form.user_id.value){
        alert("아이디를 입력하세요");
        return false;
    }
    if(form.idCheck.value !== "Y"){
        alert("아이디 중복확인을 해주세요.");
        return false;
    }
    if (!form.user_pw.value){
        alert("비밀번호를 입력하세요");
        return false;
    }
    if(form.user_pw.value !== form  .user_pw2.value){
        alert("비밀번호가 일치하지 않습니다!");
        return false;
    }
    if (!form.user_name.value){
        alert("이름을 입력하세요");
        return false;
    }
    if (!form.user_email.value){
        alert("이메일을 입력하세요");
        return false;
    }
    if (!form.user_phone.value){
        alert("전화번호를 입력하세요");
        return false;
    }
    return true;
}
</script>
<title>회원가입
</title>
</head>
<body>
<h2>회원가입</h2>
<% String error = (String) request.getAttribute("error"); %>
<% if(error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>

<form action="<%= request.getContextPath() %>/RegistServlet"  method="post" name="newact" onsubmit="return validateForm(this);">
아이디: <input type="text" name="user_id" value="<%= request.getParameter("user_id") == null ? "" : request.getParameter("user_id") %>">
<input type="button" value="중복확인"
       onclick="location.href='CheckId.jsp?user_id=' + document.newact.user_id.value">
<br/>

<%
    if(idCheck.equals("OK")){
        out.println("<span style='color: green;'>사용 가능한 아이디입니다.</span><br/>");
    } else if(idCheck.equals("NO")){
        out.println("<span style='color: red;'>이미 사용 중인 아이디입니다.</span><br/>");
    }
%>



패스워드: <input type="password" name="user_pw" /> <br />
패스워드 확인: <input type="password" name="user_pw2" /> <br />
이름: <input type="text" name="user_name" /> <br />
이메일: <input type="email" name="user_email" /> <br />
전화번호: <input type="text" name="user_phone" /> <br />
<input type="submit" value="회원가입하기" />
</form>

</body>
</html>