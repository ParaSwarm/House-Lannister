package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum Relationship  implements Serializable {
    EXCLUSIVE,
    LONGTERM,
    SHORTTERM,
    DATING,
    SERIOUS,
    INTIMATE,
    PASSIONATE,
    PLATONIC;

    public static Relationship getRandomPersonality() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<Relationship> getRandomRelationships(int number) {
        ArrayList<Relationship> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(Relationship.getRandomPersonality());
        }
        return list;
    }
}