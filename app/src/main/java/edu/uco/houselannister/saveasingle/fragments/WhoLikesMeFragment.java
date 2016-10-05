package edu.uco.houselannister.saveasingle.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by Samuel Song on 9/22/2016.
 */
public class WhoLikesMeFragment extends ListFragment implements OnItemClickListener {
    private Model appModel;

    private static final String KEY_MOVIE_TITLE = "key_title";
    ActionMode mMode;
    ActionMode.Callback mCallback;
    CharSequence[] array = {"Add to My Likes", "Share My Private Album", "Block"};
    private int pos;

    public WhoLikesMeFragment() {
        // Required empty public constructor
    }

    public static WhoLikesMeFragment newInstance() {
        return new WhoLikesMeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_who_likes_me, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, appModel.getUsernameArray());
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.user_list, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mMode = null;
                pos = position;
                new AlertDialog.Builder(getActivity())
                        .setTitle(appModel.getUsers().get(position).getName())
                        .setItems(array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Toast.makeText(getActivity(), appModel.getUsers().get(pos).getName() + " is added to My Likes.", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("Do you want to share the private album with " + appModel.getUsers().get(pos).getName() + "?")
                                                .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // do nothing
                                                    }
                                                })
                                                .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // do nothing
                                                    }
                                                })
                                                .show();
                                        break;
                                    case 2:
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("Do you want to block " + appModel.getUsers().get(pos).getName() + "?")
                                                .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // do nothing
                                                    }
                                                })
                                                .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // do nothing
                                                    }
                                                })
                                                .show();
                                        break;
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "User: " + appModel.getUsers().get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}