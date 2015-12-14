package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;

public class SearchAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("%");
		sb.append(request.getParameter("keyword"));
		sb.append("%");
		request.setAttribute("currentPage", request.getParameter("pageNum"));
		request.setAttribute("pageSize", BoardDao.getBoardDao().getSearchPageSize(sb.toString()));
		request.setAttribute("boardList", BoardDao.getBoardDao().getSearchList(sb.toString(), request.getParameter("pageNum")));
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");
	}
}
