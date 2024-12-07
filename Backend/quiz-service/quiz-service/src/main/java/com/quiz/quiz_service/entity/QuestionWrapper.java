package com.quiz.quiz_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter

public class QuestionWrapper {
  @Id
    private Integer id;
    private String questiontitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    
    public QuestionWrapper() {
    }

    public QuestionWrapper(Integer id, String questiontitle, String option2,
            String option3, String option4,String option1) {
        this.id = id;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.questiontitle = questiontitle;
    }

    

    


}
