package com.hanains.mysite.http.action.board;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {
	@Override
	public Action getAction(String actionName) {
		if(actionName == null) {
			actionName = "/";
		}
		Action action = null;
		
		if(actionName.equals("list")) {
			action = new ListAction();
		} else if(actionName.equals("view")) {
			action = new ViewAction();
		} else if(actionName.equals("modify")) {
			action = new ModifyAction();
		} else if(actionName.equals("write")) {
			action = new WriteAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}
}
