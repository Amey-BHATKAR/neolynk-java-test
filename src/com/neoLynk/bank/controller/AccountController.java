package com.neoLynk.bank.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.neoLynk.bank.actions.account.AddAccountAction;
import com.neoLynk.bank.actions.account.DeleteAccountAction;
import com.neoLynk.bank.actions.account.ListAccountAction;
import com.neoLynk.bank.actions.account.UpdateAccountAction;
import com.neoLynk.bank.model.Account;
import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;

public class AccountController {
	private static List<String> listAccounts = of("typeA", "typeB", "typeC").collect(toList());
	
	public static void addAccountsToUser(User user) {
		if(user != null) {
			for(int i = 0; i < listAccounts.size(); i++) {
				AddAccountAction.executeAction(user, listAccounts.get(i));
			}
		} else {
			System.out.println("User does not exist");
		}
	}
	
	public static void createNBankSystem() {
		
	}
	
	public static void listAccountsForUser(String userName, Bank bank) {
		if(userName != "") {
			Map<UUID, User> listUsers = bank.getListUsers();
			for (Map.Entry<UUID, User> entry : listUsers.entrySet()) {
				User user = entry.getValue();
				if(user.getFirstName().equals(userName)) {
					ListAccountAction.executeAction(user, bank);
				}
			}
		} else {
			ListAccountAction.executeAction(null, bank);
		}
	}
	
	public static void listAccountsInBank(Bank bank) {
		ListAccountAction.executeAction(null, bank);
	}
	
	public static void removeAccountFromUser(Bank bank, String userFirstName, String accountType) {
		User user = User.getUserWithUserFirstName(userFirstName, bank);
		if(user != null) {
			Account acc = Account.getAccountWithAccountType(accountType, user);
			if(acc != null) {
				DeleteAccountAction.executeAction(acc, bank);
			} else {
				System.out.println("User with firstName " + userFirstName + " does not have " + accountType + " type of account.");
			}
		} else {
			System.out.println("User with firstName " + userFirstName + " does not exist in this bank.");
		}
		
	}
	
	public static void depositToAccount(Bank bank, String userFirstName, String accountType, Double amount) {
		User user = User.getUserWithUserFirstName(userFirstName, bank);
		if(user != null) {
			Account acc = Account.getAccountWithAccountType(accountType, user);
			if(acc != null) {
				UpdateAccountAction.executeAction(amount, acc);
			} else {
				System.out.println("User with firstName " + userFirstName + " does not have " + accountType + " type of account.");
			}
		} else {
			System.out.println("User with firstName " + userFirstName + " does not exist in this bank.");
		}
	}
	
	public static void withdrawFromAccount(Bank bank, String userFirstName, String accountType, Double amount) {
		User user = User.getUserWithUserFirstName(userFirstName, bank);
		if(user != null) {
			Account acc = Account.getAccountWithAccountType(accountType, user);
			if(acc != null) {
				UpdateAccountAction.executeAction(-amount, acc);
			} else {
				System.out.println("User with firstName " + userFirstName + " does not have " + accountType + " type of account.");
			}
		} else {
			System.out.println("User with firstName " + userFirstName + " does not exist in this bank.");
		}
	}
	
}
