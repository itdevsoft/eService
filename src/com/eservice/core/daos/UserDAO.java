package com.eservice.core.daos;

import java.util.Map;

import com.eservice.core.beans.User;


public interface UserDAO{

	public Map saveUser(User user);

	public Map updateUser(User user);

	public User selectUser(String userName, String password);
}
