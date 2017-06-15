package com.eservice.core.services;

import java.util.Map;

import com.eservice.core.beans.User;

public interface AccountService {
	
	public Map addCustomer(User user);

	public Map updateCustomer(User user);
	
	public User getCustomer(String userName,String password);

}
