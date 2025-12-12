<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String idCheck = request.getParameter("idCheck");
    if (idCheck == null) idCheck = "N";

    String userId    = request.getParameter("user_id") == null ? "" : request.getParameter("user_id");
    String userName  = request.getParameter("user_name") == null ? "" : request.getParameter("user_name");
    String userEmail = request.getParameter("user_email") == null ? "" : request.getParameter("user_email");
    String userPhone = request.getParameter("user_phone") == null ? "" : request.getParameter("user_phone");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>회원가입</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../assets/css/main.css" />

        <script>
        function validateForm(form){
            if(!form.user_id.value){
                alert("아이디를 입력하세요");
                return false;
            }
            if(form.idCheck.value !== "Y"){
                alert("아이디 중복확인을 해주세요.");
                return false;
            }
            if(!form.user_pw.value){
                alert("비밀번호를 입력하세요");
                return false;
            }
            if(form.user_pw.value !== form.user_pw2.value){
                alert("비밀번호가 일치하지 않습니다!");
                return false;
            }
            if(!form.user_name.value){
                alert("이름을 입력하세요");
                return false;
            }
            if(!form.user_email.value){
                alert("이메일을 입력하세요");
                return false;
            }
            if(!form.user_phone.value){
                alert("전화번호를 입력하세요");
                return false;
            }
            return true;
        }
        </script>

	</head>
	<body class="is-preload">

		<!-- Wrapper -->
		<div id="wrapper">

			<!-- Main -->
			<div id="main">
				<div class="inner">

					<!-- Header -->
					<header id="header">
						<a href="../index.jsp" class="logo"><strong>회원가입</strong></a>
					</header>

					<!-- Content -->
					<section>
						<header class="main">
							<h1>회원가입</h1>
						</header>

						<% String error = (String) request.getAttribute("error"); %>
						<% if(error != null){ %>
						    <p style="color:red; font-weight:bold;"><%= error %></p>
						<% } %>

						<form method="post"
						      action="<%= request.getContextPath() %>/RegistServlet"
						      name="newact"
						      onsubmit="return validateForm(this);">

						    <input type="hidden" name="idCheck" value="<%= idCheck.equals("OK") ? "Y" : "N" %>">

						    <label>아이디</label>
						    <input type="text" id="user_id" name="user_id" value="<%= userId %>" required>

						    <input type="button" value="중복확인"
						        onclick="location.href='<%= request.getContextPath() %>/Account/CheckId.jsp?user_id=' + document.newact.user_id.value">

						    <% if(idCheck.equals("OK")){ %>
						        <p style="color:green;">✔ 사용 가능한 아이디입니다.</p>
						    <% } else if(idCheck.equals("NO")){ %>
						        <p style="color:red;">✖ 이미 사용 중인 아이디입니다.</p>
						    <% } %>

						    <label>비밀번호</label>
						    <input type="password" id="password" name="user_pw" required>

						    <label>비밀번호 확인</label>
						    <input type="password" id="password2" name="user_pw2" required>

						    <label>이름</label>
						    <input type="text" id="user_name" name="user_name" value="<%= userName %>" required>

						    <label>이메일</label>
						    <input type="email" id="user_email" name="user_email" value="<%= userEmail %>" required>

						    <label>전화번호</label>
						    <input type="text" id="user_phone" name="user_phone" value="<%= userPhone %>" required>

						    <br>
						    <input type="submit" value="회원가입" class="button primary">
						</form>
					</section>

				</div>
			</div>

			<!-- Sidebar -->
			<div id="sidebar">
				<div class="inner">

					<!-- Menu -->
					<nav id="menu">
						<header class="major">
							<h2>Menu</h2>
						</header>
						<ul>
							<li><a href="../index.jsp">Homepage</a></li>
							<li><a href="../generic.jsp">공지사항</a></li>
							<li>
								<span class="opener">게시판</span>
								<ul>
									<li><a href="<%= request.getContextPath() %>/freeboard.jsp">자유게시판</a></li>
									<li><a href="<%= request.getContextPath() %>/databoard.jsp">자료게시판</a></li>
									<li><a href="#">QnA게시판</a></li>
								</ul>
							</li>
						</ul>
					</nav>

					<!-- Footer -->
					<footer id="footer">
						<p class="copyright">&copy; Untitled. All rights reserved.</p>
					</footer>

				</div>
			</div>

		</div>

		<!-- Scripts -->
		<script src="../assets/js/jquery.min.js"></script>
		<script src="../assets/js/browser.min.js"></script>
		<script src="../assets/js/breakpoints.min.js"></script>
		<script src="../assets/js/util.js"></script>
		<script src="../assets/js/main.js"></script>

	</body>
</html>
