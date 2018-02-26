package com.neoLynk.bank.actions.bank;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class DeleteBankAction implements Action {

	DeleteBankAction(User user) {}
	
	public static void executeAction(Bank bank) {
		Action.setActionName("DeleteBank");
	}
}
