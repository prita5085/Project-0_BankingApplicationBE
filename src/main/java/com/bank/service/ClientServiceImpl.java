package com.bank.service;

import java.util.List;


import com.bank.dao.ClientDao;
import com.bank.models.Client;

public class ClientServiceImpl implements ClientService {
	
	ClientDao dao;
	
	public ClientServiceImpl(ClientDao dao) {
		this.dao = dao;
	}

	public Client createClient(Client client) {
		return dao.createClient(client);
	}

	public List<Client> getAllClients() {
		return dao.getAllClients();
	}

	public Client getClientById(int id) {
		return dao.getClientById(id);
	}

	public Client deleteClient(int id) {
		return dao.deleteClient(id);
	}

	public Client updateClient(Client client) {
		return dao.updateClient(client); 
	}
}

