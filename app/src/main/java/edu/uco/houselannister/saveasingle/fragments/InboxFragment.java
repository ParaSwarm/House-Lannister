package edu.uco.houselannister.saveasingle.fragments;

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
import android.widget.Toast;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.domain.Message;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class InboxFragment extends ListFragment implements OnItemClickListener {

    private Model appModel;
    ActionMode mMode;
    ActionMode.Callback mCallback;
    int place;
    String name;
    ActionMode check;

    ArrayList<Message> messages;

    public InboxFragment() {
    }

    public static InboxFragment newInstance() {
        return new InboxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        messages =  appModel.getCurrentUser().getInteractions().getInBox(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, messages);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mMode = null;
                place = position;
                name = appModel.getUsers().get(position).getName();
                mMode = getActivity().startActionMode(mCallback);

                return true;
            }
        });

        mCallback = new ActionMode.Callback() {
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                check = mode;
                mode.setTitle(name);
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mMode = null;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getActivity().getMenuInflater().inflate(R.menu.menu_inbox, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.inboxLink:
                        Toast.makeText(getActivity(), "Navigating to Sent Items.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.sentLink:
                        Toast.makeText(getActivity(), "Navigating to Sent Items.", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Message selectedMessage = this.messages.get(position);

        Bundle data = new Bundle();
        data.putSerializable("Message", selectedMessage);

        FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
        navManager.showFragmentViewMessage(data);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}