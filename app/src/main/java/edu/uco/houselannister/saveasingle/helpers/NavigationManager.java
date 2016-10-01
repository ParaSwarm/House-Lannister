package edu.uco.houselannister.saveasingle.helpers;

import android.os.Bundle;

/**
 * Created by ryan on 9/21/2016.
 */
public interface NavigationManager {
    //interface for the fragments available
    void showFragmentSettings(String title);
    void showFragmentMain();
    void showFragmentList();
    void showFragmentInbox();
    void showFragmentViewMessage(Bundle data);
    void showFragmentWhoLikesMe();
    void showFragmentSearchCriteria();
    void showFragmentUserProfile();
}
