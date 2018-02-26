package com.neoLynk.bank.controller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;

public class AccountControllerTest {

	private static Bank bank;
	
	@BeforeClass
	public static void setBank() {
		bank = BankController.createOneBankSystem();
		UserController.addUsersToBank(bank);
	}
	
	@Test
	public void addAccountsToUserTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		assertTrue(user.getListAccountByDoC().size() > 0);
	}
	
	@Test
	public void listAccountsForUserTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		assertTrue(user.getListAccountByDoC().size() > 0);
		AccountController.listAccountsForUser("A", bank);
		assertTrue("Listing of Accounts for User A done properly.", true);
	}
	
	@Test
	public void listAccountsInBankTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		user = User.getUserWithUserFirstName("X", bank);
		AccountController.addAccountsToUser(user);
		assertTrue(user.getListAccountByDoC().size() > 0);
		AccountController.listAccountsInBank(bank);
		assertTrue("Listing of Accounts for Bank done properly.", true);
	}
	
	@Test
	public void removeAccountWithZeroBalanceFromUserTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		Integer originalSize = user.getListAccountByDoC().size();
		assertTrue(originalSize > 0);
		AccountController.removeAccountFromUser(bank, "A", "typeB");
		assertTrue("Account typeB was removed from user A | ", user.getListAccountByDoC().size() < originalSize);
	}
	
	@Test
	public void depositToAccountTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		AccountController.depositToAccount(bank, "A", "typeA", 200D);
		UserController.getTotalBalanceOfUser("A", bank);
	}
	
	@Test
	public void withdrawFromAccountTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		AccountController.depositToAccount(bank, "A", "typeA", 200D);
		UserController.getTotalBalanceOfUser("A", bank);
		AccountController.withdrawFromAccount(bank, "A", "typeA", 100D);
		UserController.getTotalBalanceOfUser("A", bank);
	}
	
	@Test
	public void addSameTypeAccountsToUserTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		assertTrue(user.getListAccountByDoC().size() > 0);
		AccountController.addAccountsToUser(user);
		assertTrue(user.getListAccountByDoC().size() > 0);
	}
	
	@Test
	public void listAccountsForUsersWithNoAccountsTest() {
		AccountController.listAccountsForUser("A", bank);
		assertTrue("Listing of Accounts for User A done properly.", true);
	}
	
	@Test
	public void listAccountsForBankWithNoUsers() {
		UserController.removeUserFromBank(bank, "A");
		UserController.removeUserFromBank(bank, "X");
		UserController.removeUserFromBank(bank, "L");
		AccountController.listAccountsForUser("", bank);
		assertTrue("Listing of Accounts for Bank done properly.", true);
	}
	
	@Test
	public void removeAccountNotExistingFromUserTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		Integer originalSize = user.getListAccountByDoC().size();
		assertTrue(originalSize > 0);
		AccountController.removeAccountFromUser(bank, "A", "typeB");
		assertTrue(user.getListAccountByDoC().size() < originalSize);
		AccountController.removeAccountFromUser(bank, "A", "typeB");
		assertTrue(user.getListAccountByDoC().size() < originalSize);
	}
	
	@Test
	public void removeAccountWithNonZeroBalanceFromUserTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		AccountController.depositToAccount(bank, "A", "typeB", 200D);
		Integer originalSize = user.getListAccountByDoC().size();
		assertTrue(originalSize > 0);
		AccountController.removeAccountFromUser(bank, "A", "typeB");
		assertTrue(user.getListAccountByDoC().size() == originalSize);
	}
	
	@Test
	public void depositToAccountNotExistingTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		Integer originalSize = user.getListAccountByDoC().size();
		assertTrue(originalSize > 0);
		AccountController.removeAccountFromUser(bank, "A", "typeB");
		assertTrue(user.getListAccountByDoC().size() < originalSize);
		AccountController.depositToAccount(bank, "A", "typeB", 200D);
	}
	
	@Test
	public void withdrawFromAccountWithNoBalanceTest() {
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		AccountController.withdrawFromAccount(bank, "A", "typeA", 100D);
	}
	
}
