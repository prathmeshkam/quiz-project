package com.questionservice.question_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.questionservice.question_service.entity.Question;


@Repository
public interface questionRepository extends JpaRepository<Question,Integer> {

public List<Question> findByCategory(String category);

@Query(value = "select q.id from quizquestion q where q.category=:cat order by random() limit :noQue" , nativeQuery = true)   

public List<Integer> createByCat(String cat, int noQue);
}
