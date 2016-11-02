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

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.domain.UserInteractions;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by Samuel Song on 9/22/2016.
 */
public class WhoLikesMeFragment extends ListFragment implements OnItemClickListener {
    private Model appModel;

    ActionMode mMode;
    CharSequence[] array = new CharSequence[3];
    private int pos;
    ArrayList<User> FavoritesArrayList;
    ArrayList<User> BlockArrayList;
    ArrayList<User> AccessPrivatePhotoList;
    String photoMessage;

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

        if (appModel.getCurrentUser().getInteractions().getFavorites() == null)
            FavoritesArrayList = new ArrayList<User>();
        else
            FavoritesArrayList = new ArrayList<User>(appModel.getCurrentUser().getInteractions().getFavorites());

        if (appModel.getCurrentUser().getInteractions().getPrivatePhotoAccess() == null)
            AccessPrivatePhotoList = new ArrayList<User>();
        else
            AccessPrivatePhotoList = new ArrayList<User>(appModel.getCurrentUser().getInteractions().getPrivatePhotoAccess());
        System.out.println("I AM WORKING4 " + AccessPrivatePhotoList.size());
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
                boolean favoriteCheck = false;
                boolean photoCheck = false;
                for (User u : FavoritesArrayList)
                    if (u.getEmailAddress().contains(appModel.getUsers().get(pos).getEmailAddress())) {
                        favoriteCheck = true;
                    }
                if (favoriteCheck) {
                    array[0] = getResources().getString(R.string.remove_from_my_fav);
                } else {
                    array[0] = getResources().getString(R.string.add_to_my_fav);
                }
                for (User u : AccessPrivatePhotoList)
                    if (appModel.getUsers().get(pos).getEmailAddress().equals(u.getEmailAddress()))
                        photoCheck = true;
                if (photoCheck) {
                    array[1] = getResources().getString(R.string.stop_sharing_my_fav);
                    photoMessage = getResources().getString(R.string.ask_stop_sharing);
                } else {
                    array[1] = getResources().getString(R.string.share_my_fav);
                    photoMessage = getResources().getString(R.string.ask_start_sharing);
                }
                array[2] = getResources().getString(R.string.block);

                new AlertDialog.Builder(getActivity())
                        .setTitle(appModel.getUsers().get(position).getName())
                        .setItems(array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        if (array[0].toString().equals(getResources().getString(R.string.add_to_my_fav))) {
                                            Toast.makeText(getActivity(), appModel.getUsers().get(pos).getName() + getResources().getString(R.string.is_added_to_my_fav), Toast.LENGTH_SHORT).show();
                                            FavoritesArrayList.add(appModel.getUsers().get(pos));
                                            appModel.getCurrentUser().getInteractions().setFavorites(FavoritesArrayList);
                                        } else {
                                            Toast.makeText(getActivity(), appModel.getUsers().get(pos).getName() + getResources().getString(R.string.remove_from_my_fav), Toast.LENGTH_SHORT).show();
                                            FavoritesArrayList.remove(FavoritesArrayList.indexOf(appModel.getUsers().get(pos)));
                                            appModel.getCurrentUser().getInteractions().setFavorites(FavoritesArrayList);
                                        }
                                        break;
                                    case 1:
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle(photoMessage + appModel.getUsers().get(pos).getName() + "?")
                                                .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (array[1].toString().equalsIgnoreCase(getResources().getString(R.string.stop_sharing_my_fav))) {
                                                            Toast.makeText(getActivity(), getResources().getString(R.string.stopped_sharing) + appModel.getUsers().get(pos).getName(), Toast.LENGTH_SHORT).show();
                                                            AccessPrivatePhotoList.remove(AccessPrivatePhotoList.indexOf(appModel.getUsers().get(pos)));
                                                            appModel.getCurrentUser().getInteractions().setPrivatePhotoAccess(AccessPrivatePhotoList);
                                                        } else {
                                                            Toast.makeText(getActivity(), getResources().getString(R.string.started_sharing) + appModel.getUsers().get(pos).getName(), Toast.LENGTH_SHORT).show();
                                                            AccessPrivatePhotoList.add(appModel.getUsers().get(pos));
                                                            appModel.getCurrentUser().getInteractions().setPrivatePhotoAccess(AccessPrivatePhotoList);
                                                        }
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
                                                    }
                                                })
                                                .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // do nothing
                                                    }
                                                }).show();
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
//        Toast.makeText(getActivity(), "User: " + appModel.getUsers().get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}