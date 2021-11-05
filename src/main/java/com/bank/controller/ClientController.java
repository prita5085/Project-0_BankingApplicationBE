package com.bank.controller;

import java.util.List; 
import com.bank.models.Client;
import com.bank.service.ClientService;
import com.google.gson.Gson;

import io.javalin.http.Handler;

public class ClientController {
	
	ClientService cs;
	Gson gson = new Gson();
	
	public ClientController(ClientService cs) {
		this.cs = cs;
	}
    
    public Handler createClient = (ctx) -> {
    	Client c = gson.fromJson(ctx.body(), Client.class);
    	c = cs.createClient(c);
    	if(c !=null) {
    		ctx.result(gson.toJson(c));
    		ctx.status(201);
    	}
    	
    };
		
	public Handler getAllClients = (ctx) -> {
		List<Client> allClients = cs.getAllClients();
		if(allClients != null) {
			ctx.result(gson.toJson(allClients));
		} else {
			ctx.status(404);
		}
		//ctx.result(gson.toJson(allClients));
	};
	
	public Handler getClientById = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		 
		Client c = cs.getClientById(id);
		if(c != null) {
			ctx.result(gson.toJson(c));
		} else {
			ctx.result("The client with ID: " + id + " was not found");
			ctx.status(404);
		}
	};
	
	public Handler updateClient = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client c = gson.fromJson(ctx.body(), Client.class);
        
        c = cs.updateClient(c);
        	if(c != null) {
            	ctx.result(gson.toJson(c));
            }else {
             ctx.result("The client with ID: " + id + " was not found"); 
             ctx.status(404);        
            }
    };
    
    public Handler deleteClient = (ctx) -> { 
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client c = cs.deleteClient(id);
        if(c != null) {
        	ctx.result("Client with Id: " + id + " is deleted");
        	ctx.status(204);
        }else {
         ctx.result("The client with ID: " + id + " was not found"); 
         ctx.status(404);        
        }    
    };
}

