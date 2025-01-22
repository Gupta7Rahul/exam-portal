package com.examportal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examportal.beans.Question;
import com.examportal.dao.QuestionDao;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public List<Question> getAllQuestions(){
		
		return questionDao.findAll();
		
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		 try {
	            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		 try {
		 questionDao.save(question);
		 }catch (Exception e){
	            e.printStackTrace();
	        }
		 return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

}
