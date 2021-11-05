package com.bank.app;
 
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bank.controller.AccountController;
import com.bank.controller.ClientController;
import com.bank.dao.AccountDao;
import com.bank.dao.AccountDaoImpl;
import com.bank.dao.ClientDao;
import com.bank.dao.ClientDaoImpl;
import com.bank.models.Account;
import com.bank.models.Client;
import com.bank.service.AccountService;
import com.bank.service.AccountServiceImpl;
import com.bank.service.ClientService;
import com.bank.service.ClientServiceImpl;
import com.bank.util.ConnectionDemo;

import io.javalin.Javalin;

public class App {
	public static void main(String[] args) 
	{
		//Establish our Javalin Object
		Javalin app = Javalin.create();
		//Establish routes/endpoints
		establishRoutes(app);
		//Run Javalin
		app.start();
	}

	private static void establishRoutes(Javalin app) {
		app.get("/hello", (ctx) -> ctx.result("Hello World"));
		
		ClientDao cd = new ClientDaoImpl();
		ClientService cs = new ClientServiceImpl(cd);
		ClientController cc = new ClientController(cs); 
		
		app.get("/clients", cc.getAllClients);
		app.get("/clients/:id", cc.getClientById);
		app.post("/clients", cc.createClient);
		app.put("/clients/:id", cc.updateClient);
		app.delete("/clients/:id", cc.deleteClient);
		
		AccountDao ad = new AccountDaoImpl();
		AccountService as = new AccountServiceImpl(ad);
		AccountController ac = new AccountController(as);
		
		app.post("/clients/:id/accounts", ac.createAccount);
		app.get("/clients/:id/accounts", ac.getAllAccountsById);
		app.get("/clients/:cid/accounts/ranges", ac.get_All_Accounts_With_Range_Of_Amts);
		app.get("/clients/:id/accounts/:no", ac.get_Account_By_AccNo_For_Client);
		app.put("/clients/:id/accounts/:no", ac.update_Account_By_AccNo_For_Client);
		app.delete("/clients/:id/accounts/:no", ac.deleteAccount);
		app.patch("/clients/:id/accounts/:no", ac.depositOrWithdraw);
		app.patch("/clients/:id/accounts/:acc1/transfer/:acc2", ac.transferFunds);
	} 
}
