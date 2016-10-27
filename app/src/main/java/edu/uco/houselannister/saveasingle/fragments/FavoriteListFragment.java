package edu.uco.houselannister.saveasingle.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by ThinkPad on 9/28/2016.
 */
public class FavoriteListFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private Model appModel;


    private static final String KEY_MOVIE_TITLE = "key_title";
    ActionMode mMode;
    ActionMode.Callback mCallback;
    int place;
    String name;
    ActionMode check;
    ArrayList<String> StringFavoritesArrayList = new ArrayList<String>();


    public FavoriteListFragment() {
        // Required empty public constructor
    }

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());

        for(User u: appModel.getCurrentUser().getInteractions().getFavorites()){
            StringFavoritesArrayList.add(u.getName()+ " - " + u.getEmailAddress());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favorite_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, StringFavoritesArrayList);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.user_list, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mMode = null;
                place = position;
                //name = getResources().getStringArray(R.array.user_list)[position];
                name = appModel.getUsers().get(position).getName();
                mMode = getActivity().startActionMode(mCallback);

                return true;
            }
        });
        mCallback = new ActionMode.Callback() {

            /** Invoked whenever the action mode is shown. This is invoked immediately after onCreateActionMode */
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                check = mode;
                mode.setTitle(name);
                return false;
            }

            /** Called when user exits action mode */
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mMode = null;
            }

            /** This is called when the action mode is created. This is called by startActionMode() */
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getActivity().getMenuInflater().inflate(R.menu.menu_fav_list, menu);
                return true;
            }

            /** This is called when an item in the context menu is selected */
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        EditText input = new EditText(getActivity());
                        new AlertDialog.Builder(getActivity())
                                .setTitle(getResources().getString(R.string.favorite_send_message_to) + appModel.getUsers().get(place).getName())
                                .setView(input)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        // continue with delete
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                    }
                                })
                                .show();
                        //   Toast.makeText(getActivity(), getResources().getStringArray(R.array.user_list)[place] + " Message" + mode, Toast.LENGTH_SHORT).show();
                        mode.finish();    // Automatically exists the action mode, when the user selects this action
                        break;
                    case R.id.item2:
                        new AlertDialog.Builder(getActivity())
                                .setTitle(getResources().getString(R.string.favorite_block_entry))
                                .setMessage(getResources().getString(R.string.favorite_block_confirmation) + appModel.getUsers().get(place).getName())
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        //        Toast.makeText(getActivity(), getResources().getStringArray(R.array.user_list)[place] + " BLOCK", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        break;
                }
                return false;
            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), getResources().getString(R.string.favorite_user) + appModel.getUsers().get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}