package com.neoLynk.bank.actions.user;

import java.util.Map;
import java.util.UUID;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class ListUserAction implements Action {

	ListUserAction() {}
	
	public static void executeAction(Bank bank) {
		Map<UUID, User> listUsers = bank.getListUsers();
		for (Map.Entry<UUID, User> entry : listUsers.entrySet()) {
			User user = entry.getValue();
			System.out.println("UUID for the user is | " + entry.getKey());
			System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " currently holds " 
								+ user.getListAccountByDoC().size() + " accounts in " + bank.getBankName());
		}
		Action.setActionName("ListUser");
	}
}
