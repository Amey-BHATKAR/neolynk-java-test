package com.neoLynk.bank.actions.bank;

import com.neoLynk.bank.model.Bank;
import com.neoLynk.bank.tools.Action;

public class UpdateBankAction implements Action {
	
	UpdateBankAction() {}
	
	public static void executeAction(Bank bank) {
		Action.setActionName("UpdateBank");
	}
	
}
