package com.examportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.beans.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
