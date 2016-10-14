package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum Religion implements Serializable {
    BAHAI("Baha'i", "Baha'ism"),
    BUDDHIST("Buddhist", "Buddhism"),
    CHINESETRAD("Chinese Traditional", "Chinese Traditional"),
    CHRISTIAN("Christian", "Christianity"),
    CONFUCIUS("Confucius", "Confucianism"),
    HINDU("Hindu", "Hinduism"),
    ISLAM("Islam", "Islam"),
    JAINIST("Jain", "Jainism"),
    JUDAISM("Judaism", "Judaism"),
    NONRELIGIOUS("Non-religious", "Non-Religious"),
    PRIMAL("Primal", "Primal"),
    SHINTO("Shinto", "Shintoism"),
    SIKH("Sikh", "Sikhism"),
    SPORTS("Sports", "Sports Fan");

    private final String text;
    private final String ism;

    Religion(String text, String ism) {
        this.text = text;
        this.ism = ism;
    }

    public static String[] GetNames() {
        String[] nameList = new String[values().length];
        for (Religion l : values()) {
            nameList[l.ordinal()] = l.text;
        }
        return nameList;
    }

    public static String[] GetIsm() {
        String[] nameList = new String[values().length];
        for (Religion l : values()) {
            nameList[l.ordinal()] = l.ism;
        }
        return nameList;
    }

    public static Religion getRandom() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<Religion> getRandoms(int number) {
        ArrayList<Religion> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(Religion.getRandom());
        }
        return list;
    }

}