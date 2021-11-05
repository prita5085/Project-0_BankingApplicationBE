package com.bank.dao;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.app.MyLogger;
import com.bank.exceptions.ResourceNotFoundException;
import com.bank.models.Account;
import com.bank.models.Client;
import com.bank.util.ConnectionDemo;

//Repo Class
public class AccountDaoImpl implements AccountDao {
	
	ClientDao cd = new ClientDaoImpl();
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	//create accounts for client with id
	
	public Account createAccount(Account account, int cid) {
		MyLogger.logger.info("Entered createAccount()");
		Client client = cd.getClientById(cid);
		String str = "insert into accounts values(default,?,?,?) RETURNING *";
		try {
			con = ConnectionDemo.getMyConnection();
			pst = con.prepareStatement(str);
	
			pst.setString(1, account.getAccountType());
			pst.setDouble(2, account.getBalance());
			pst.setInt(3, cid);
			 
			rs = pst.executeQuery();
			if(rs.next()) {
				return buildAccount(rs, client);
			}
		}catch(SQLException se) {
			MyLogger.logger.error("Exception occured in createAccount()");
			//System.out.println(se);
		}
		MyLogger.logger.info("Exiting createAccount()");
		return null;
	}
	
		//get all accounts for client - id
	
		public List<Account> getAllAccountsById(int cid) {
			MyLogger.logger.info("Entered getAllAccountsById()");
			String str = "select * from accounts where c_id=?";
			Client client = cd.getClientById(cid);
				if(client == null) {
					return null;
				} else {
			try {
				con = ConnectionDemo.getMyConnection();
				pst = con.prepareStatement(str);
				pst.setInt(1, cid);
				rs = pst.executeQuery();
				List<Account> allAccounts = new ArrayList<Account>();
					while(rs.next()) {
						allAccounts.add(buildAccount(rs, client));
						}
						return allAccounts;
				}catch(SQLException se) {
					MyLogger.logger.error("Exception occured in getAllAccountsById()");
					//System.out.println(se);
				}
				}
			return null;
		}
	
		//get all accounts for client 7 - amt between 400 and 2000
		
		public List<Account> get_All_Accounts_With_Range_Of_Amts(int bigAmt, int smallAmt, int cid) {
			MyLogger.logger.info("Entered get_All_Accounts_With_Range_Of_Amts()");
			String str = "select * from accounts where balance<=? and balance>=? and c_id=?";
			try {
				Client client = cd.getClientById(cid);
				if(client == null) {
					return null;
				}else {
				con = ConnectionDemo.getMyConnection();
				pst = con.prepareStatement(str);

					pst.setInt(1, bigAmt);
					pst.setInt(2, smallAmt);
					pst.setInt(3, cid);
					
				rs = pst.executeQuery();
				List<Account> all = new ArrayList<Account>();
				while(rs.next()) {
						all.add(buildAccount(rs, client));
					}
				return all;
				}
				}catch(SQLException se) {
					MyLogger.logger.error("Exception occured in get_All_Accounts_With_Range_Of_Amts()");
				//System.out.println(se);
			}
			MyLogger.logger.info("Exiting get_All_Accounts_With_Range_Of_Amts()");
			return null;
		}
	
	
	//get account 4 for client 9 
		
		public Account get_Account_By_AccNo_For_Client(int accNo, int cid) {
			MyLogger.logger.info("Entered get_Account_By_AccNo_For_Client()");
			String str = "select * from accounts where acc_no=? and c_id=?";
			Client client = cd.getClientById(cid);
			try {
				con = ConnectionDemo.getMyConnection();
				pst = con.prepareStatement(str);
				pst.setInt(1, accNo);
				pst.setInt(2, cid);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					return buildAccount(rs, client);
				}
				}catch(SQLException se) {
					MyLogger.logger.error("Exception occured in get_Account_By_AccNo_For_Client()");
				//System.out.println(se);
			}
			MyLogger.logger.info("Exiting get_Account_By_AccNo_For_Client()");
			return null;
		}
		
		//update account with id/acc_no 3 for client 10
		
		public Account update_Account_By_AccNo_For_Client(Account account, int cid){
			MyLogger.logger.info("Entered update_Account_By_AccNo_For_Client()");
			Client client = cd.getClientById(cid);
			String str = "update accounts set acc_type=?,balance=? where acc_no=? and c_id=? RETURNING *";
			try {
				con = ConnectionDemo.getMyConnection();
				pst = con.prepareStatement(str);
				
				pst.setString(1, account.getAccountType());
				pst.setDouble(2, account.getBalance());
				pst.setInt(3, account.getAccountNo());
				pst.setInt(4, cid); 
				 
				rs = pst.executeQuery();
				if(rs.next()) {
					return buildAccount(rs,client);
				}
			}catch(SQLException se) {
				MyLogger.logger.error("Exception occured in update_Account_By_AccNo_For_Client()");
				//System.out.println(se);
			}
			MyLogger.logger.info("Exiting update_Account_By_AccNo_For_Client()");
			return null;
		}
		
		
		//delete account 6 for client 15
		
		public Account deleteAccount(int accNo, int cid) {
			Client client = cd.getClientById(cid);
			MyLogger.logger.info("Entered deleteAccount()");
			String str = "delete from accounts where acc_no=? and c_id=? RETURNING *";
			try {
				con = ConnectionDemo.getMyConnection();
				pst = con.prepareStatement(str);
				pst.setInt(1, accNo);
				pst.setInt(2, cid);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					return buildAccount(rs,client);
				}
				System.out.println(rs);
			}catch(SQLException se) {
				MyLogger.logger.error("Exception occured in deleteAccount()");
				//System.out.println(se);
			} 
			MyLogger.logger.info("Exiting deleteAccount()");
			return null;
		}
		
		//deposit or withdraw
		
		public Account depositOrWithdraw(double currentBalance, int id, int no){
			MyLogger.logger.info("Entered depositOrWithdraw()");
			Client client = cd.getClientById(id);
			String str = "update accounts set balance=? where acc_no=? and c_id=? RETURNING *";
			try {
				con = ConnectionDemo.getMyConnection();
				pst = con.prepareStatement(str);
				
				pst.setDouble(1, currentBalance);
				pst.setInt(2, no);
				pst.setInt(3, id);
				 
				rs = pst.executeQuery();
				if(rs.next()) {
					return buildAccount(rs,client);
				}
			}catch(SQLException se) {
				MyLogger.logger.error("Exception occured in depositOrWithdraw()");
				//System.out.println(se);
			}
			MyLogger.logger.info("Exiting depositOrWithdraw()");

			return null;
		}
		

		//transfer funds 
		
		public List<Account> transferFunds(double newTotal1, double newTotal2, int acc1, int acc2, int id){
			MyLogger.logger.info("Entered transferFunds()");
			Client client = cd.getClientById(id);
			String str = "update accounts set balance = case when acc_no = ? THEN ? WHEN acc_no = ? THEN ? ELSE balance END RETURNING *";
			try { 
				con = ConnectionDemo.getMyConnection();
				pst = con.prepareStatement(str);
				
				pst.setInt(1, acc1);
				pst.setDouble(2, newTotal1);
				pst.setInt(3, acc2);
				pst.setDouble(4, newTotal2);
				rs = pst.executeQuery();
				
				List<Account> all = new ArrayList<Account>();
				if(rs.next()) {
					 all.add(buildAccount(rs,client));
				}
				return all;
				
			}catch(SQLException se) {
				MyLogger.logger.error("Exception occured in transferFunds()");
				//System.out.println(se);
			}
			MyLogger.logger.info("Exiting transferFunds()");

			return null;
		}
		
			
	//Helper Method
		private Account buildAccount(ResultSet rs, Client client) throws SQLException{
			Account a = new Account();
			a.setAccountNo(rs.getInt("acc_no"));
			a.setAccountType(rs.getString("acc_type"));
			a.setBalance(rs.getDouble("balance"));
			a.setClient(client);
			return a;
		}
}
