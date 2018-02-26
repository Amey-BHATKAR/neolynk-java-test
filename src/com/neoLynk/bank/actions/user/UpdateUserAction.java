package com.neoLynk.bank.actions.user;

import java.util.List;
import java.util.UUID;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.model.User;
import com.neoLynk.bank.tools.Action;

public class UpdateUserAction implements Action {
	
	UpdateUserAction() {}
	
	// if decided to update user, but specifically changing a attribute rather than the whole user
	public static void executeAction(String modifiedValue, UUID idUser, String whatToChange, Bank bank) {
		switch (whatToChange) {
		case "firstName":
			updateFirstName(idUser, modifiedValue, bank);
			break;
		case "lastName":
			updateLastName(idUser, modifiedValue, bank);
			break;
		case "fullAddress":
			updateFullAddress(idUser, modifiedValue, bank);
			break;
		case "age":
			updateAge(idUser, Integer.parseInt(modifiedValue), bank);
			break;
		case "phoneNbr":
			updatePhoneNbr(idUser, modifiedValue, bank);
			break;
		default:
			break;
		}
		Action.setActionName("UpdateUser");
	}
	
	// if decided to update user, without checking if there is a change in the 2 values
	public static void executeAction(List<String> arrayOfNewValues, User user, Bank bank) {
		UUID idUser = user.getIdUser();
		updateFirstName(idUser, arrayOfNewValues.get(0).toString(), bank);
		updateLastName(idUser, arrayOfNewValues.get(1).toString(), bank);
		updateFullAddress(idUser, arrayOfNewValues.get(2).toString(), bank);
		updateAge(idUser, Integer.parseInt(arrayOfNewValues.get(3).toString()), bank);
		updatePhoneNbr(idUser, arrayOfNewValues.get(4).toString(), bank);
		Action.setActionName("UpdateUser");
	}
	
	private static void updateFirstName(UUID idUser, String firstName, Bank bank) {
		bank.getListUsers().get(idUser).setFirstName(firstName);
	}
	
	private static void updateLastName(UUID idUser, String lastName, Bank bank) {
		bank.getListUsers().get(idUser).setLastName(lastName);
	}
	
	private static void updateFullAddress(UUID idUser, String fullAddress, Bank bank) {
		bank.getListUsers().get(idUser).setFullAddress(fullAddress);
	}
	
	private static void updateAge(UUID idUser, Integer age, Bank bank) {
		bank.getListUsers().get(idUser).setAge(age);
	}
	
	private static void updatePhoneNbr(UUID idUser, String phoneNbr, Bank bank) {
		bank.getListUsers().get(idUser).setPhoneNbr(phoneNbr);
	}
	
}
