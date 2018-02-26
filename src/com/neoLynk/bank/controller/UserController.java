package com.neoLynk.bank.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

import java.util.List;

import com.neoLynk.bank.actions.bank.GetTotalBalanceAction;
import com.neoLynk.bank.actions.user.AddUserAction;
import com.neoLynk.bank.actions.user.DeleteUserAction;
import com.neoLynk.bank.actions.user.ListUserAction;
import com.neoLynk.bank.actions.user.UpdateUserAction;
import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;

public class UserController {
	private static List<List<String>> listUsers = of(of("A", "B", "C", "18", "12141").collect(toList()), 
														of("X", "Y", "Z", "28", "22213141").collect(toList()), 
														of("L", "M", "N", "48", "448241").collect(toList())).collect(toList());
	
	public static void addUsersToBank(Bank bank) {
		for(int i = 0; i < listUsers.size(); i++) {
			AddUserAction.executeAction(bank, listUsers.get(i));
		}
	}
	
	/* for later when we want to have n banks
	* public static void createNBankSystem() {
		
	}*/
	
	public static void listUsersForTheBank(Bank bank) {
		ListUserAction.executeAction(bank);
	}
	
	// for when the application may have UI, we can work around with objects and IDs, 
	public static void removeUserFromBank(Bank bank, User user) {
		
	}
	
	// but for now we will have work with things like firstName of user
	public static void removeUserFromBank(Bank bank, String userFirstName) {
		User user = User.getUserWithUserFirstName(userFirstName, bank);
		if(user != null) {
			DeleteUserAction.executeAction(bank, user);
		} else {
			System.out.println("User with firstName " + userFirstName + " does not exist in this bank.");
		}
	}
	
	// if decided to update user, but specifically changing a attribute rather than the whole user
	public static void editUserInBank(Bank bank, String userFirstName, List<String> arrayOfNewValues) {
		User user = User.getUserWithUserFirstName(userFirstName, bank);
		if(user != null) {
			if(!(user.getFirstName()).equals(arrayOfNewValues.get(0).toString())){
				UpdateUserAction.executeAction(arrayOfNewValues.get(0).toString(), user.getIdUser(), "firstName", bank);
			} else {
				System.out.println("There was no difference in user's first name. No changes made.");
			}
			if(!(user.getLastName()).equals(arrayOfNewValues.get(1).toString())){
				UpdateUserAction.executeAction(arrayOfNewValues.get(1).toString(), user.getIdUser(), "lastName", bank);
			} else {
				System.out.println("There was no difference in user's last name. No changes made.");
			}
			if(!(user.getFullAddress()).equals(arrayOfNewValues.get(2).toString())){
				UpdateUserAction.executeAction(arrayOfNewValues.get(2).toString(), user.getIdUser(), "fullAddress", bank);
			} else {
				System.out.println("There was no difference in user's full address. No changes made.");
			}
			if(!(user.getAge().toString()).equals(arrayOfNewValues.get(3).toString())){
				UpdateUserAction.executeAction(arrayOfNewValues.get(3).toString(), user.getIdUser(), "age", bank);
			} else {
				System.out.println("There was no difference in user's age. No changes made.");
			}
			if(!(user.getPhoneNbr()).equals(arrayOfNewValues.get(4).toString())){
				UpdateUserAction.executeAction(arrayOfNewValues.get(4).toString(), user.getIdUser(), "phoneNbr", bank);
			} else {
				System.out.println("There was no difference in user's phone number. No changes made.");
			}
		} else {
			System.out.println("User with firstName " + userFirstName + " does not exist in this bank.");
		}
	}
	
	// if decided to update user, without checking if there is a change in the 2 values
	public static void editUserInBankDirectly(Bank bank, String userFirstName, List<String> arrayOfNewValues) {
		User user = User.getUserWithUserFirstName(userFirstName, bank);
		if(user != null) {
			UpdateUserAction.executeAction(arrayOfNewValues, user, bank);
		} else {
			System.out.println("User with firstName " + userFirstName + " does not exist in this bank.");
		}
	}
	
	public static void getTotalBalanceOfUser(String userFirstName, Bank bank) {
		if(!userFirstName.equals("")) {
			User user = User.getUserWithUserFirstName(userFirstName, bank);
			if(user != null) {
				GetTotalBalanceAction.executeAction(user, bank);
			} else {
				System.out.println("User with firstName " + userFirstName + " does not exist in this bank.");
			}
		} else {
			GetTotalBalanceAction.executeAction(null, bank);
		}
	}
	
}
