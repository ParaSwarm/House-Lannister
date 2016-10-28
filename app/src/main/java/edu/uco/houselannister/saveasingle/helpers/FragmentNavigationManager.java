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
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.fragments.AdminUsersFragment;
import edu.uco.houselannister.saveasingle.fragments.ComposeMessageFragment;
import edu.uco.houselannister.saveasingle.fragments.FavoriteListFragment;
import edu.uco.houselannister.saveasingle.fragments.Fragment_gallery;
import edu.uco.houselannister.saveasingle.fragments.InboxFragment;
import edu.uco.houselannister.saveasingle.fragments.MainFragment;
import edu.uco.houselannister.saveasingle.fragments.MapsActivity;
import edu.uco.houselannister.saveasingle.fragments.SearchCriteriaFragment;
import edu.uco.houselannister.saveasingle.fragments.SearchFragment;
import edu.uco.houselannister.saveasingle.fragments.UserProfile_Fragment;
import edu.uco.houselannister.saveasingle.fragments.ViewMessageFragment;
import edu.uco.houselannister.saveasingle.fragments.WhoLikesMeFragment;

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
        showFragment(SearchFragment.newInstance(title), false);
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


    public void showFragmentGallery(){
        showFragment(Fragment_gallery.newInstance(), false);
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
    public void showFragmentSearchCriteria() {
        showFragment(SearchCriteriaFragment.newInstance(), false);
    }

    @Override
    public void showFragmentInbox() {
        showFragment(InboxFragment.newInstance(), false);
    }

    @Override
    public void showFragmentMap(Location location, ArrayList<User> matchingUsers) {showFragment(MapsActivity.newInstance(location, matchingUsers), false);}

    @Override
    public void showFragmentViewMessage(Bundle data) {
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
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container, fragment);



        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }
        fm.executePendingTransactions();
    }
}
