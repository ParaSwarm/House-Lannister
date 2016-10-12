package edu.uco.houselannister.saveasingle.domain;

/**
 * Created by ryan on 10/3/2016.
 */
public enum Ages {
    EIGHTEEN("18"),
    NINETEEN("19"),
    TWENTY("20"),
    TWENTYONE("21"),
    TWENTYTWO("22"),
    TWENTYTHREE("23"),
    TWENTYFOUR("24"),
    TWENTYFIVE("25"),
    TWENTYSIX("26"),
    TWENTYSEVEN("27"),
    TWENTYEIGHT("28"),
    TWENTYNINE("29"),
    THIRTY("30"),
    THIRTYONE("31"),
    THIRTYTWO("32"),
    THIRTYTHREE("33"),
    THIRTYFOUR("34"),
    THIRTYFIVE("35"),
    THIRTYSIX("36"),
    THIRTYSEVEN("37"),
    THIRTYEIGHT("38"),
    THIRTYNINE("39"),
    FOURTY("40"),
    FOURTYONE("41"),
    FOURTYTWO("42"),
    FOURTYTHREE("43"),
    FOURTYFOUR("44"),
    FOURTYFIVE("45"),
    FOURTYSIX("46"),
    FOURTYSEVEN("47"),
    FOURTYEIGHT("48"),
    FOURTYNINE("49"),
    FIFTY("50"),
    FIFTYONE("51"),
    FIFTYTWO("52"),
    FIFTYTHREE("53"),
    FIFTYFOUR("54"),
    FIFTYFIVE("55"),
    FIFTYSIX("56"),
    FIFTYSEVEN("57"),
    FIFTYEIGHT("58"),
    FIFTYNINE("59"),
    SIXTY("60"),
    SIXYTYONE("61"),
    SIXTYTWO("62"),
    SIXTYTHREE("63"),
    SIXTYFOUR("64"),
    SIXTYFIVE("65"),
    SIXTYSIX("66"),
    SIXTYSEVEN("67"),
    SIXTYEIGHT("68"),
    SIXTYNINE("69"),
    SEVENTY("70"),
    SEVENTYONE("71"),
    SEVENTYTWO("72"),
    SEVENTYTHREE("73"),
    SEVENTYFOUR("74"),
    SEVENTYFIVE("75"),
    SEVENTYSIX("76"),
    SEVENTYSEVEN("77"),
    SEVENTYEIGHT("78"),
    SEVENTYNINE("79"),
    EIGHTY("80");

    private final String text;

    Ages(String text) {this.text = text;}

    public static String[] GetAges() {
        String[] ageList = new String[values().length];
        for (Ages l : values()) {
            ageList[l.ordinal()] = l.text;
        }
        return ageList;
    }
}
