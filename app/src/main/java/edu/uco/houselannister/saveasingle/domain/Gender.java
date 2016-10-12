package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum Gender implements Serializable {
    MALE,
    FEMALE,
    OTHER,
    NONSPECIFIC;

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
}