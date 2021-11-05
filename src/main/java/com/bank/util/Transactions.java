package com.bank.util;

import com.bank.models.Account;

public class Transactions extends Account {
	private double deposit;
	private double withdraw;
	private double amount;
	
	public Transactions() { }
	
	public Transactions(double deposit, double withdraw) {
		super();
		this.deposit = deposit;
		this.withdraw = withdraw;
	}

	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transactions [deposit=" + deposit + ", withdraw=" + withdraw + ", amount=" + amount + "]";
	}

	
	


	

	
}
