package com.neoLynk.bank;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.neoLynk.bank.actions.user.AddUserAction;
import com.neoLynk.bank.actions.user.DeleteUserAction;
import com.neoLynk.bank.controller.AccountController;
import com.neoLynk.bank.controller.BankController;
import com.neoLynk.bank.controller.UserController;
import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;

public class MainController {

	private static Bank bank;
	// if want a list of banks
	//public static List<Bank> listBanks = BankController.createNBankSystem();
	
	public static void main(String[] args) {
		System.out.println("Wassup!!!!");
		// create bank
		bank = BankController.createOneBankSystem();
		// create users in this bank
		UserController.addUsersToBank(bank);
		// create accounts for each user of this bank
		for (Map.Entry<UUID, User> entry : bank.getListUsers().entrySet()) {
			AccountController.addAccountsToUser(entry.getValue());
		}
		System.out.println("Total list of Users in " + bank.getBankName() + " are | " + (bank.getListUsers().size()));
		BankController.listBanks();
		UserController.listUsersForTheBank(bank);
		AccountController.listAccountsForUser("A", bank);
		AccountController.listAccountsInBank(bank);
		//DeleteUserAction.executeAction(bank.getListUsers().get(0));
		//System.out.println("Total list of Users in " + bank.getBankName() + " are | " + (bank.getListUsers().size()));
	}
	
	

}
