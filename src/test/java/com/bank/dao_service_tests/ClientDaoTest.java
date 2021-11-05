package com.bank.dao_service_tests;

import java.util.List;   

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.dao.ClientDao;
import com.bank.dao.ClientDaoImpl;
import com.bank.models.Client;

public class ClientDaoTest {
	

	ClientDao dao = new ClientDaoImpl();
	
	static Client createClient;
	static Client updateClient; //instaniate with a new test client into the DB in the SetUp
	static Client getClient;
	Client deleteClient;
	static int newId;


	@BeforeAll
	public static void setUp() throws Exception {
		createClient = new Client ("TestClient_1", 90, "Test_2");
	}

	@Test
	public void getAllClientsTest() {
		List<Client> clients = dao.getAllClients();
		
		Assertions.assertNotNull(clients); //Makes sure that clients is not null.
		Assertions.assertNotEquals(0, clients.size());	
	}
	
	@Test
	public void A_createClientTest() {
		
		Client createdClient = dao.createClient(createClient);
		newId = createdClient.getClientId();
		
		Client c1 = dao.getClientById(newId);
		int id1 = c1.getClientId();
		
		Assertions.assertEquals(newId, id1);
//		System.out.println(newId);
//		System.out.println(c1);
	}
	
	@Test
	public void C_getClientByIdTest() {
		Client c3 = dao.getClientById(43);
		Assertions.assertNotNull(c3);
	}
	
	@Test
	public void D_deleteClientTest() {
		
		Client c4 = dao.deleteClient(newId);
		//System.out.println("deleted " + newId);
	}
	
	@Test
	public void B_updateClientTest() {
		updateClient = new Client (newId,"Pearl_Test", 80, "UpdateTest 4");
		Client uc_Test = dao.updateClient(updateClient);
		Client c2 = dao.getClientById(uc_Test.getClientId());
		
		Assertions.assertEquals(uc_Test.getClientId(), c2.getClientId());
		
//		System.out.println(uc_Test);
//		System.out.println(c2);
	}
	
	
}
