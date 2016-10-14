package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum Ethnicity implements Serializable {
    WHITE,
    HISPANIC,
    BLACK,
    NATIVE,
    ASIAN,
    OTHER,
    NONSPECIFIC;

    public static Ethnicity getRandom() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<Ethnicity> getRandoms(int number) {
        ArrayList<Ethnicity> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(Ethnicity.getRandom());
        }
        return list;
    }
}