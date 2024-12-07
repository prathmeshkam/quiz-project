
package com.questionservice.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionservice.question_service.entity.Question;
import com.questionservice.question_service.entity.QuestionWrapper;
import com.questionservice.question_service.entity.Response;
import com.questionservice.question_service.service.questionService;





@RestController
@RequestMapping("question")

public class questioncontroller {

    @Autowired
    questionService questionservice;
    @Autowired
    Environment environment;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestion()
    {
        System.out.println(environment.getProperty("local.server.port"));
        System.out.println("12345");
        return questionservice.getAllQuestion();
    }

    @GetMapping("{cat}")
    public List<Question> getCategoryJava(@PathVariable String cat)
    {
        return questionservice.getByCategory(cat);
    }

    @PostMapping("addquestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return questionservice.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String cat , @RequestParam Integer noQue)
    {
        return questionservice.getQuestionsForQuiz(cat , noQue);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> id)
    {
        return questionservice.getQuestionsFromId(id);
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionservice.getScore(responses);
    }

}
