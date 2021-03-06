package edu.uco.houselannister.saveasingle.helpers;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.BuildConfig;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.domain.Message;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.fragments.AdminUsersFragment;
import edu.uco.houselannister.saveasingle.fragments.ComposeMessageFragment;
import edu.uco.houselannister.saveasingle.fragments.DisplayPrivateAlbumFragment;
import edu.uco.houselannister.saveasingle.fragments.Display_UserProfile;
import edu.uco.houselannister.saveasingle.fragments.FavoriteListFragment;
import edu.uco.houselannister.saveasingle.fragments.Fragment_gallery;
import edu.uco.houselannister.saveasingle.fragments.InboxFragment;
import edu.uco.houselannister.saveasingle.fragments.MainFragment;
import edu.uco.houselannister.saveasingle.fragments.MapsActivity;
import edu.uco.houselannister.saveasingle.fragments.PrivateAlbumFragment;
import edu.uco.houselannister.saveasingle.fragments.QuestionFragment;
import edu.uco.houselannister.saveasingle.fragments.SentMessagesFragment;
import edu.uco.houselannister.saveasingle.fragments.MySharingFragment;
import edu.uco.houselannister.saveasingle.fragments.UserQuestionFragment;
import edu.uco.houselannister.saveasingle.fragments.WhoLikesMeFragment;
import edu.uco.houselannister.saveasingle.fragments.SearchCriteriaFragment;
import edu.uco.houselannister.saveasingle.fragments.UserProfile_Fragment;
import edu.uco.houselannister.saveasingle.fragments.SettingsFragment;
import edu.uco.houselannister.saveasingle.fragments.ViewMessageFragment;

public class FragmentNavigationManager implements NavigationManager {



    //region Singleton Constructor
    private static FragmentNavigationManager sInstance;
    private MainActivity mActivity;
    private FragmentManager mFragmentManager;

    public static FragmentNavigationManager obtain(MainActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    public static FragmentNavigationManager getsInstance() {
        return sInstance;
    }

    public FragmentManager getmFragmentManager() {
        return mFragmentManager;
    }

    private void configure(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }
    //endregion

    @Override
    public void showFragmentSettings(String title) {
        showFragment(SettingsFragment.newInstance(title), false);
    }

    @Override
    public void showFragmentMain() {
        showFragment(MainFragment.newInstance(), false);
    }

    @Override
    public void showFragmentList() {
        showFragment(FavoriteListFragment.newInstance(), false);
    }

    @Override
    public void showFragmentUserProfile() {
        showFragment(UserProfile_Fragment.newInstance(), false);
    }

    @Override
    public void showFragmentUserProfile(User user) {
        showFragment(Display_UserProfile.newInstance(user), false);
    }


    public void showFragmentGallery(){
        showFragment(Fragment_gallery.newInstance(), false);
    }

    public void showFragmentPrivateAlbum() {
        showFragment(PrivateAlbumFragment.newInstance(), false);
    }

    public void showFragmentDisplayPrivateAlbum(User user) {
        showFragment(DisplayPrivateAlbumFragment.newInstance(user), false);
    }

    @Override
    public void showFragmentQuestions() {
        showFragment(QuestionFragment.newInstance(1), false);
    }

    @Override
    public void showFragmentUserQuestions() {
        showFragment(UserQuestionFragment.newInstance(1), false);
    }

    @Override
    public void showFragmentAdminUsers() {
        showFragment(AdminUsersFragment.newInstance(1), false);
    }

    @Override
    public void showFragmentWhoLikesMe() {
        showFragment(WhoLikesMeFragment.newInstance(), false);
    }

    @Override
    public void showFragmentMySharing() {
        showFragment(MySharingFragment.newInstance(), false);
    }

    @Override
    public void showFragmentSearchCriteria() {
        showFragment(SearchCriteriaFragment.newInstance(), false);
    }

    @Override
    public void showFragmentInbox() {
        showFragment(InboxFragment.newInstance(), false);
    }

    @Override
    public void showFragmentSentMessages() {
        showFragment(SentMessagesFragment.newInstance(), false);
    }

    @Override
    public void showFragmentMap(Location location, ArrayList<User> matchingUsers) {
        showFragment(MapsActivity.newInstance(location, matchingUsers), false);
    }

    @Override
    public void showFragmentViewMessage(Message message) {
        Bundle data = new Bundle();
        data.putSerializable("Message", message);

        ViewMessageFragment viewMessageFragment = new ViewMessageFragment();
        viewMessageFragment.setArguments(data);
        showFragment(viewMessageFragment, false);
    }

    @Override
    public void showFragmentComposeMessage(Bundle data) {
        ComposeMessageFragment composeMessageFragment = new ComposeMessageFragment();
        composeMessageFragment.setArguments(data);
        showFragment(composeMessageFragment, false);
    }

    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        //actually handles creating the fragment and sending to the caller to change
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction().add(R.id.container, fragment);



        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }
        fm.executePendingTransactions();
    }
}
