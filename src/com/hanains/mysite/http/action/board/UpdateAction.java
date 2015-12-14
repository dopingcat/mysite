package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

public class UpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("currentPage", request.getParameter("currentPage"));
		BoardVo vo = new BoardVo();
		UserVo uVo = (UserVo)request.getSession().getAttribute("authUser");
		
		if(uVo == null) {
			response.sendRedirect("/mysite/user?a=login");
			return;
		}
		vo.setNo(Long.parseLong(request.getParameter("board_no")));
		vo.setMemberNo(uVo.getNo());
		vo.setMemberName(uVo.getName());
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		BoardDao.getBoardDao().update(vo);
		response.sendRedirect("/mysite/board?a=view&board_no="+request.getParameter("board_no"));
	}
}
