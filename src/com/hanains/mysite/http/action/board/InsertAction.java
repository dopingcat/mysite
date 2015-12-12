package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

public class InsertAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardVo vo = new BoardVo();
		UserVo uVo = (UserVo)request.getSession().getAttribute("authUser");
		
		vo.setMemberNo(uVo.getNo());
		vo.setMemberName(uVo.getName());
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		new BoardDao().insert(vo);

		response.sendRedirect("/mysite/board");
	}
}
