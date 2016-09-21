package edu.uco.houselannister.saveasingle;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.uco.houselannister.saveasingle.domain.ZipCode;

public class MainActivity extends Activity {
    private String[] navigationTitle;
    private DrawerLayout navigationDrawer;
    private ListView navigationDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;

//    @BindView(R.id.sample_TextView) TextView mTextView;

    public String something(){
        ZipCode x = new ZipCode("83838",23.34343,-38.333,"Edmond, OK");
        return x.getName();
    }
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        //this is master!

        //mTextView = (TextView) findViewById(R.id.sample_TextView);
//        mTextView.setText(something());

        //navigation drawer
        navigationTitle = getResources().getStringArray(R.array.navigation_titles);
        navigationDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationDrawerListView = (ListView) findViewById(R.id.left_drawer);
        //set the adapter for the list view to use
        navigationDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, navigationTitle));

        navigationDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, navigationDrawer, R.string.open, R.string.closed) {
            public void onDrawerOpened(View drawerView) {

            }

            public void onDrawerClosed(View view) {

            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        navigationDrawer.setDrawerListener(mDrawerToggle);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
//        mDrawerToggle = new ActionBarDrawerToggle(
//                this,                  /* host Activity */
//                navigationDrawer,
//                R.string.open,  /* "open drawer" description */
//                R.string.closed  /* "close drawer" description */
//        ) {
//
//            /** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                getActionBar().setTitle("closed");
//            }
//
//            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                getActionBar().setTitle("open");
//            }
//        };
//
//        // Set the drawer toggle as the DrawerListener
//        navigationDrawer.setDrawerListener(mDrawerToggle);

//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
}
