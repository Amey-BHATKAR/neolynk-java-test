package com.neoLynk.bank.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;

public class UserControllerTest {
	
	private static Bank bank;
	
	@BeforeClass
	public static void setBank() {
		bank = BankController.createOneBankSystem();
	}
	
	@Test
	public void addUsersToBankTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void listUsersForTheBankTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		UserController.listUsersForTheBank(bank);
		assertTrue("Listing of users done properly.", true);
	}

	@Test
	public void removeUserWithNoAccountsFromBankTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		UserController.removeUserFromBank(bank, "A");
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void editUserInBankTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		UserController.editUserInBank(bank, "A", of("E", "D", "G", "98", "44132141").collect(toList()));
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void editUserInBankDirectlyTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		UserController.editUserInBank(bank, "A", of("E", "D", "G", "98", "44132141").collect(toList()));
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void addExistingUserTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void editNonExistingUserTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		UserController.removeUserFromBank(bank, "A");
		assertTrue(bank.getListUsers().size() > 0);
		UserController.editUserInBank(bank, "A", of("E", "D", "G", "98", "44132141").collect(toList()));
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void removeNonExistingUserTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		UserController.removeUserFromBank(bank, "A");
		assertTrue(bank.getListUsers().size() > 0);
		UserController.removeUserFromBank(bank, "A");
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void removeUserWithAccountsButZeroBalanceTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		// add some accounts to this user
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		UserController.removeUserFromBank(bank, "A");
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void removeUserWithAccountButNonZeroBalanceTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		// add some accounts to this user
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		// add a transaction or two in this users accounts
		AccountController.depositToAccount(bank, "A", "typeA", 200D);
		UserController.removeUserFromBank(bank, "A");
		assertTrue(bank.getListUsers().size() > 0);
	}
	
	@Test
	public void getTotalBalanceOfUserTest() {
		UserController.addUsersToBank(bank);
		assertTrue(bank.getListUsers().size() > 0);
		// add some accounts to this user
		User user = User.getUserWithUserFirstName("A", bank);
		AccountController.addAccountsToUser(user);
		// add a transaction or two in this users accounts
		AccountController.depositToAccount(bank, "A", "typeA", 200D);
		UserController.getTotalBalanceOfUser("A", bank);
		AccountController.depositToAccount(bank, "A", "typeB", 200D);
		UserController.getTotalBalanceOfUser("A", bank);
		AccountController.withdrawFromAccount(bank, "A", "typeB", 100D);
		UserController.getTotalBalanceOfUser("A", bank);
		assertTrue(bank.getListUsers().size() > 0);
	}
	
}
