package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum Gender implements Serializable {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"),
    NONSPECIFIC("Non Specific");

    public static Gender getRandom() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<Gender> getRandoms(int number) {
        ArrayList<Gender> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(Gender.getRandom());
        }
        return list;
    }

    private final String text;

    Gender(String text) {this.text = text;}

    public static String[] GetGenders() {
        String[] genderList = new String[values().length];
        for (Gender l : values()) {
            genderList[l.ordinal()] = l.text;
        }
        return genderList;
    }
}