<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
							<% pageContext.setAttribute("cn", "\n"); %>
							${fn:replace(boardVo.content, cn, "<br/>")}
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<!-- <a href="/mysite/board?pageNum=현재 글의 페이지 넘버 받아오기">글목록</a> -->
					<a href="/mysite/board?pageNum=${currentPage}">글목록</a>
					<c:if test="${(not empty authUser) && (boardVo.memberNo eq authUser.no)}">
						<a href="/mysite/board?a=modify&board_no=${boardVo.no}&currentPage=${currentPage}">글수정</a>
					</c:if>
					
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="pwd" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>