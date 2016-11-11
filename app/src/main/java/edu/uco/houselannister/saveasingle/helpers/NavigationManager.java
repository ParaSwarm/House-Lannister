package edu.uco.houselannister.saveasingle.helpers;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Message;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 9/21/2016.
 */
public interface NavigationManager {
    //interface for the fragments available
    void showFragmentSettings(String title);
    void showFragmentMain();
    void showFragmentList();
    void showFragmentInbox();
    void showFragmentSentMessages();
    void showFragmentViewMessage(Message message);
    void showFragmentComposeMessage(Bundle data);
    void showFragmentWhoLikesMe();
    void showFragmentMySharing();
    void showFragmentSearchCriteria();
    void showFragmentUserProfile();
    void showFragmentAdminUsers();
    void showFragmentMap(Location location, ArrayList<User> matchingUsers);
    void showFragmentGallery();
   // void showFragmentPrivateAlbum();
    void showFragmentDisplayPrivateAlbum(User user);
    void showFragmentQuestions();
}
