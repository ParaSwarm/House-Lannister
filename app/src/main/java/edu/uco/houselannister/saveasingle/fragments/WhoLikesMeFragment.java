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
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.adapters.FavoriteUserItemAdapter;
import edu.uco.houselannister.saveasingle.adapters.MessageItemAdapter;
import edu.uco.houselannister.saveasingle.domain.Message;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class WhoLikesMeFragment extends ListFragment implements OnItemClickListener {

    private Model appModel;

    ActionMode mMode;
    CharSequence[] userMenuOptions = new CharSequence[5];
    private int pos;

    String photoMessage;

    public WhoLikesMeFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_who_likes_me, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FavoriteUserItemAdapter adapter = new FavoriteUserItemAdapter(this.getContext(), R.layout.listview_favorites_item_row, appModel.getUsers().toArray(new User[0]));
        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mMode = null;
                pos = position;
                final boolean isAlreadyFavorited;
                final boolean hasPhotoAccess;
                final boolean isBlocked;
                final User selectedUser = appModel.getUsers().get(pos);

                hasPhotoAccess = appModel.getCurrentUser().getInteractions().getPrivatePhotoAccess().contains(selectedUser);
                isAlreadyFavorited = appModel.getCurrentUser().getInteractions().getFavorites().contains(selectedUser);
                isBlocked = appModel.getCurrentUser().getInteractions().getBlocked().contains(selectedUser);

                if (isAlreadyFavorited) {
                    userMenuOptions[0] = getResources().getString(R.string.remove_from_my_fav);
                } else {
                    userMenuOptions[0] = getResources().getString(R.string.add_to_my_fav);
                }

                if (hasPhotoAccess) {
                    userMenuOptions[1] = "Stop sharing my private album.";
                    photoMessage = getResources().getString(R.string.who_likes_me_stop_sharing);
                } else {
                    userMenuOptions[1] = "Share my private album.";
                    photoMessage = getResources().getString(R.string.started_sharing);
                }

                userMenuOptions[2] = isBlocked ? getResources().getString(R.string.unblock) : getResources().getString(R.string.block);
                userMenuOptions[3] = "View Profile";
                userMenuOptions[4] = getResources().getString(R.string.see_private_album);

                new AlertDialog.Builder(getActivity())
                        .setTitle(selectedUser.getName())
                        .setItems(userMenuOptions, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     // Favorites
                                        if (!isAlreadyFavorited) {
                                            Toast.makeText(getActivity(), selectedUser.getName() + " " + getResources().getString(R.string.is_added_to_my_fav), Toast.LENGTH_SHORT).show();
                                            appModel.getCurrentUser().getInteractions().addToFavorites(selectedUser);
                                        } else {
                                            Toast.makeText(getActivity(), selectedUser.getName() + " " + getResources().getString(R.string.remove_from_my_fav), Toast.LENGTH_SHORT).show();
                                            appModel.getCurrentUser().getInteractions().removeFromFavorites(selectedUser);
                                        }
                                        break;
                                    case 1:     //Photo Album
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle(photoMessage + selectedUser.getName() + "?")
                                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (hasPhotoAccess) {
                                                            Toast.makeText(getActivity(), getResources().getString(R.string.stopped_sharing) + " " +selectedUser.getName(), Toast.LENGTH_SHORT).show();
                                                            appModel.getCurrentUser().getInteractions().revokePrivatePhotoAccess(selectedUser);
                                                        } else {
                                                            Toast.makeText(getActivity(), getResources().getString(R.string.started_sharing) + " " +selectedUser.getName(), Toast.LENGTH_SHORT).show();
                                                            appModel.getCurrentUser().getInteractions().grantPrivatePhotoAccess(selectedUser);
                                                        }
                                                    }
                                                })
                                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                })
                                                .show();
                                        break;
                                    case 2:     // Block User
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle(isBlocked ? getResources().getString(R.string.unblock) + " " + selectedUser.getName() + "?" : getResources().getString(R.string.favorite_block_confirmation) + " " + selectedUser.getName() + "?")
                                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                })
                                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (isBlocked) {
                                                            appModel.getCurrentUser().getInteractions().unblockUser(selectedUser);
                                                            Toast.makeText(getActivity(), String.format("User %s was unblocked.", selectedUser.getName()), Toast.LENGTH_SHORT).show();
                                                            return;
                                                        }

                                                        appModel.getCurrentUser().getInteractions().blockUser(selectedUser);
                                                        Toast.makeText(getActivity(), String.format("User %s blocked.", selectedUser.getName()), Toast.LENGTH_SHORT).show();
                                                    }
                                                }).show();
                                        break;
                                    case 3:     // For viewing user profile
                                        if (selectedUser.getInteractions().getBlocked().contains(appModel.getCurrentUser())) {
                                            Toast.makeText(getActivity(), "This user has blocked you. You cannot view their profile.", Toast.LENGTH_SHORT).show();
                                            return;
                                        }

                                        appModel.getCurrentUser().setUserNameForProfile(selectedUser.getName());

                                        Fragment newFragment = new Display_UserProfile();
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                        transaction.replace(R.id.container, newFragment);
                                        transaction.addToBackStack(null);
                                        transaction.commit();
                                        break;
                                    case 4:
                                        FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
                                        navManager.showFragmentDisplayPrivateAlbum(appModel.getUsers().get(pos));
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}