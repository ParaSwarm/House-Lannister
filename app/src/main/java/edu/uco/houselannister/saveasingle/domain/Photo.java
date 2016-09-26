package edu.uco.houselannister.saveasingle.domain;

import android.media.Image;
import android.net.Uri;

public class Photo {

    private Boolean isPrivate;

    private Uri locationUri;

    private Image photo;

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Uri getLocationUri() {
        return locationUri;
    }

    public void setLocationUri(Uri locationUri) {
        this.locationUri = locationUri;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }
}