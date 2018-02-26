package com.neoLynk.bank.actions.user;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class DeleteUserAction implements Action {

	DeleteUserAction(User user) {}
	
	public static void executeAction(Bank bank, User user) {
		bank.removeUserFromBank(user);
		Action.setActionName("DeleteUser");
	}
}
