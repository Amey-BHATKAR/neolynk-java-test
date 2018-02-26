package com.neoLynk.bank.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neoLynk.bank.actions.bank.AddBankAction;
import com.neoLynk.bank.actions.bank.ListBankAction;
import com.neoLynk.bank.model.Bank;

public class BankController {
	private static List<List<String>> listBanks = of(of("XYZ Bank", (new Date()).getTime() + "").collect(toList()), of("ABC Bank", ((new Date()).getTime() * (60 * 60 * 24)) + "").collect(toList()), of("LMN Bank", ((new Date()).getTime() * (60 * 60 * 48)) + "").collect(toList())).collect(toList());
	
	public static Bank createOneBankSystem() {
		return AddBankAction.executeAction(listBanks.get(0));
	}
	
	public static List<Bank> createNBankSystem() {
		List<Bank> listBankObjects = new ArrayList<Bank>();
		for(int i = 0; i < listBanks.size(); i++) {
			listBankObjects.add(AddBankAction.executeAction(listBanks.get(i)));
		}
		return listBankObjects;
	}
	
	public static void listBanks() {
		ListBankAction.executeAction();
	}
	
}
