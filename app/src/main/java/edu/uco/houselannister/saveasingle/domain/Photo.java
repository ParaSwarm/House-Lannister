package edu.uco.houselannister.saveasingle.domain;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class Photo implements Serializable {

    private Boolean isPrivate;

    private Uri locationUri;

    private Bitmap photo;

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

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}