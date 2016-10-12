package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.*;

public class Response implements Serializable {

    private Question question;

    private ArrayList<Integer> responses;

    private String explanation;

    private ArrayList<Integer> acceptableResponses;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Integer> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<Integer> responses) {
        this.responses = responses;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public ArrayList<Integer> getAcceptableResponses() {
        return acceptableResponses;
    }

    public void setAcceptableResponses(ArrayList<Integer> acceptableResponses) {
        this.acceptableResponses = acceptableResponses;
    }
}