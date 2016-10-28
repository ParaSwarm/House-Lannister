package edu.uco.houselannister.saveasingle.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

public class FavoriteListFragment extends ListFragment implements OnItemClickListener {

    private Model appModel;

    ArrayList<User> favorites;

    public FavoriteListFragment() {
    }

    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        favorites =  appModel.getCurrentUser().getInteractions().getFavorites();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FavoriteUserItemAdapter adapter = new FavoriteUserItemAdapter(this.getContext(), R.layout.listview_favorites_item_row, favorites.toArray(new User[0]));
        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final User selectedFavorite = this.favorites.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(String.format("Delete %s from Favorites?", selectedFavorite.getName()))
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                appModel.getCurrentUser().getInteractions().deleteFromFavorites(selectedFavorite);
                                favorites = appModel.getCurrentUser().getInteractions().getFavorites();

                                FavoriteUserItemAdapter adapter = new FavoriteUserItemAdapter(((FavoriteUserItemAdapter)getListAdapter()).getContext(), R.layout.listview_favorites_item_row, favorites.toArray(new User[0]));
                                setListAdapter(adapter);

                                Toast.makeText(getActivity(), String.format("User %s removed.", selectedFavorite.getName()), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }
                ).show();
    }
}