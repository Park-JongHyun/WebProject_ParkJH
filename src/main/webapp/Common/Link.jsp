<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="1" width="90%"> 
    <tr>
        <td align="center">
        <!-- 로그인 여부에 따른 메뉴 변화 -->
            <a href="../Account/NewAccount.jsp">회원가입</a>            
            &nbsp;&nbsp;&nbsp; 
            
        <% if (session.getAttribute("UserId") == null) { %>
            <a href="../Login/LoginMain.jsp">로그인</a>
        <% } else { %>
            <a href="../Login/Logout.jsp">로그아웃</a>
        <% } %>            
            &nbsp;&nbsp;&nbsp; 
            
            <a href="../mvcboard/list.do?boardType=free">자유게시판</a>
            &nbsp;&nbsp;&nbsp; 
            
            <a href="../mvcboard/list.do?boardType=data">자료게시판</a>
            &nbsp;&nbsp;&nbsp; 
            
            <a href="../mvcboard/list.do?boardType=notice">공지게시판</a>
            &nbsp;&nbsp;&nbsp; 
            
            <a href="#1">회원정보수정</a>
        </td>
    </tr>
</table>
