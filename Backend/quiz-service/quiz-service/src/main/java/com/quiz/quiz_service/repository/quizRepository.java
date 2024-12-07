package com.quiz.quiz_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.quiz_service.entity.Quiz;

public interface quizRepository extends JpaRepository<Quiz , Integer>{

}
