package com.bank.dao;

import java.util.List;

import com.bank.exceptions.ResourceNotFoundException;
import com.bank.models.Account;

public interface AccountDao {
	
	public Account createAccount(Account account, int cid);
	public List<Account> getAllAccountsById(int cid);
	public List<Account> get_All_Accounts_With_Range_Of_Amts(int bigAmt, int smallAmt, int cid);
	public Account get_Account_By_AccNo_For_Client(int accNo, int cid);
	public Account update_Account_By_AccNo_For_Client(Account account, int cid);
	public Account deleteAccount(int accNo, int cid);
	public Account depositOrWithdraw(double currentBalance, int id, int no);
	public List<Account> transferFunds(double newTotal1, double newTotal2, int acc1, int acc2, int id);
		
	}

