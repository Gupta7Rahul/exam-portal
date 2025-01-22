package com.examportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.beans.User;
import com.examportal.dao.UserRepository;
import com.examportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean registerUser(User user) {
		   try {
			   userRepository.save(user);
			   return true;
		   }catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public User loginUser(String email, String password) {
		User validUser=userRepository.findByEmail(email);
		if(validUser!=null && validUser.getPassword().equals(password))
		{
		return validUser;
		}
		return null;
	}

}
