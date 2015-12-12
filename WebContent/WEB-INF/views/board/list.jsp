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
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="hidden" name="a" value="search">
					<input type="text" id="keyword" name="keyword" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${boardList}" var="vo" varStatus="stat">
						<tr>
							<td>${vo.no}</td>
							<td><a href="/mysite/board?a=view&board_no=${vo.no}">${vo.title}</a></td>
							<td>${vo.memberName}</td>
							<td>${vo.viewCnt}</td>
							<td>${vo.regDate}</td>
							<td><c:if test="${(not empty authUser) && (vo.memberNo eq authUser.no)}">
								<a href="">삭제</a>
							</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<li class="pg-prev"><a href="/mysite/board?pageNum=${currentPage-1}">◀ 이전</a></li>
						<c:if test="${(currentPage < 3)}">
							<c:forEach begin="1" end="5" varStatus="stat">
								<c:choose>
									<c:when test="${stat.index > pageSize}">
										<li class="disable">${stat.index}</li>
									</c:when>
									<c:otherwise>
										<li><a href="/mysite/board?pageNum=${stat.index}">${stat.index}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						<li class="pg-next"><a href="/mysite/board?pageNum=${currentPage+1}">다음 ▶</a></li>
					</ul>	
				</div>
				<c:if test="${not empty authUser}">
					<div class="bottom">
						<a href="/mysite/board?a=write" id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="pwd" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>