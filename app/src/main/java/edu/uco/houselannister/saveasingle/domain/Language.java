package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;

public enum Language implements Serializable {
    SPANISH(R.string.spanish),
    CHINESE(R.string.chinese),
    ENGLISH(R.string.english),
    HINDI(R.string.hindi),
    ARABIC(R.string.arabic);

//    private final String text;
    private int resourceID;

    Language(int id) {
        this.resourceID = id;
    }

    public static String[] GetNames() {
        String[] nameList = new String[values().length];
        nameList[0] = SPANISH.toString();
        nameList[1] = CHINESE.toString();
        nameList[2] = ENGLISH.toString();
        nameList[3] = HINDI.toString();
        nameList[4] = ARABIC.toString();
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

    @Override
    public String toString(){
        return MainActivity.getMainInstance().getApplicationContext().getString(resourceID);
    }
}