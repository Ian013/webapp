package com.epam.training.application.dao;

import java.util.List;

import com.epam.training.application.dao.model.User;

public interface UserDao {

	Integer insertUser(User user);

	User getUser(Integer id);
	
	List<User> getUsers(String firstName);
}
