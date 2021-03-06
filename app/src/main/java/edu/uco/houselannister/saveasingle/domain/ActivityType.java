package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

public enum ActivityType implements Serializable {
    MADEFRIEND(true),
    MADEMATCH(true),
    NEWUSER(true),
    ADDEDPHOTO(true),
    UPDATEDPROFILE(true),
    INTERESTING(true),
    SENTMESSAGE(false);

    private final boolean isHidden;

    ActivityType(boolean isHidden){
        this.isHidden = isHidden;
    }

}