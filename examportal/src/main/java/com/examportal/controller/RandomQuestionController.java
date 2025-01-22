package com.examportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.beans.Question;
import com.examportal.beans.QuestionWrapper;
import com.examportal.beans.Response;
import com.examportal.service.QuizService;import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("quiz")
public class RandomQuestionController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam("category") String category, @RequestParam("numQ") int numQ, @RequestParam("title") String title){
		return quizService.createQuiz(category,numQ,title);
	}
    
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("id") Integer id){
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Integer id, @RequestBody List<Response> responses){
		return quizService.calculateResult(id,responses);
	}
}
