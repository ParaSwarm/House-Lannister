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
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class MySharingFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private Model appModel;

    ActionMode mMode;
    private int pos;
    ArrayList<User> AccessPrivatePhotoList;
    ArrayList<String> StringAccessPrivatePhotoList = new ArrayList<String>();
    String photoMessage;

    public MySharingFragment() {
        // Required empty public constructor
    }

    public static MySharingFragment newInstance() {
        return new MySharingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());

        if (appModel.getCurrentUser().getInteractions().getPrivatePhotoAccess() == null) {
            AccessPrivatePhotoList = new ArrayList<User>();
        } else {
            AccessPrivatePhotoList = new ArrayList<User>(appModel.getCurrentUser().getInteractions().getPrivatePhotoAccess());
            for (User u : appModel.getCurrentUser().getInteractions().getPrivatePhotoAccess()) {
                StringAccessPrivatePhotoList.add(u.getName() + " - " + u.getEmailAddress());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_sharing, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, StringAccessPrivatePhotoList);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.user_list, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mMode = null;
                pos = position;

                new AlertDialog.Builder(getActivity())
                        .setTitle(appModel.getCurrentUser().getInteractions().getPrivatePhotoAccess().get(position).getName())
                        .setMessage(getResources().getString(R.string.ask_stop_sharing))
                        .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AccessPrivatePhotoList.remove(pos);
                                StringAccessPrivatePhotoList.remove(pos);
                                appModel.getCurrentUser().getInteractions().setPrivatePhotoAccess(AccessPrivatePhotoList);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {
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
//        Toast.makeText(getActivity(), "User: " + appModel.getUsers().get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
