package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

public enum Interests implements Serializable{
    SPORTS("Sports"),
    CYCLING("Cycling"),
    NATURE("Nature"),
    WRITING("Writing"),
    VOLUNTEERING("Volunteering"),
    COLLECTING("Collecting"),
    MUSIC("Music"),
    PHOTOGRAPHY("Photography"),
    ART("Art"),
    WATERSPORTS("Water Sports"),
    MARTIALARTS("Martial Args"),
    SPELUNKING("Spelunking"),
    MOVIES("Movies"),
    GARDENING("Gardening"),
    COOKING("Cooking"),
    TRAVEL("Travel"),
    SHOPPING("Shopping"),
    SLEEPING("Sleeping");

    private final String text;

    Interests(String text) {
        this.text = text;
    }

    public static String[] GetInterests() {
        String[] interestList = new String[values().length];
        for (Interests l : values()) {
            interestList[l.ordinal()] = l.text;
        }
        return interestList;
    }

}