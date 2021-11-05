package com.bank.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.dao.AccountDao;
import com.bank.exceptions.ResourceNotFoundException;
import com.bank.models.Account;
import com.bank.models.Client;
import com.bank.util.Transactions; 

public class AccountServiceImpl implements AccountService {

	AccountDao dao;
	ClientService cs;
	
	public AccountServiceImpl(AccountDao dao) {
		this.dao = dao;
	}
	
	public Account createAccount(Account account, int cid) {
		return dao.createAccount(account, cid);
	}

	public List<Account> getAllAccountsById(int cid){
		return dao.getAllAccountsById(cid);
	}

	public List<Account> get_All_Accounts_With_Range_Of_Amts(int bigAmt, int smallAmt, int cid) {
		return dao.get_All_Accounts_With_Range_Of_Amts(bigAmt, smallAmt, cid);
	}

	public Account get_Account_By_AccNo_For_Client(int accNo, int cid) {
		return dao.get_Account_By_AccNo_For_Client(accNo, cid);
	}

	public Account update_Account_By_AccNo_For_Client(Account account, int cid) {
		return dao.update_Account_By_AccNo_For_Client(account, cid);
	}

	public Account deleteAccount(int accNo, int cid) {
		return dao.deleteAccount(accNo, cid);
	}

	
	//withdraw or deposit 
	
	@Override
	public Account depositOrWithdraw(Transactions tr, int id, int no) {
		
		Account a = this.get_Account_By_AccNo_For_Client(no, id);
		double currentBalance = a.getBalance();
		double depositAmt = tr.getDeposit();
		double withdrawAmt = tr.getWithdraw();
		
		if(depositAmt > 0) {
			currentBalance = currentBalance + depositAmt;
			return dao.depositOrWithdraw(currentBalance, id, no);
		} else if (withdrawAmt > 0 && withdrawAmt <= currentBalance){
			currentBalance = currentBalance - withdrawAmt;
			return dao.depositOrWithdraw(currentBalance, id, no);
		} else {
			return null;
		}
	}

	// transfer funds - serviceImpl

	public List<Account> transferFunds(int id, int acc1, int acc2, Transactions tr) {
		
		Account account1 = this.get_Account_By_AccNo_For_Client(acc1, id);
		Account account2 = this.get_Account_By_AccNo_For_Client(acc2, id);
		
		if(tr.getAmount() <= account1.getBalance() && tr.getAmount() > 0) {
			
			double newTotal1 = account1.getBalance() - tr.getAmount();
			double newTotal2 = account2.getBalance() + tr.getAmount();
			
			account1.setBalance(newTotal1);
			account2.setBalance(newTotal2);
			
			List<Account> accounts = new ArrayList<Account>();
			accounts.add(account1);
			accounts.add(account2);
			dao.transferFunds(newTotal1, newTotal2, acc1, acc2, id);
			return accounts;
		}
		return null;		
}
}


