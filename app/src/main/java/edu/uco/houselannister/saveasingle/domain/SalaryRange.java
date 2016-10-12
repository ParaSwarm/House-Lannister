package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public enum SalaryRange implements Serializable {
    POORSLOB,
    STARVINGARTIST,
    GETTINGBY,
    WELLOFF,
    DADDYWARBUCKS;

    public static SalaryRange getRandom(){
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    public static ArrayList<SalaryRange> getRandoms(int number) {
        ArrayList<SalaryRange> list = new ArrayList<>();
        for(int i = 0; i <= number; i++){
            list.add(SalaryRange.getRandom());
        }
        return list;
    }
}