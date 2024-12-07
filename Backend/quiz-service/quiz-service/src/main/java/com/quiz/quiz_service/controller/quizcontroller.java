package com.quiz.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quiz_service.entity.Question;
import com.quiz.quiz_service.entity.QuestionWrapper;
import com.quiz.quiz_service.entity.QuizDto;
import com.quiz.quiz_service.entity.Response;
import com.quiz.quiz_service.service.quizService;
@RestController
@RequestMapping("quiz")
public class quizcontroller {

    @Autowired
    quizService quizservice;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
    {
        return quizservice.createQuiz(quizDto.getCategory() , quizDto.getNoQue() , quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id)
    {
        return quizservice.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> findScore(@PathVariable int id , @RequestBody List<Response> responses)
    {
        return quizservice.calResult(id , responses);
    }

}
