package com.bank.dao;

import java.sql.*; 
import java.util.ArrayList;
import java.util.List;

import com.bank.app.MyLogger;
import com.bank.exceptions.ResourceNotFoundException;
import com.bank.models.Client;
import com.bank.util.ConnectionDemo;
//Repo Class
public class ClientDaoImpl implements ClientDao {
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	//add client
	public Client createClient(Client client) {
		String str = "insert into clients values(default,?,?,?) RETURNING *";
		try {
			con = ConnectionDemo.getMyConnection();
			pst = con.prepareStatement(str);
			
			pst.setString(1, client.getName());
			pst.setInt(2, client.getAge());
			pst.setString(3, client.getCity());
			 
			rs = pst.executeQuery();
			if(rs.next()) {
				return buildClient(rs);
			}
		}catch(SQLException se) {
			MyLogger.logger.error("Exception occured in createClient()");
		}
		return null;
	}

	//get all clients
	public List<Client> getAllClients() {
		String str = "select * from clients";
		try {
			con = ConnectionDemo.getMyConnection();
			pst = con.prepareStatement(str);
			rs = pst.executeQuery();
			List<Client> allClients = new ArrayList<Client>();
			while(rs.next()) {
					allClients.add(buildClient(rs));
				}
			return allClients;
			}catch(SQLException se) {
			System.out.println(se);
		}
		return null;
	}

	//get client by id
	public Client getClientById(int id) {
		String str = "select * from clients where c_id = ?";
		try {
			con = ConnectionDemo.getMyConnection();
			pst = con.prepareStatement(str);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
                return buildClient(rs);
            }
		}catch(SQLException se) {
			MyLogger.logger.error("Client is not found");
			//System.out.println(se);
		} 
		return null;		
	}

	//delete client by id
	public Client deleteClient(int id) {
		
		String str = "delete from clients where c_id = ? RETURNING *";
		try {
			con = ConnectionDemo.getMyConnection();
			pst = con.prepareStatement(str);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
                return buildClient(rs);
            }
		}catch(SQLException se) {
			MyLogger.logger.error("Exception occured in deleteClient()");
			//System.out.println(se);
		} 
		return null;
	}

	//update client
	public Client updateClient(Client client) {
		String str = "update clients set c_name=?, c_age=?, c_city=? where c_id=? RETURNING *";
		try {
			con = ConnectionDemo.getMyConnection();
			pst = con.prepareStatement(str);
			
			pst.setString(1, client.getName());
			pst.setInt(2, client.getAge());
			pst.setString(3, client.getCity());
			pst.setInt(4, client.getClientId());
			 
			rs = pst.executeQuery();
			if(rs.next()) {
				return buildClient(rs);
			}
		}catch(SQLException se) {
			MyLogger.logger.error("Exception occured in updateClient()");
			//System.out.println(se);
		}
		return null;
	}
	
	//Helper Method
	private Client buildClient(ResultSet rs) throws SQLException{
		Client c = new Client();
		c.setClientId(rs.getInt("c_id"));
		c.setName(rs.getString("c_name"));
		c.setAge(rs.getInt("c_age"));
		c.setCity(rs.getString("c_city"));
		return c;
	}
	
	/*
	 * 2nd way to get client by id:
	 * String str = "select * from clients where c_id=?";
	 * try{
	 * PreparedStatement pst = con.prepareStatement(str);
	 * catch(SQLException se)
	 *set values for placeholders
	 *pst.setInt(1,id);
	 *execute query which returns result set 
	 *ResultSet rs = pst.executeQuery(); //select statement is used. 
	 *Extract results out of result set
	 * Use helper class
	 * if(rs.next()){
	 * return buildClient(rs);
	 * } catch
	 * return null;
	 */

}
