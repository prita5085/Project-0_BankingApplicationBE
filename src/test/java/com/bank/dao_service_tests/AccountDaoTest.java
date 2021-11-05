package com.bank.dao_service_tests;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.dao.AccountDao;
import com.bank.dao.AccountDaoImpl;
import com.bank.dao.ClientDao;
import com.bank.dao.ClientDaoImpl;
import com.bank.models.Account;
import com.bank.models.Client;

public class AccountDaoTest {

	static ClientDao dao = new ClientDaoImpl();
	AccountDao acc_dao = new AccountDaoImpl();
	
	static Client createClient;
	static Client testClient;
	static Account createAccount1;
	static Account createAccount2;
	static Account createAccount3;
	static Account updateAccount; //instaniate with a new test client into the DB in the SetUp
	static Account getAccount;
	static Account deleteAccount;
	static int newId;
	static int no1;
	static int no2;
	static int no3;
	static int acc1;
	static int acc2;
	static int acc3;


	@BeforeAll
	public static void setUp() throws Exception {
	
		createClient = new Client ("Client_1_ForAccountTest", 90, "Test_2");
		testClient = dao.createClient(createClient);
		newId = testClient.getClientId();
		
		createAccount1 = new Account ("Test_CD_Account", 100.00);
		createAccount2 = new Account("Test_Checking", 5000.00);
		createAccount3 = new Account("Test_Saving", 35000.00);
	}

	
	@Test
	public void A_createAccount() {

		Account createdAccount1 = acc_dao.createAccount(createAccount1, newId);
		Account createdAccount2 = acc_dao.createAccount(createAccount2, newId);
		Account createdAccount3 = acc_dao.createAccount(createAccount3, newId);
		
		no1 = createdAccount1.getAccountNo();
		no2 = createdAccount2.getAccountNo();
		no3 = createdAccount3.getAccountNo();
		
		Account checkAcc = acc_dao.get_Account_By_AccNo_For_Client(no1, newId);
		int id1 = checkAcc.getAccountNo();
		
		Assertions.assertEquals(no1, id1);
//		System.out.println(no1);
//		System.out.println(id1);
	}
	
	@Test
	public void B_update_Account_By_AccNo_For_Client_Test() {
		
		Account updateAccount = new Account (no1, "Updating Account_Test", 10000);
		
		Account ua_Test = acc_dao.update_Account_By_AccNo_For_Client(updateAccount, newId);
		Account a2 = acc_dao.get_Account_By_AccNo_For_Client(no1, newId);
		
		Assertions.assertEquals(ua_Test.getAccountNo(), a2.getAccountNo());
//		System.out.println(ua_Test);
//		System.out.println(a2);
	}
	
	@Test
	public void C_getAllAccountsById_Test() {
		List<Account> a3 = acc_dao.getAllAccountsById(newId);
		
		Assertions.assertNotNull(a3);
	}
	
	@Test
	public void D_get_All_Accounts_With_Range_Of_Amts() {
		List<Account> accounts = acc_dao.get_All_Accounts_With_Range_Of_Amts(8000, 100, newId);
		Assertions.assertNotNull(accounts);
	}
	
	@Test
	public void E_get_Account_By_AccNo_For_Client() {
		Account account = acc_dao.get_Account_By_AccNo_For_Client(no2, newId);
		Assertions.assertNotNull(account);
	}
		
	@Test
	public void F_deleteAccountTest() {
		Account a4 = acc_dao.deleteAccount(no1, newId);
		Account a5 = acc_dao.deleteAccount(no2, newId);
		Account a6 = acc_dao.deleteAccount(no3, newId);
		Client c = dao.deleteClient(newId);
//		System.out.println("deleted " + no1);
//		System.out.println("deleted " + no2);
//		System.out.println("deleted " + no3);
//		System.out.println("deleted " + newId);
	}
	
}
