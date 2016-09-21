package edu.uco.houselannister.saveasingle.domain;

public enum GiftType {
    BOXOFCHOCOLATES(10),
    FLOWER(20),
    STUFFEDANIMAL(30),
    JEWEL(40),
    HUMANORGAN(50);

    private final int cost;
    GiftType(int cost){
        this.cost = cost;
    }
}
