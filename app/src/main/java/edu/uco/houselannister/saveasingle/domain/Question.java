package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.*;

public class Question  implements Serializable {

    private String question;

    private QuestionCategory category;

    private ArrayList<String> responses;

    private Boolean allowMultipleResponses;

    private Boolean enabled;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionCategory category) {
        this.category = category;
    }

    public ArrayList<String> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<String> responses) {
        this.responses = responses;
    }

    public Boolean getAllowMultipleResponses() {
        return allowMultipleResponses;
    }

    public void setAllowMultipleResponses(Boolean allowMultipleResponses) {
        this.allowMultipleResponses = allowMultipleResponses;
    }

    public Boolean getEnabled() {
        if(this.enabled == null){
            this.enabled = false;
        }
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}