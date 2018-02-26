package com.neoLynk.bank.actions.bank;

import java.util.List;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.tools.Action;

public class AddBankAction implements Action {

	AddBankAction() {}
	
	public static Bank executeAction(List<String> bankInfo) {
		Bank bank = new Bank(bankInfo.get(0).toString(), Long.parseLong(bankInfo.get(1).toString()));
		Action.setActionName("AddBank");
		return bank;
	}
	
}
