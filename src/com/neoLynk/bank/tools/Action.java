package com.neoLynk.bank.tools;

public interface Action {
	//public String getActionName();
	static String setActionName(String actionName) {
		System.out.println(actionName + "Action called.");
		return actionName;
	}
}
