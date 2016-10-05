package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

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

}