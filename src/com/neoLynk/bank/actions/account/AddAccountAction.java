package com.neoLynk.bank.actions.account;

import java.util.Date;
import java.util.Random;

import com.neoLynk.bank.model.Account;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class AddAccountAction implements Action {

	AddAccountAction() {}
	
	public static void executeAction(User user, String typeAccount) {
		System.out.println("typeAccount --------------- " + typeAccount);
		Account account = new Account(((new Date()).getTime()) * (new Random().ints(1, 9).limit(1).findFirst().getAsInt()), 0.0D, typeAccount);
		user.addAccountToUser(account);
		Action.setActionName("AddAccount");
	}
	
}
