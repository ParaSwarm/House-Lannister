package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum Status implements Serializable {
    SINGLE,
    MARRIED,
    COMPLICATED,
    WIDOWED,
    SEPARATED,
    DIVORCED,
    OPENRELATIONSHIP,
    CIVILUNION,
    DOMESTICPARTNERSHIP;

    public static Status getRandom() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<Status> getRandoms(int number) {
        ArrayList<Status> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(Status.getRandom());
        }
        return list;
    }
}