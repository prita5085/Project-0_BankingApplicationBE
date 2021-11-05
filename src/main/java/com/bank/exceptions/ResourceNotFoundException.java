package com.bank.exceptions;

public class ResourceNotFoundException extends Exception {
	
	public String toString() {
		return "Client or Account is not found";
	}
}
