package com.eservice.hibernate.service.impls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eservice.core.beans.User;
import com.eservice.core.daos.UserDAO;
import com.eservice.core.services.AccountService;

//This will make easier to autowired
@Repository("accountService")
public class AccountServiceImpl implements AccountService {

	private UserDAO userDAO;

	@Autowired
	public void setUserDAO(UserDAO dao) {
		this.userDAO = dao;
	}

	@Override
	public Map addCustomer(User user){
		return userDAO.saveUser(user);
	}

	@Override
	public Map updateCustomer(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public User getCustomer(String userName, String password) {
		return userDAO.selectUser(userName,password);
	}

}
