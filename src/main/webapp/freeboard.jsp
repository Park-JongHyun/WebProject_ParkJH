<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>
<head>
    <title>자유게시판</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
</head>
<body class="is-preload">

<div id="wrapper">

    <!-- Main -->
    <div id="main">
        <div class="inner">

            <!-- Header -->
            <jsp:include page="/Common/Header.jsp" />

            <!-- Content -->
            <section>
                <header class="main">
                    <h1>자유게시판</h1>
                </header>

                <!-- 검색 폼 -->
                <form method="get" action="<%= request.getContextPath() %>/BoardServlet">
                    <table width="100%">
                        <tr>
                            <td align="center">
                                <select name="searchField">
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                </select>
                                <input type="text" name="searchWord" />
                                <input type="submit" value="검색" />
                            </td>
                        </tr>
                    </table>
                </form>

                <!-- 목록 -->
                <table border="1" width="100%">
                    <tr>
                        <th width="10%">번호</th>
                        <th width="*">제목</th>
                        <th width="15%">작성자</th>
                        <th width="10%">조회수</th>
                        <th width="15%">작성일</th>
                        <th width="8%">첨부</th>
                    </tr>

                    <c:choose>
                        <c:when test="${ empty boardLists }">
                            <tr>
                                <td colspan="6" align="center">
                                    등록된 게시물이 없습니다.
                                </td>
                            </tr>
                        </c:when>

                        <c:otherwise>
                            <c:forEach var="row" items="${boardLists}" varStatus="loop">
                                <tr align="center">
                                    <td>${ map.totalCount - loop.index }</td>

                                    <td align="left">
                                        <a href="../mvcboard/view.do?idx=${row.idx}&boardType=${map.boardType}">
                                            ${ row.title }
                                        </a>
                                    </td>

                                    <td>${ row.id }</td>
                                    <td>${ row.visitcount }</td>
                                    <td>${ row.postdate }</td>

                                    <td>
                                        <c:if test="${ not empty row.ofile }">
                                            <a href="../mvcboard/download.do?ofile=${row.ofile}&sfile=${row.sfile}&idx=${row.idx}">
                                                [Down]
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                </table>

                <!-- 글쓰기 버튼 -->
                <div style="text-align:right; margin-top:15px;">
                    <button type="button"
                            onclick="location.href='../mvcboard/write.do?boardType=${map.boardType}'">
                        글쓰기
                    </button>
                </div>

            </section>

        </div>
    </div>

    <!-- Sidebar (변경 없음) -->
    <div id="sidebar">
        <div class="inner">
            <nav id="menu">
                <header class="major">
                    <h2>Menu</h2>
                </header>
                <ul>
                    <li><a href="index.jsp">Homepage</a></li>
                    <li><a href="generic.jsp">공지사항</a></li>
                    <li>
                        <span class="opener">게시판</span>
                        <ul>
                            <li><a href="freeboard.jsp">자유게시판</a></li>
                            <li><a href="databoard.jsp">자료게시판</a></li>
                            <li><a href="qnaboard.jsp">QnA게시판</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <footer id="footer">
                <p class="copyright">&copy; Untitled. All rights reserved.</p>
            </footer>

        </div>
    </div>

</div>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/browser.min.js"></script>
<script src="assets/js/breakpoints.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>

</body>
</html>
