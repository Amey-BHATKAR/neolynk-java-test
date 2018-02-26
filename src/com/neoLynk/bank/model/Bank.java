package com.neoLynk.bank.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bank {

	private String bankName;
	private Long dateOfCreation;
	
	private Map<UUID, User> listUsers = Collections.synchronizedMap(new HashMap<UUID, User>());
	
	public Bank(String bankName, Long dateOfCreation) {
		this.setBankName(bankName);
		this.setDateOfCreation(dateOfCreation);
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Long dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public Map<UUID, User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(Map<UUID, User> listUsers) {
		this.listUsers = listUsers;
	}
	
	public void addUserToBank(User user) {
		User userA = User.getUserWithUserFirstName(user.getFirstName(), this);
		if(userA == null) {
			this.listUsers.put(user.getIdUser(), user);
		} else {
			System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " already exists.");
		}
	}
	
	public void removeUserFromBank(User user) {
		if(this.listUsers.containsKey(user.getIdUser())) {
			if(isUserRemovable(user)){
				this.listUsers.remove(user.getIdUser());
			} else {
				System.out.println("One of this user's account has non zero balance. User can't be removed.");
			}
		}
	}
	
	public void removeUserFromBank(UUID idUser) {
		if(this.listUsers.containsKey(idUser)) {
			this.listUsers.remove(idUser);
		}
	}
	
	private static Boolean isUserRemovable(User user) {
		Boolean isRemovable = true;
		if(user.getListAccountByDoC().size() != 0) {
			Map<UUID, Account> listAccounts = user.getListAccountByDoC();
			for (Map.Entry<UUID, Account> entry : listAccounts.entrySet()) {
				Account account = entry.getValue();
				if(account.getBalance() > 0) {
					isRemovable = false;
				}
			}
		}
		return isRemovable;
	}
}
