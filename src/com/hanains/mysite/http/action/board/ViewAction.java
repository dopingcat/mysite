package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;

public class ViewAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		request.setAttribute("boardVo", dao.selectBoard(request.getParameter("board_no")));
		dao.incViewCnt(request.getParameter("board_no"));
		
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/view.jsp");
	}
}
