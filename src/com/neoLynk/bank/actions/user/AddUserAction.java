package com.neoLynk.bank.actions.user;

import java.util.List;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class AddUserAction implements Action {

	AddUserAction() {}
	
	public static void executeAction(Bank bank, List<String> userInfo) {
		User user = new User(userInfo.get(0).toString(), userInfo.get(1).toString(), userInfo.get(2).toString(), 
								Integer.parseInt(userInfo.get(3).toString()), userInfo.get(4).toString());
		bank.addUserToBank(user);
		Action.setActionName("AddUser");
	}
	
	
	
}
