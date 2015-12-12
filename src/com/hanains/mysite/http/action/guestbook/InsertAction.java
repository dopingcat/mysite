package com.hanains.mysite.http.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.action.Action;
import com.hanains.mysite.dao.GuestBookDao;
import com.hanains.mysite.vo.GuestBookVo;

public class InsertAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = request.getParameter("message");
		
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		new GuestBookDao().insert(vo);

		response.sendRedirect("/mysite/guestbook");
	}
}
