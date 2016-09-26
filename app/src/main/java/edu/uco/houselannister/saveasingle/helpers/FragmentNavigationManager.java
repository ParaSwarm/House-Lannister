package edu.uco.houselannister.saveasingle.helpers;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import edu.uco.houselannister.saveasingle.BuildConfig;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.fragments.FavoriteListFragment;
import edu.uco.houselannister.saveasingle.fragments.MainFragment;
import edu.uco.houselannister.saveasingle.fragments.SearchFragment;

/**
 * Created by ryan on 9/21/2016.
 */
public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private MainActivity mActivity;

    public static FragmentNavigationManager obtain(MainActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragmentSettings(String title) {
        //called when wanting to show the settings fragment
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

    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        //actually handles creating the fragment and sending to the caller to change
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container, fragment);

//        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }
        fm.executePendingTransactions();
    }
}
