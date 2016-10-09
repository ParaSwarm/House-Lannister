package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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

    public static Interests getRandomPersonality() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<Interests> getRandomInterests(int numberOfPersonalities) {
        ArrayList<Interests> list = new ArrayList<>();
        for(int i = 0; i <= numberOfPersonalities; i++){
            list.add(Interests.getRandomPersonality());
        }
        return list;
    }

}