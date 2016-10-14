package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.*;

public class Questionnaire implements Serializable {

    private ArrayList<Question> questions;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}