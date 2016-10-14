package edu.uco.houselannister.saveasingle.domain;

import com.google.android.gms.plus.model.people.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum Personality implements Serializable {
    INTROVERT,
    EXTROVERT,
    SENSUAL,
    INTUITIVE,
    THOUGHTFUL,
    INSIGHTFUL,
    JUDGMENTAL,
    PERCEPTIVE;;

    public static Personality getRandomPersonality() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<Personality> getRandomPersonalities(int numberOfPersonalities) {
        ArrayList<Personality> list = new ArrayList<>();
        for(int i = 0; i <= numberOfPersonalities; i++){
            list.add(Personality.getRandomPersonality());
        }
        return list;
    }
}
