package edu.uco.houselannister.saveasingle.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.fragments.AdminUsersFragment;
import edu.uco.houselannister.saveasingle.helpers.CustomExpandableListAdapter;
import edu.uco.houselannister.saveasingle.helpers.ExpandableListDataSource;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.helpers.NavigationManager;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;


public class MainActivity extends AppCompatActivity implements AdminUsersFragment.OnUserAdminListFragmentInteractionListener {

    @BindArray(R.array.user_profile_titles)
    public String[] settingsNavigationTitles;
    @BindArray(R.array.home_menu_titles)
    public String[] homeNavigationTitles;
    @BindArray(R.array.admin_titles)
    public String[] adminNavigationTitles;
    @BindArray(R.array.people_titles)
    public String[] peopleNavigationTitles;
    @BindView(R.id.navList)
    ExpandableListView navigationDrawerListView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindString(R.string.admin_key)
    String mAdminKey;

    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationManager mNavigationManager;
    private Map<String, List<String>> mExpandableListData;
    private List<String> mExpandableListTitle;
    private CustomExpandableListAdapter mExpandableListAdapter;
    private String mActivityTitle;
    private Model appModel;
    private int year, month, day;
    String radioButton = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());

        //navigation drawer
        mActivityTitle = getTitle().toString();

        mNavigationManager = FragmentNavigationManager.obtain(this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams")
        View listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        navigationDrawerListView.addHeaderView(listHeaderView);
        mExpandableListData = ExpandableListDataSource.getData(this);
        if (!appModel.getAuthenticatedUser().getAdmin()) {
            mExpandableListData.remove(mAdminKey);
        }
        mExpandableListTitle = mExpandableListTitle == null ? new ArrayList(mExpandableListData.keySet()) : mExpandableListTitle;
        addDrawerItems();
        setupDrawer();
        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }
    }

    private void selectFirstItemAsDefault() {
        //starts the main fragment first to use as the starting point for the app
        if (mNavigationManager != null) {
            mNavigationManager.showFragmentMain();
        }
    }

    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        navigationDrawerListView.setAdapter(mExpandableListAdapter);
        navigationDrawerListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        navigationDrawerListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        navigationDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition)))).get(childPosition).toString();
//                getSupportActionBar().setTitle(selectedItem);

                //checks which menu you are clicking on, home navigation is first, settings navigation is the second list
                //probably can be changed to a switch statement later
                if (homeNavigationTitles[0].compareTo(selectedItem) == 0) { // Home
                    mNavigationManager.showFragmentMain();
                } else if (homeNavigationTitles[1].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentInbox();
                } else if (settingsNavigationTitles[0].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentUserProfile();
                } else if (settingsNavigationTitles[1].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentMain();
                } else if (settingsNavigationTitles[2].compareTo(selectedItem) == 0) { // logout
                    appModel = null;
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if (settingsNavigationTitles[3].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentSettings(selectedItem);
                } else if (peopleNavigationTitles[0].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentList();
                } else if (peopleNavigationTitles[1].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentWhoLikesMe();
                } else if (selectedItem.compareTo("Search") == 0) {
                    mNavigationManager.showFragmentSearchCriteria();
                } else if (adminNavigationTitles[0].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentAdminUsers();
                } else if (adminNavigationTitles[1].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentMain();
                } else if (adminNavigationTitles[2].compareTo(selectedItem) == 0) {
                    mNavigationManager.showFragmentMain();
                } else {
                    throw new IllegalArgumentException("Not supported fragment type");
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_closed) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    public void onRadioButtonChecked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.maleRadioButton:
                if (checked)
                    break;

            case R.id.femaleRadiobutton:
                if (checked)
                    break;
        }
    }


    //region For date of birth in User Profile Fragment

    @Override
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {

        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            onDateClick(arg1, arg2 + 1, arg3);
        }
    };

    private void onDateClick(int year, int month, int day) {
        TextView txt = (TextView) findViewById(R.id.DOB_TextView);
        txt.setText("Date of Birth : " + new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    //endregion For date of birth in User Profile Fragment

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUserAdminListFragmentInteraction(final User userItem) {
        appModel.setCurrentUserImpersonation(userItem);
        Toast.makeText(MainActivity.this, "Now Impersonating " + userItem.getName(), Toast.LENGTH_SHORT).show();
    }
}
