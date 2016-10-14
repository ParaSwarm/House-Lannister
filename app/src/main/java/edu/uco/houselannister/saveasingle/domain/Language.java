package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum Language implements Serializable {
    SPANISH("Spanish"),
    CHINESE("Chinese"),
    ENGLISH("English"),
    HINDI("Hindi"),
    ARABIC("Arabic");

    private final String text;

    Language(String text) {
        this.text = text;
    }

    public static String[] GetNames() {
        String[] nameList = new String[values().length];
        for (Language l : values()) {
            nameList[l.ordinal()] = l.text;
        }
        return nameList;
    }

    public static Language getRandom() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<Language> getRandoms(int number) {
        ArrayList<Language> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(Language.getRandom());
        }
        return list;
    }
}