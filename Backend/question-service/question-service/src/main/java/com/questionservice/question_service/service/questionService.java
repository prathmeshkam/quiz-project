
package com.questionservice.question_service.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionservice.question_service.entity.Question;
import com.questionservice.question_service.entity.QuestionWrapper;
import com.questionservice.question_service.entity.Response;
import com.questionservice.question_service.repository.questionRepository;





@Service
public class questionService {

    @Autowired
    private questionRepository questionrepository;
    public ResponseEntity<List<Question>> getAllQuestion()
    {
        try{
        return new ResponseEntity<>(questionrepository.findAll(),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList(),HttpStatus.BAD_REQUEST);

    }
    public List<Question> getByCategory(String cat)
    {
      
        List<Question> a = questionrepository.findByCategory(cat);
        return a;
    }
    public ResponseEntity<String> addQuestion(Question question) {
        try{
        questionrepository.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);

     
    }
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String cat, Integer noQue) {
      List<Integer> queQuiz = questionrepository.createByCat(cat, noQue);
      return new ResponseEntity<>(queQuiz , HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> id) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questionsFromDB = new ArrayList<>();

        for(Integer i:id)
        {
            questionsFromDB.add(questionrepository.findById(i).get());
        }
        for(Question que:questionsFromDB)
        {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(que.getId());
            wrapper.setQuestiontitle(que.getQuestiontitle());
            wrapper.setOption1(que.getOption1());
            wrapper.setOption2(que.getOption2());
            wrapper.setOption3(que.getOption3());
            wrapper.setOption4(que.getOption4());

            wrappers.add(wrapper);

        }


        return new ResponseEntity<>(wrappers , HttpStatus.OK);



    }
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;

        for(Response r:responses)
        {
            Question question = questionrepository.findById(r.getId()).get();

            if(r.getResponse().equals(question.getRightanswer()))
            {
                right++;
            }

        }

        return new ResponseEntity<>(right , HttpStatus.OK);
    }
}
