package edu.uco.houselannister.saveasingle.activities;

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
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.helpers.CustomExpandableListAdapter;
import edu.uco.houselannister.saveasingle.helpers.ExpandableListDataSource;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.helpers.NavigationManager;
import edu.uco.houselannister.saveasingle.model.AppModel;

public class MainActivity extends AppCompatActivity {
    private String[] settingsNavigationTitles;
    private String[] homeNavigationTitles;
    private String[] listNavigationTitles;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView navigationDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationManager mNavigationManager;
    private Map<String, List<String>> mExpandableListData;
    private List<String> mExpandableListTitle;
    private CustomExpandableListAdapter mExpandableListAdapter;
    private String mActivityTitle;
    private Model appModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        appModel = AppModel.getAppModelInstance(new ServiceProxy()) {
//        });


        ButterKnife.bind(this);

        //navigation drawer
        mActivityTitle = getTitle().toString();
        settingsNavigationTitles = getResources().getStringArray(R.array.user_profile_titles);
        homeNavigationTitles = getResources().getStringArray(R.array.home_menu_titles);
        listNavigationTitles = getResources().getStringArray(R.array.friends_list_titles);    ///////////////////// home titles
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationDrawerListView = (ExpandableListView) findViewById(R.id.navList);


        mNavigationManager = FragmentNavigationManager.obtain(this);
        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        navigationDrawerListView.addHeaderView(listHeaderView);
        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());
        addDrawerItems();
        setupDrawer();
        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void selectFirstItemAsDefault() {
        //starts the main fragment first to use as the starting point for the app
        if (mNavigationManager != null) {
//            String firstSettings = getResources().getStringArray(R.array.settings_sub_menus)[0];
            String firstSettings = "Search";
            mNavigationManager.showFragmentMain();
//            getSupportActionBar().setTitle(firstSettings);
        }
    }

    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        navigationDrawerListView.setAdapter(mExpandableListAdapter);
        navigationDrawerListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                getSupportActionBar().setTitle(mExpandableListTitle.get(groupPosition).toString());
            }
        });

        navigationDrawerListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                getSupportActionBar().setTitle(R.string.app_title);
            }
        });

        navigationDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition)))).get(childPosition).toString();
//                getSupportActionBar().setTitle(selectedItem);
                //checks which menu you are clicking on, home navigation is first, settings navigation is the second list
                //probably can be changed to a switch statement later
                if (homeNavigationTitles[0].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentMain();
                } else if (settingsNavigationTitles[1].compareTo(selectedItem) == 0) { //checking that selectedItem == "Settings"
                    mNavigationManager.showFragmentSettings(selectedItem);
                } else if (listNavigationTitles[0].compareTo(selectedItem) == 0) { //checking that selectedItem == Favorite List
                    mNavigationManager.showFragmentList();
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
//                getSupportActionBar().setTitle(R.string.app_title);
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
//                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

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
}