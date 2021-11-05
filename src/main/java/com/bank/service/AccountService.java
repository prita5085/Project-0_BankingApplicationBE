package com.bank.service;

import java.util.List;

import com.bank.exceptions.ResourceNotFoundException;
import com.bank.models.Account;
import com.bank.util.Transactions;

public interface AccountService {
	
	public Account createAccount(Account account, int cid);
	public List<Account> getAllAccountsById(int cid);
	public List<Account> get_All_Accounts_With_Range_Of_Amts(int bigAmt, int smallAmt, int cid);
	public Account get_Account_By_AccNo_For_Client(int accNo, int cid);
	public Account update_Account_By_AccNo_For_Client(Account account, int cid);
	public Account deleteAccount(int accNo, int cid);
	public Account depositOrWithdraw(Transactions tr, int id, int no);
	public List<Account> transferFunds(int id, int acc1, int acc2, Transactions tr);
	
}
