package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;

public class ListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		request.setAttribute("currentPage", request.getParameter("pageNum"));
		request.setAttribute("pageSize", dao.getPageSize());
		request.setAttribute("boardList", dao.getList(request.getParameter("pageNum")));
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");
	}
}
