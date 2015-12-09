package com.hanains.mysite.http.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;

public class LoginAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDao();
		UserVo vo = dao.get(email, password);
		
		if(vo == null) {
			HttpUtil.redirect(response, "/mysite/user?a=loginform&result=fail");
			//HttpUtil.forwarding(request, response, "/WEB-INF/views/user/loginform_retry.jsp");
			return;	// 클라이언트쪽에서는 리다이렉트가 되지만 소스는 계속 돌고있으므로 리턴
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", vo);
		HttpUtil.redirect(response, "/mysite/main");
	}
}
