package edu.uco.houselannister.saveasingle.domain;

public enum Language {
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
}