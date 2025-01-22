package com.examportal.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.examportal.beans.Question;
import com.examportal.beans.User;
import com.examportal.service.QuestionService;
import com.examportal.service.UserService;

@Controller
public class JspController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/regPage")
	public String openRegPage(Model model) {
		model.addAttribute("user", new User());
		
		return "register";
	}
	
	@PostMapping("/regForm")
	public String submitRegForm(@ModelAttribute("user") User user, Model model) {
		boolean status= userService.registerUser(user);
		if(status) {
			model.addAttribute("successMsg","User registerd successfully");
		}else {
			model.addAttribute("errorMsg", "user not registerd due to some error");
		}
		return "register";
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/loginForm")
	public String submitLoginForm(@ModelAttribute("user") User user,Model model) {
		User validUser=userService.loginUser(user.getEmail(), user.getPassword());
		
		if(validUser!=null) {
			return "profile";
		}else {
			model.addAttribute("errorMsg", "EmailId and password didn't match");
			return "login";
		}
		
		
	}
	
	@GetMapping("/testPage")
	public String openTestPage(Model model) {
		 List<Question> questions = (List<Question>) questionService.getAllQuestions();
		
	        model.addAttribute("questions", questions);
	        return "test";
	    }
}



