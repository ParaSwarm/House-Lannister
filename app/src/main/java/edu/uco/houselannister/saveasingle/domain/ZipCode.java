package edu.uco.houselannister.saveasingle.domain;

public class ZipCode {

    private final String zip;
    private final Double lat;
    private final Double lon;
    private final String name;

    public ZipCode(String zip, Double latitude, Double longitude, String locationName) {
        this.zip = zip;
        this.lat = latitude;
        this.lon = longitude;
        this.name = locationName;
    }

/*
    Z73013("73013", 35.618788, -97.484375, "Edmond, OK"),
    Z73012("73012", 35.667234, -97.593657, "Edmond, OK"),
    Z73025("73025", 35.733957, -97.577310, "Edmond, OK"),
    Z73034("73034", 35.703414, -97.434052, "Edmond, OK"),
    Z73003("73003", 35.668905, -97.497380, "Edmond, OK");
*/

    public String getZip() {
        return zip;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }
}