package edu.uco.houselannister.saveasingle.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ryan on 10/4/2016.
 */
public enum SearchDistances {
    ONEMILE("1 Mile"),
    FIVEMILES("5 Miles"),
    TENMILES("10 Miles"),
    FIFTEENMILES("15 Miles"),
    TWENTYFIVEMILES("25 Miles"),
    FIFTYMILES("50 Miles");

    private final String text;

    SearchDistances(String text) {this.text = text;}

    public static String[] GetDistances() {
        String[] distanceList = new String[values().length];
        for (SearchDistances l : values()) {
            distanceList[l.ordinal()] = l.text;
        }
        return distanceList;
    }

    public static SearchDistances getRandom() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<SearchDistances> getRandoms(int number) {
        ArrayList<SearchDistances> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(SearchDistances.getRandom());
        }
        return list;
    }
}
