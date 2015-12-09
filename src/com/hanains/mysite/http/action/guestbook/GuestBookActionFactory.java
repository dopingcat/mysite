package com.hanains.mysite.http.action.guestbook;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {
	@Override
	public Action getAction(String actionName) {
		if(actionName == null) {
			actionName = "/";
		}
		Action action = null;
		
		if(actionName.equals("list")) {
			action = new ListAction();
		} else if(actionName.equals("deleteform")) {
			action = new FormAction();
		} else if(actionName.equals("delete")) {
			action = new DeleteAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
