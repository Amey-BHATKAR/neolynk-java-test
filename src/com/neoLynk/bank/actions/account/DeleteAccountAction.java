package com.neoLynk.bank.actions.account;

import com.neoLynk.bank.model.Account;
import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class DeleteAccountAction implements Action {

	DeleteAccountAction() {}
	
	public static void executeAction(Account acc, Bank bank) {
		User user = bank.getListUsers().get(acc.getIdUser());
		user.removeAccountFromUser(acc);
		Action.setActionName("DeleteAccount");
	}
}
