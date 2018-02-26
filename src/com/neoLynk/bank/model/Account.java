package com.neoLynk.bank.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Account {
	
	private UUID idAccount;
	
	private Long dateOfCreation;
	private Double balance;
	private String typeOfAccount;
	
	private UUID idUser;
	
	private Map<Long, Double> listTransactions = Collections.synchronizedMap(new HashMap<Long, Double>());
	
	public Account(Long dateOfCreation, Double balance, String typeAccount) {
		if(balance >= 0) {
			this.setIdAccount(UUID.randomUUID());
			this.setDateOfCreation(dateOfCreation);
			this.setBalance(balance);
			this.setTypeOfAccount(typeAccount);
		} else {
			System.out.println("You can not start an account with negative balance");
		}
	}

	public UUID getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(UUID idAccount) {
		this.idAccount = idAccount;
	}

	public Long getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Long dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}

	public Map<Long, Double> getListTransactions() {
		return listTransactions;
	}

	public void setListTransactions(Map<Long, Double> listTransactions) {
		this.listTransactions = listTransactions;
	}
	
	public void addTransaction(Long dateOfTransaction, Double amount) {
		if(!this.listTransactions.containsKey(dateOfTransaction)) {
			if(this.checkBalance(amount)) {
				this.listTransactions.put(dateOfTransaction, amount);
				this.setBalance(this.getBalance() + amount);
			}
			
		}
	}
	
	public void cancelTransaction(Long dateOfTransaction, Double amount) {
		if(this.listTransactions.containsKey(dateOfTransaction)) {
			if(this.listTransactions.get(dateOfTransaction).equals(amount)) {
				if(this.checkBalance(amount)) {
					this.listTransactions.remove(dateOfTransaction);
					this.setBalance(this.getBalance() + amount);
				}
			}
		}
	}
	
	public Boolean checkBalance(Double amount) {
		if((this.balance + amount) >= 0) {
			return true;
		} else {
			System.out.println("You do not have enough balance for this transaction.");
			return false;
		}
	}

	public static Account getAccountWithAccountType(String accountType, User user) {
		Map<UUID, Account> listAccounts = user.getListAccountByDoC();
		for (Map.Entry<UUID, Account> entry : listAccounts.entrySet()) {
			Account accountTemp = entry.getValue();
			if(accountTemp.getTypeOfAccount().equals(accountType)) {
				return accountTemp;
			}
		}
		return null;
	}
	
}
