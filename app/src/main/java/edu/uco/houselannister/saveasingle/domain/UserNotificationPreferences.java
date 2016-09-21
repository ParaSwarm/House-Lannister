package edu.uco.houselannister.saveasingle.domain;

import java.util.*;

/**
 * 
 */
public class UserNotificationPreferences {

    /**
     * Default constructor
     */
    public UserNotificationPreferences() {
    }

    /**
     * 
     */
    private Boolean messages;

    /**
     * 
     */
    private Boolean views;

    /**
     * 
     */
    private Boolean friendRequests;

    /**
     * 
     */
    private Boolean friendRequestAccepted;

    /**
     * 
     */
    private Boolean likes;

    /**
     * 
     */
    private Boolean favorite;

    /**
     * 
     */
    private Boolean photoKeyRequest;

    /**
     * 
     */
    private Boolean photoKeyAccept;

    /**
     * 
     */
    private Boolean wasRecommended;

    /**
     * 
     */
    private Boolean recRecommendation;

    public Boolean getMessages() {
        return messages;
    }

    public void setMessages(Boolean messages) {
        this.messages = messages;
    }

    public Boolean getViews() {
        return views;
    }

    public void setViews(Boolean views) {
        this.views = views;
    }

    public Boolean getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Boolean friendRequests) {
        this.friendRequests = friendRequests;
    }

    public Boolean getFriendRequestAccepted() {
        return friendRequestAccepted;
    }

    public void setFriendRequestAccepted(Boolean friendRequestAccepted) {
        this.friendRequestAccepted = friendRequestAccepted;
    }

    public Boolean getLikes() {
        return likes;
    }

    public void setLikes(Boolean likes) {
        this.likes = likes;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getPhotoKeyRequest() {
        return photoKeyRequest;
    }

    public void setPhotoKeyRequest(Boolean photoKeyRequest) {
        this.photoKeyRequest = photoKeyRequest;
    }

    public Boolean getPhotoKeyAccept() {
        return photoKeyAccept;
    }

    public void setPhotoKeyAccept(Boolean photoKeyAccept) {
        this.photoKeyAccept = photoKeyAccept;
    }

    public Boolean getWasRecommended() {
        return wasRecommended;
    }

    public void setWasRecommended(Boolean wasRecommended) {
        this.wasRecommended = wasRecommended;
    }

    public Boolean getRecRecommendation() {
        return recRecommendation;
    }

    public void setRecRecommendation(Boolean recRecommendation) {
        this.recRecommendation = recRecommendation;
    }
}