package com.questionservice.question_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "quizquestion")
public class Question {
    //All this value names are should be same as in the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String difficultylevel;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questiontitle;
    private String rightanswer;


    public Question() {
    }


    public Question(int id, String category, String difficultylevel, String option1, String option2, String option3,
            String option4, String questiontitle, String rightanswer) {
        this.id = id;
        this.category = category;
        this.difficultylevel = difficultylevel;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.questiontitle = questiontitle;
        this.rightanswer = rightanswer;
    }

}
