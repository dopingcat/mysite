package com.hanains.mysite.http.action.user;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;

public class UserActionFactory extends ActionFactory {
	@Override
	public Action getAction(String actionName) {
		if(actionName == null) {
			actionName = "/";
		}
		Action action = null;
		
		if(actionName.equals("joinform")) {
			action = new JoinFormAction();
		} else if(actionName.equals("join")) {
			action = new JoinAction();
		} else if(actionName.equals("joinsuccess")) {
			action = new JoinSuccessAction();
		} else if(actionName.equals("loginform")) {
			action = new LoginFormAction();
		} else if(actionName.equals("login")) {
			action = new LoginAction();
		} else if(actionName.equals("logout")) {
			action = new LogoutAction();
		} else {
			action = new LoginFormAction();
		}
		
		return action;
	}
}
