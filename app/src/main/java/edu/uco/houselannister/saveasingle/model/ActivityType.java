package edu.uco.houselannister.saveasingle.model;

/**
 * 
 */
public enum ActivityType {
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