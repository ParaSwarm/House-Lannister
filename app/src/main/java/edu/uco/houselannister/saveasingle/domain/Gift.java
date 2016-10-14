package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

public class Gift implements Serializable {

    private GiftType giftType;

    private User to;

    private User from;

    private String caption;

    private Boolean isReceived;

    public GiftType getGiftType() {
        return giftType;
    }

    public void setGiftType(GiftType giftType) {
        this.giftType = giftType;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Boolean getReceived() {
        return isReceived;
    }

    public void setReceived(Boolean received) {
        isReceived = received;
    }
}