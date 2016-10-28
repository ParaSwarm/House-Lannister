package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.adapters.MessageItemAdapter;
import edu.uco.houselannister.saveasingle.domain.Message;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class SentMessagesFragment extends ListFragment implements OnItemClickListener {

    private Model appModel;

    ArrayList<Message> messages;

    @BindView(R.id.goto_inbox)
    Button sentMessagesButton;

    public SentMessagesFragment() {
    }

    public static SentMessagesFragment newInstance() {
        return new SentMessagesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        messages =  appModel.getCurrentUser().getInteractions().getOutBox();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sent_messages, container, false);
        ButterKnife.bind(this, view);

        sentMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInbox();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MessageItemAdapter adapter = new MessageItemAdapter(this.getContext(), R.layout.listview_inbox_item_row, messages.toArray(new Message[0]));
        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Message selectedMessage = this.messages.get(position);

        Bundle data = new Bundle();
        data.putSerializable("Message", selectedMessage);

        FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
        navManager.showFragmentViewMessage(data);
    }

    public void goToInbox(){
        FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
        navManager.showFragmentInbox();
    }
}