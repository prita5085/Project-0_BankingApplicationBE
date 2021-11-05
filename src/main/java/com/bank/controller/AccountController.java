package com.bank.controller;

import java.util.List;

import com.bank.app.MyLogger;
import com.bank.exceptions.ResourceNotFoundException;
import com.bank.models.Account;
import com.bank.models.Client;
import com.bank.service.AccountService;
import com.bank.service.ClientService;
import com.bank.util.Transactions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.javalin.http.Handler;

public class AccountController {

	AccountService as;
	ClientService cs;
	Gson gson = new Gson();

	public AccountController(AccountService as) {
		this.as = as;
	}

	// create account

	public Handler createAccount = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Account a = gson.fromJson(ctx.body(), Account.class);
		a = as.createAccount(a, id);
		if (a != null) {
			ctx.result(gson.toJson(a));
			ctx.status(201);
		} else {
			ctx.result("The client with ID: " + id + " was not found");
			ctx.status(404);
		}
	};

	// get all accounts by id

	public Handler getAllAccountsById = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));

		List<Account> allAccounts = as.getAllAccountsById(id);
		if (allAccounts != null) {
			ctx.result(gson.toJson(allAccounts));
		} else {
			ctx.result("The client with ID: " + id + " was not found / No accounts were found");
			ctx.status(404);
		}
	};

	// get all accounts based on amt ranges - check

	public Handler get_All_Accounts_With_Range_Of_Amts = (ctx) -> {

		int cid = Integer.parseInt(ctx.pathParam("cid"));
		int bigAmt = Integer.parseInt(ctx.queryParam("amountLessThan"));
		int smallAmt = Integer.parseInt(ctx.queryParam("amountGreaterThan"));

		List<Account> all = as.get_All_Accounts_With_Range_Of_Amts(bigAmt, smallAmt, cid);
		if (all != null) {
			ctx.result(gson.toJson(all));
		} else {
			ctx.result("The client with ID: " + cid + " was not found / No accounts were found");
			ctx.status(404);
		}
	};

	// get 1 account for 1 client

	public Handler get_Account_By_AccNo_For_Client = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		int no = Integer.parseInt(ctx.pathParam("no"));

		Account a = as.get_Account_By_AccNo_For_Client(no, id);
		if (a != null) {
			ctx.result(gson.toJson(a));
		} else {
			ctx.result("Client with ID: " + id + " was not found / No Accounts were found");
			ctx.status(404);
		}
	};

	// update account

	public Handler update_Account_By_AccNo_For_Client = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		int no = Integer.parseInt(ctx.pathParam("no"));
		Account a = gson.fromJson(ctx.body(), Account.class);
		a.setAccountNo(no);

		a = as.update_Account_By_AccNo_For_Client(a, id);
		if (a != null) {
			ctx.result(gson.toJson(a));
		} else {
			ctx.result("Client with ID: " + id + " was not found / No Accounts were found");
			ctx.status(404);
		}
	};

	// delete Account

	public Handler deleteAccount = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		int no = Integer.parseInt(ctx.pathParam("no"));

		Account a = as.deleteAccount(no, id);
		ctx.result(gson.toJson(a));
		if (a != null) {
			ctx.result(gson.toJson(a));
		} else {
			ctx.result("Account or Client doesn't exist");
			ctx.status(404);
		}
	};

	// withdraw or deposit
	public Handler depositOrWithdraw = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		int no = Integer.parseInt(ctx.pathParam("no"));
		Transactions tr = gson.fromJson(ctx.body(), Transactions.class);
		
			Account a = as.depositOrWithdraw(tr, id, no);
			if (a != null) {
				ctx.result(gson.toJson(a));
			} else {
				ctx.result("Invalid Entry - Insufficient funds Or Negative Value Was Entered");
				ctx.status(422);
			}
	};

	// transfer funds
	public Handler transferFunds = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		int acc1 = Integer.parseInt(ctx.pathParam("acc1"));
		int acc2 = Integer.parseInt(ctx.pathParam("acc2"));
		Transactions tr = gson.fromJson(ctx.body(), Transactions.class);

		List<Account> accounts = as.transferFunds(id, acc1, acc2, tr);
		
		if(accounts != null) {
			ctx.result(gson.toJson(accounts));
		} else {
			ctx.result("Invalid Entry - Insufficient funds Or Negative Value Entered");;
			ctx.status(422);
		}
	};
}
