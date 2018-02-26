package com.neoLynk.bank.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
	
	private UUID idUser;

	private String firstName;
	private String lastName;
	private String fullAddress;
	private Integer age;
	private String phoneNbr;
	
	private Map<UUID, Account> listAccounts = Collections.synchronizedMap(new HashMap<UUID, Account>());
	
	public User(String firstName, String lastName, String fullAddress, Integer age, String phoneNbr) {
		this.setIdUser(UUID.randomUUID());
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setFullAddress(fullAddress);
		this.setAge(age);
		this.setPhoneNbr(phoneNbr);
	}

	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	public Map<UUID, Account> getListAccountByDoC() {
		return listAccounts;
	}

	public void setListAccountByDoC(Map<UUID, Account> listAccountByDoC) {
		this.listAccounts = listAccountByDoC;
	}
	
	public void addAccountToUser(Account account) {
		Account accountA = Account.getAccountWithAccountType(account.getTypeOfAccount(), this);
		if(accountA == null) {
			this.listAccounts.put(account.getIdAccount(), account);
			account.setIdUser(this.idUser);
		} else {
			System.out.println("User " + this.getFirstName() + " " + this.getLastName() + " already has account of type " + account.getTypeOfAccount());
		}
	}
	
	public void removeAccountFromUser(Account account) {
		if(this.listAccounts.containsKey(account.getIdAccount())) {
			if(this.checkBalance(account)) {
				this.listAccounts.remove(account.getIdAccount());
			}
		}
	}
	
	public void removeAccountFromUser(UUID idAccount) {
		if(this.listAccounts.containsKey(idAccount)) {
			Account account = this.listAccounts.get(idAccount);
			if(this.checkBalance(account)) {
				this.listAccounts.remove(idAccount);
			}
		}
	}
	
	private Boolean checkBalance(Account account) {
		if(account.getBalance() == 0) {
			return true;
		} else {
			System.out.println("You need to empty account of type " + account.getTypeOfAccount() + " before deleting it.");
			return false;
		}
	}
	
	public static User getUserWithUserFirstName(String firstName, Bank bank) {
		User user = null;
		Map<UUID, User> listUsers = bank.getListUsers();
		for (Map.Entry<UUID, User> entry : listUsers.entrySet()) {
			User userTemp = entry.getValue();
			if(userTemp.getFirstName().equals(firstName)) {
				user = userTemp;
				break;
			}
		}
		return user;
	}
}
