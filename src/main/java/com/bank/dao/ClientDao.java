package com.bank.dao;

import java.util.List; 

import com.bank.models.Client;

public interface ClientDao {
	
	public Client createClient(Client client);
	public List<Client> getAllClients();
	public Client getClientById(int id);
	public Client deleteClient(int id);
	public Client updateClient(Client client);
}
