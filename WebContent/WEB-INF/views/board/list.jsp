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
					<input type="hidden" name="pageNum" value="1">
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
							<td><a href="/mysite/board?a=view&board_no=${vo.no}&currentPage=${currentPage}">${vo.title}</a></td>
							<td>${vo.memberName}</td>
							<td>${vo.viewCnt}</td>
							<td>${vo.regDate}</td>
							<td><c:if test="${(not empty authUser) && (vo.memberNo eq authUser.no)}">
								<a href="/mysite/board?a=delete&board_no=${vo.no}">삭제</a>
							</c:if></td>
						</tr>
					</c:forEach>
				</table>
					
				<div class="pager">
					<ul>
						<li class="pg-prev"><a href="/mysite/board?pageNum=1">◀◀ 처음${pageNum}</a></li>
						<li class="pg-prev"><a href="/mysite/board?pageNum=${currentPage-1}">◀ 이전</a></li>
						<!-- 현재 페이지가 3 이하일땐 인덱스 1~5 고정 -->
						<c:choose>
							<c:when test="${(currentPage <= 3)}">
								<c:forEach begin="1" end="5" varStatus="stat">
									<c:choose>
										<c:when test="${stat.index > pageSize}">
											<li class="disable">${stat.index}</li>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${stat.index eq currentPage}">
													<li class="current">${stat.index}</li>
												</c:when>
												<c:otherwise>
													<li><a href="/mysite/board?pageNum=${stat.index}">${stat.index}</a></li>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when test="${(currentPage > 3)}">
								<!-- 현재 페이지가 4 이상일땐 인덱스 현제 페이지 -2~+2 범위 -->
								<c:forEach begin="${currentPage-2}" end="${currentPage+2}" varStatus="stat">
									<c:choose>
										<c:when test="${stat.index > pageSize}">
											<li class="disable">${stat.index}</li>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${stat.index eq currentPage}">
													<li class="current">${stat.index}</li>
												</c:when>
												<c:otherwise>
													<li><a href="/mysite/board?pageNum=${stat.index}">${stat.index}</a></li>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
						</c:choose>
						<li class="pg-next"><a href="/mysite/board?pageNum=${currentPage+1}">다음 ▶</a></li>
						<li class="pg-next"><a href="/mysite/board?pageNum=${pageSize}">마지막 ▶</a></li>
					</ul>	
				</div>
				<c:if test="${not empty authUser}">
					<div class="bottom">
						<a href="/mysite/board?a=write&currentPage=${currentPage}" id="new-book">글쓰기</a>
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