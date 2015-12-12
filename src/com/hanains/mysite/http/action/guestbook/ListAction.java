package com.hanains.mysite.http.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.GuestBookDao;

public class ListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("guestBookList", new GuestBookDao().getList());
		HttpUtil.forwarding(request, response, "/WEB-INF/views/guestbook/list.jsp");
	}
}
