package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum EducationLevel implements Serializable {
    NONE,
    ELEMENTARY,
    HSDIPLOMA,
    SOMECOLLEGE,
    BACHELORS,
    MASTERS,
    DOCTORAL;

    public static EducationLevel getRandom(){
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<EducationLevel> getRandoms(int number) {
        ArrayList<EducationLevel> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(EducationLevel.getRandom());
        }
        return list;
    }
}