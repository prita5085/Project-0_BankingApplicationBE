package com.bank.dao_service_tests;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.dao.ClientDao;
import com.bank.dao.ClientDaoImpl;
import com.bank.models.Client;
import com.bank.service.ClientService;
import com.bank.service.ClientServiceImpl;

public class ClientServiceTest {
	
	ClientDao dao = new ClientDaoImpl();
	ClientService cs = new ClientServiceImpl(dao);
	
	static Client createClient;
	static Client updateClient; 
	static Client getClient;
	Client deleteClient;
	static int newId;


	@BeforeAll
	public static void setUp() throws Exception {
		createClient = new Client ("TestClient_1", 90, "Test_2");
		updateClient = new Client (35,"Pearl_Test", 39, "Test 3");
	}

	@Test
	public void getAllClientsTest() {
		List<Client> clients = cs.getAllClients();
		
		Assertions.assertNotNull(clients); //Makes sure that clients is not null.
		Assertions.assertNotEquals(0, clients.size());	
	}
	
	@Test
	public void A_createClientTest() {
		
		Client createdClient = cs.createClient(createClient);
		newId = createdClient.getClientId();
		
		Client c1 = cs.getClientById(newId);
		int id1 = c1.getClientId();
		
		Assertions.assertEquals(createdClient.getClientId(), id1);
//		System.out.println(newId);
//		System.out.println(c1);
	}
	
	@Test
	public void C_getClientByIdTest() {
		Client c3 = cs.getClientById(43);
		Assertions.assertNotNull(c3);
	}
	
	@Test
	public void D_deleteClientTest() {

		Client c4 = cs.deleteClient(newId);
//		System.out.println("deleted " + newId);
			
	}
	
	@Test
	public void B_updateClientTest() {
		Client uc_Test = cs.updateClient(updateClient);
		Client c2 = cs.getClientById(uc_Test.getClientId());
		
		Assertions.assertEquals(uc_Test.getClientId(), c2.getClientId());
//		System.out.println(uc_Test);
//		System.out.println(c2);
	}
}
