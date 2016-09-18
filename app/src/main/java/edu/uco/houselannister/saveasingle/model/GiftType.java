package edu.uco.houselannister.saveasingle.model;

/**
 * 
 */
public enum GiftType {
    BOXOFCHOCOLATES(10),
    FLOWER(20),
    STUFFEDANIMAL(30),
    JEWELL(40),
    HUMANORGAN(50);

    private final int cost;
    GiftType(int cost){
        this.cost = cost;
    }
}