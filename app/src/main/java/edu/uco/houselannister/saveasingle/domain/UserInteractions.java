package edu.uco.houselannister.saveasingle.domain;

import android.app.Notification;

import java.io.Serializable;
import java.util.*;

public class UserInteractions implements Serializable {

    private ArrayList<User> friends;

    private ArrayList<User> friendRequests;

    private ArrayList<Match> matches;

    private ArrayList<Recommendation> recommenders;

    private ArrayList<Recommendation> recommendees;

    private ArrayList<String> myPrivatePhotos;

    private ArrayList<User> privatePhotoAccess;

    private ArrayList<Message> inBox;

    private ArrayList<Message> outBox;

    private ArrayList<User> favorites;

    private ArrayList<User> profileUpdated;

    private ArrayList<User> blocked;

    private ArrayList<User> likes;

    private ArrayList<User> viewed;

    private ArrayList<Gift> sentGifts;

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

    public ArrayList<String> getMyPrivatePhotos() {
        return myPrivatePhotos;
    }

    public void setMyPrivatePhotos(ArrayList<String> myPrivatePhotos) {
        this.myPrivatePhotos = myPrivatePhotos;
    }

    public ArrayList<User> getPrivatePhotoAccess() {
        if(privatePhotoAccess == null) {
            privatePhotoAccess = new ArrayList<>();
        }
        return privatePhotoAccess;
    }

    public void setPrivatePhotoAccess(ArrayList<User> privatePhotoAccess) {
        this.privatePhotoAccess = privatePhotoAccess;
    }


    public ArrayList<Message> getInBox() {
        return this.getInBox(true);
    }

    public ArrayList<Message> getInBox(boolean getBlockedMessages) {
        if(inBox == null) {
            inBox = new ArrayList<>();
        }

        if(getBlockedMessages) {
            return inBox;
        }

        ArrayList<Message> unblockedMessages = new ArrayList<>();

        for(Message message : inBox) {
            if(!this.getBlocked().contains(message.getFrom())){
                unblockedMessages.add(message);
            }
        }

        return unblockedMessages;
    }

    public void setInBox(ArrayList<Message> inBox) {
        this.inBox = inBox;
    }

    public ArrayList<Message> getOutBox() {
        if(outBox == null) {
            outBox = new ArrayList<>();
        }
        return outBox;
    }

    public void setOutBox(ArrayList<Message> outBox) {
        this.outBox = outBox;
    }

    public ArrayList<User> getFavorites() {
        if(favorites == null) {
            favorites = new ArrayList<>();
        }
        return favorites;
    }

    public void setFavorites(ArrayList<User> favorites) {
        this.favorites = favorites;
    }

    public ArrayList<User> getBlocked() {
        if(blocked == null) {
            blocked = new ArrayList<>();
        }
        return blocked;
    }

    public void deleteFromFavorites(User userToRemove) {
        if(this.getFavorites().contains(userToRemove)) {
            this.favorites.remove(userToRemove);
        }
    }

    public void setBlocked(ArrayList<User> blocked) {
        this.blocked = blocked;
    }

    public void blockUser(User userToBlock) {
        if(!this.getBlocked().contains(userToBlock)) {
            this.blocked.add(userToBlock);
        }
    }

    public void unblockUser(User userToUnblock) {
        if(this.getBlocked().contains(userToUnblock)) {
            this.blocked.remove(userToUnblock);
        }
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