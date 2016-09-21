package edu.uco.houselannister.saveasingle.domain;

import java.util.*;

/**
 * 
 */
public class Question {

    /**
     * Default constructor
     */
    public Question() {
    }

    /**
     * 
     */
    private String question;

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

    /**

     * 
     */
    private QuestionCategory category;

    /**
     * 
     */
    private ArrayList<String> responses;

    /**
     * 
     */
    private Boolean allowMultipleResponses;



}