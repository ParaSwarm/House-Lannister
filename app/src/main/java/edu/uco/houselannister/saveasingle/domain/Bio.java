package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

public class Bio implements Serializable {

    private String aboutMe;

    private String aboutYou;

    private String whyMessageMe;

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAboutYou() {
        return aboutYou;
    }

    public void setAboutYou(String aboutYou) {
        this.aboutYou = aboutYou;
    }

    public String getWhyMessageMe() {
        return whyMessageMe;
    }

    public void setWhyMessageMe(String whyMessageMe) {
        this.whyMessageMe = whyMessageMe;
    }
}