package com.examportal.service;

import com.examportal.beans.User;

public interface UserService {
	
	public boolean registerUser(User user);
	public User loginUser(String email,String password);

}
