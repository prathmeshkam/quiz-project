package com.quiz.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quiz_service.FeignClient.Feign;
import com.quiz.quiz_service.entity.Question;
import com.quiz.quiz_service.entity.QuestionWrapper;
import com.quiz.quiz_service.entity.Quiz;
import com.quiz.quiz_service.entity.Response;
// import com.quiz.quiz_service.repository.questionRepository;
import com.quiz.quiz_service.repository.quizRepository;


@Service
public class quizService {
@Autowired
quizRepository quizrepository;
@Autowired
Feign feign;


public ResponseEntity<String> createQuiz(String cat, int noQue, String title) {
    // Quiz quiz = new Quiz();

    // List<Question> questions = questionrepository.createByCat(cat , noQue);
    // quiz.setTitle(title);
    // quiz.setQuestions(questions);

    // quizrepository.save(quiz);

    //Microservice is calling here.
    List<Integer> questions = feign.getQuestionForQuiz(cat, noQue).getBody();
    Quiz quiz = new Quiz();
    quiz.setTitle(title);
    quiz.setQuestions(questions);
    
    quizrepository.save(quiz);
  
    return new ResponseEntity<>("Quiz Created" , HttpStatus.CREATED);
}



public ResponseEntity<List<QuestionWrapper>>  getQuizQuestions(int id) {
    Quiz quiz = quizrepository.findById(id).get();
    ResponseEntity<List<QuestionWrapper>> questions = feign.getQuestionsFromId(quiz.getQuestions());

    return questions;
    


  // List<Question> queFromDB = quiz.get().getQuestions();
    // List<QuestionWrapper> queForUser = new ArrayList<>();
    
    // for(Question q:queFromDB)
    // {
    //     QuestionWrapper que = new QuestionWrapper(q.getId() ,q.getQuestiontitle(), q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
    //     queForUser.add(que);
    // }


  // return new ResponseEntity<>(queForUser , HttpStatus.OK);

}



public ResponseEntity<Integer> calResult(int id ,List<Response> responses) {

  // Optional<Quiz> quiz = quizrepository.findById(id);
  // List<Question> questions = quiz.get().getQuestions();

  // int right = 0;
  // int i = 0;
  // for(Response r:responses)
  // {
  //   if(r.getResponse().equals(questions.get(i).getRightanswer()))
  //   right++;

  //   i++;
  // }

  ResponseEntity<Integer>  score = feign.getScore(responses);
  return score;
}


}
