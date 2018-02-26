package com.neoLynk.bank.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankControllerTest {

	@Test
	public void createOneBankSystemTest() {
		assertTrue(BankController.createOneBankSystem() != null);
	}
	
	@Test
	public void createNBankSystemTest() {
		assertTrue(BankController.createNBankSystem() != null);
	}

}
