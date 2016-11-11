package edu.uco.houselannister.saveasingle.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by Samuel Song on 9/22/2016.
 */
public class WhoLikesMeFragment extends ListFragment implements OnItemClickListener {
    private Model appModel;

    private static final String KEY_MOVIE_TITLE = "key_title";
    ActionMode mMode;
    CharSequence[] array = {"Add to my favorites", "Share my private album", "Block", "View Profile"};
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
                final User selectedUser = appModel.getUsers().get(pos);

                for (User u : FavoritesArrayList) {
                    if (u.getEmailAddress().contains(selectedUser.getEmailAddress())) {
                        favoriteCheck = true;
                    }
                }
                if (favoriteCheck) {
                    array[0] = "Remove from my favorites.";
                    photoMessage = getResources().getString(R.string.who_likes_me_stop_sharing);
                } else {
                    array[0] = "Add to my favorites.";
                }

                for (User u : AccessPrivatePhotoList) {
                    if (selectedUser.getEmailAddress().equals(u.getEmailAddress())) {
                        photoCheck = true;
                    }
                }
                if (photoCheck) {
                    array[1] = "Stop sharing my private album.";
                    photoMessage = getResources().getString(R.string.who_likes_me_stop_sharing);;
                } else {
                    array[1] = "Share my private album.";
                    photoMessage = getResources().getString(R.string.who_likes_me_stop_sharing);
                }

                new AlertDialog.Builder(getActivity())
                        .setTitle(selectedUser.getName())
                        .setItems(array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     // Favorites
                                        if (array[0].toString().equals("Add to my favorites.")) {
                                            Toast.makeText(getActivity(), selectedUser.getName() + " is added to the favorite list", Toast.LENGTH_SHORT).show();
                                            FavoritesArrayList.add(selectedUser);
                                            appModel.getCurrentUser().getInteractions().setFavorites(FavoritesArrayList);
                                        } else {
                                            Toast.makeText(getActivity(), selectedUser.getName() + " is removed from the favorite list", Toast.LENGTH_SHORT).show();
                                            FavoritesArrayList.remove(FavoritesArrayList.indexOf(selectedUser));
                                            appModel.getCurrentUser().getInteractions().setFavorites(FavoritesArrayList);
                                        }
                                        break;
                                    case 1:     //Photo Album
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle(photoMessage + selectedUser.getName() + "?")
                                                .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (array[1].toString().equalsIgnoreCase("Stop sharing my private album.")) {
                                                            Toast.makeText(getActivity(), "Stopped sharing your private album with " + selectedUser.getName(), Toast.LENGTH_SHORT).show();
                                                            AccessPrivatePhotoList.remove(AccessPrivatePhotoList.indexOf(selectedUser));
                                                            appModel.getCurrentUser().getInteractions().setPrivatePhotoAccess(AccessPrivatePhotoList);
                                                        } else {
                                                            Toast.makeText(getActivity(), "Started sharing your private album with " + selectedUser.getName(), Toast.LENGTH_SHORT).show();
                                                            AccessPrivatePhotoList.add(selectedUser);
                                                            appModel.getCurrentUser().getInteractions().setPrivatePhotoAccess(AccessPrivatePhotoList);
                                                        }
                                                    }
                                                })
                                                .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                })
                                                .show();
                                        break;
                                    case 2:     // Block User
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("Do you want to block " + selectedUser.getName() + "?")
                                                .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
//
                                                    }
                                                })
                                                .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        appModel.getCurrentUser().getInteractions().blockUser(selectedUser);
                                                        Toast.makeText(getActivity(), String.format("User %s blocked.", selectedUser.getName()), Toast.LENGTH_SHORT).show();
                                                    }
                                                }).show();
                                        break;
                                    case 3:     // For viewing user profile
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("Do you want to view " + selectedUser.getName() + "'s Profile ?")
                                                .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        appModel.getCurrentUser().setUserNameForProfile(selectedUser.getName());

                                                        Fragment newFragment = new Display_UserProfile();
                                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                        transaction.replace(R.id.container, newFragment);
                                                        transaction.addToBackStack(null);
                                                        transaction.commit();
                                                    }
                                                })
                                                .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                }).show();
                                        break;

                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
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