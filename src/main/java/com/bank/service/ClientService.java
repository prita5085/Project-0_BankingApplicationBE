package com.bank.service;

import java.util.List;

import com.bank.models.Client;

public interface ClientService {
	public Client createClient(Client client);
	public List<Client> getAllClients();
	public Client getClientById(int id);
	public Client deleteClient(int id);
	public Client updateClient(Client client);
}
