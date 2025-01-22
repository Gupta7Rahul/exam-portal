package com.examportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examportal.beans.Question;
import com.examportal.beans.QuestionWrapper;
import com.examportal.beans.Quiz;
import com.examportal.beans.Response;
import com.examportal.dao.QuestionDao;
import com.examportal.dao.QuizDao;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
	
		List<Question> questions=questionDao.findRandomQuestionByCategory(category,numQ);
		
		Quiz quiz= new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz=quizDao.findById(id); 
		List<Question> questionsfromDB=quiz.get().getQuestions();
		List<QuestionWrapper> questionforUser=new ArrayList<>();
		for(Question q:questionsfromDB) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionforUser.add(qw);
		}
		return new ResponseEntity<>(questionforUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
					right++;
			
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
}
