package com.neoLynk.bank.actions.account;

import java.util.Date;

import com.neoLynk.bank.model.Account;
import com.neoLynk.bank.tools.Action;

public class UpdateAccountAction implements Action {
	
	UpdateAccountAction() {}
	
	public static void executeAction(Double amount, Account account) {
		account.addTransaction((new Date()).getTime(), amount);
		Action.setActionName("UpdateAccount");
	}
	
}
