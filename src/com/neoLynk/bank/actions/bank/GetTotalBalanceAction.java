package com.neoLynk.bank.actions.bank;

import java.util.Map;
import java.util.UUID;

import com.neoLynk.bank.model.Account;
import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class GetTotalBalanceAction implements Action {

	GetTotalBalanceAction() {}
	
	public static void executeAction(User user, Bank bank) {
		if(user != null) {
			getBalance(user);
		} else {
			Map<UUID, User> listUsers = bank.getListUsers();
			System.out.println("Total balance from all the users in bank " + bank.getBankName() + " is | ");
			for (Map.Entry<UUID, User> entry : listUsers.entrySet()) {
				User userA = entry.getValue();
				getBalance(userA);
			}
			
		}
		Action.setActionName("GetTotalBalance");
	}
	
	private static void getBalance(User user) {
		Map<UUID, Account> listAccounts = user.getListAccountByDoC();
		System.out.println("Account info of the user " + user.getFirstName() + " " + user.getLastName() + " is | ");
		Double totalBalance = new Double(0.0D);
		for (Map.Entry<UUID, Account> entry : listAccounts.entrySet()) {
			Account acc = entry.getValue();
			totalBalance += acc.getBalance();
			System.out.println("UUID for the account is | " + entry.getKey());
			System.out.println("Transactions for this account created on " + acc.getDateOfCreation() + " are " + acc.getListTransactions());
		}
		System.out.println("Total balance for the user " + user.getFirstName() + " " + user.getLastName() + " is | " + totalBalance);
	}
}
