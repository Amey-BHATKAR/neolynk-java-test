package com.neoLynk.bank.actions.account;

import java.util.Map;
import java.util.UUID;

import com.neoLynk.bank.model.Account;
import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class ListAccountAction implements Action {

	ListAccountAction() {}
	
	public static void executeAction(User user, Bank bank) {
		if(user != null) {
			listAccounts(user);
		} else {
			Map<UUID, User> listUsers = bank.getListUsers();
			if(listUsers.size() != 0) {
				System.out.println("List accounts for all the users in bank " + bank.getBankName() + " are | ");
				for (Map.Entry<UUID, User> entry : listUsers.entrySet()) {
					User userA = entry.getValue();
					listAccounts(userA);
				}
			} else {
				System.out.println("Bank " + bank.getBankName() + " has no Users holding any accounts.");
			}
			
		}
		Action.setActionName("ListAccount");
	}
	
	private static void listAccounts(User user) {
		Map<UUID, Account> listAccounts = user.getListAccountByDoC();
		if(listAccounts.size() != 0) {
			System.out.println("List accounts for the user " + user.getFirstName() + " " + user.getLastName() + " are | ");
			for (Map.Entry<UUID, Account> entry : listAccounts.entrySet()) {
				Account acc = entry.getValue();
				System.out.println("UUID for the account is | " + entry.getKey());
				System.out.println("Account created on " + acc.getDateOfCreation() + " holds " + acc.getBalance());
			}
		} else {
			System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " has no accounts.");
		}
	}
}
