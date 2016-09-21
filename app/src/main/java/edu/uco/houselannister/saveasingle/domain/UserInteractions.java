package edu.uco.houselannister.saveasingle.domain;

import java.util.*;

/**
 * 
 */
public class UserInteractions {

    /**
     * Default constructor
     */
    public UserInteractions() {
    }

    /**
     * 
     */
    private ArrayList<User> friends;

    /**
     * 
     */
    private ArrayList<User> friendRequests;

    /**
     * 
     */
    private ArrayList<Match> matches;

    /**
     * 
     */
    private ArrayList<Recommendation> recommenders;

    /**
     * 
     */
    private ArrayList<Recommendation> recommendees;

    /**
     * 
     */
    private ArrayList<User> myPrivatePhotos;

    /**
     * 
     */
    private ArrayList<User> privatePhotoAccess;

    /**
     * 
     */
    private ArrayList<Message> inBox;

    /**
     * 
     */
    private ArrayList<Message> outBox;

    /**
     * 
     */
    private ArrayList<User> favorites;

    /**
     * 
     */
    private ArrayList<User> blocked;

    /**
     * 
     */
    private ArrayList<User> likes;

    /**
     * 
     */
    private ArrayList<User> viewed;

    /**
     * 
     */
    private ArrayList<Gift> sentGifts;

    /**
     * 
     */
    private ArrayList<Gift> receivedGifts;


    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<User> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(ArrayList<User> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public ArrayList<Recommendation> getRecommenders() {
        return recommenders;
    }

    public void setRecommenders(ArrayList<Recommendation> recommenders) {
        this.recommenders = recommenders;
    }

    public ArrayList<Recommendation> getRecommendees() {
        return recommendees;
    }

    public void setRecommendees(ArrayList<Recommendation> recommendees) {
        this.recommendees = recommendees;
    }

    public ArrayList<User> getMyPrivatePhotos() {
        return myPrivatePhotos;
    }

    public void setMyPrivatePhotos(ArrayList<User> myPrivatePhotos) {
        this.myPrivatePhotos = myPrivatePhotos;
    }

    public ArrayList<User> getPrivatePhotoAccess() {
        return privatePhotoAccess;
    }

    public void setPrivatePhotoAccess(ArrayList<User> privatePhotoAccess) {
        this.privatePhotoAccess = privatePhotoAccess;
    }

    public ArrayList<Message> getInBox() {
        return inBox;
    }

    public void setInBox(ArrayList<Message> inBox) {
        this.inBox = inBox;
    }

    public ArrayList<Message> getOutBox() {
        return outBox;
    }

    public void setOutBox(ArrayList<Message> outBox) {
        this.outBox = outBox;
    }

    public ArrayList<User> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<User> favorites) {
        this.favorites = favorites;
    }

    public ArrayList<User> getBlocked() {
        return blocked;
    }

    public void setBlocked(ArrayList<User> blocked) {
        this.blocked = blocked;
    }

    public ArrayList<User> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<User> likes) {
        this.likes = likes;
    }

    public ArrayList<User> getViewed() {
        return viewed;
    }

    public void setViewed(ArrayList<User> viewed) {
        this.viewed = viewed;
    }

    public ArrayList<Gift> getSentGifts() {
        return sentGifts;
    }

    public void setSentGifts(ArrayList<Gift> sentGifts) {
        this.sentGifts = sentGifts;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}