<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.hanains.mysite.vo.UserVo" %>
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<a href="/mysite/main"><h1>MySite</h1></a>
		<ul>
			<% if(authUser == null) { %>
			<li><a href="/mysite/user?a=loginform">로그인</a>
			<li><a href="/mysite/user?a=joinform">회원가입</a>
			<% } else { %>
			<li><a href="">회원정보수정</a>
			<li><a href="/mysite/user?a=logout">로그아웃</a>
			<li><%= authUser.getName() %>님 안녕하세요 ^^;</li>
			<% } %>
		</ul>
	</div>
</body>
</html>