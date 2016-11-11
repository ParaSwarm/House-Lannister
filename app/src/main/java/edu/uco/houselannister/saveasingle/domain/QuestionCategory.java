package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

public enum QuestionCategory implements Serializable {
    POLITICS("Politics"),
    RELIGION("Religion"),
    SEX("Sex"),
    ETHICS("Ethics"),
    RELATIONSHIP("Relationship"),
    HYGIENE("Hygiene"),
    FOOD("Food");


    private final String text;

    QuestionCategory(String text) {
        this.text = text;
    }

    public static String[] GetNames() {
        String[] nameList = new String[values().length];
        for (QuestionCategory l : values()) {
            nameList[l.ordinal()] = l.text;
        }
        return nameList;
    }
}