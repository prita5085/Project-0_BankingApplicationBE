package com.bank.app;

import java.util.List;

import com.bank.dao.AccountDao;
import com.bank.dao.AccountDaoImpl;
import com.bank.dao.ClientDao;
import com.bank.dao.ClientDaoImpl;
import com.bank.models.Account;
import com.bank.models.Client;
import com.bank.service.AccountService;
import com.bank.service.AccountServiceImpl;

public class RepoTest {
	//Using this class to test methods
	public static void main(String[] args) {
		
		ClientDao cd = new ClientDaoImpl();
		AccountDao ad = new AccountDaoImpl();
		
		System.out.println(cd.getClientById(3));
		
//		System.out.println(cd.getAllClients());

//		cd.deleteClient(8);
//		
//		Client c9 = new Client(15,"Pinto",10,"99765");
//		System.out.println(cd.updateClient(c9));

//		Account a = new Account("SmallCheckingAccount", 2000);
//		System.out.println(ad.createAccount(a, 12));
		
//		List<Account> allAccounts = ad.getAllAccountsById(2);
//		System.out.println(allAccounts.toString());
		
//		List<Account> allAccounts = ad.getAllAccountsWithRangeOfAmts(100, 200, 2);
		
//		for(Account a : allAccounts) {
//			System.out.println(a);
//		}
		
//		Account a = ad.get_Account_By_AccNo_For_Client(4, 9);
//		System.out.println(a);
		
//		Account a = new Account(5,"Savings Acct", 55000);
//		System.out.println(ad.update_Account_By_AccNo_For_Client(a, 2));
		
//		ad.deleteAccount(12, 2);
		
		
	}
}
