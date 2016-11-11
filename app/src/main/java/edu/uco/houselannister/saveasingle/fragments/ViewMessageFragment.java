package edu.uco.houselannister.saveasingle.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.domain.Message;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class ViewMessageFragment extends Fragment {
    public ViewMessageFragment() {
    }

    private Model appModel;
    Message message;

    @BindView(R.id.from)
    TextView fromTextView;

    @BindView(R.id.subject)
    TextView subjectTextView;

    @BindView(R.id.message)
    TextView messageTextView;

    @BindView(R.id.delete_button)
    Button deleteButton;

    @BindView(R.id.block_button)
    Button blockButton;

    @BindView(R.id.reply_button)
    Button replyButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_message, container, false);
        ButterKnife.bind(this, view);

        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());

        Bundle data = getArguments();

        message = (Message) data.getSerializable("Message");

        fromTextView.setText(message.getFrom().getName());
        subjectTextView.setText(message.getSubject());
        messageTextView.setText(message.getMessage());

        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeMessage();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteMessage();
            }
        });

        blockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmBlockUser();
            }
        });

        message.setRead(true);

        return view;
    }

    private void composeMessage() {

        Bundle data = new Bundle();
        data.putSerializable("User", message.getFrom());
        data.putSerializable("Message", message);

        FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
        navManager.showFragmentComposeMessage(data);
    }

    private void confirmDeleteMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getResources().getString(R.string.view_message_delete))
                .setPositiveButton(getResources().getString(R.string.message_delete_button), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                appModel.getCurrentUser().getInteractions().getInBox().remove(message);
                                Toast.makeText(getActivity(), getResources().getString(R.string.view_message_deleted), Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                                getFragmentManager().popBackStack();
                                FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
                                navManager.showFragmentInbox();
                            }
                        }
                )
                .setNegativeButton(
                        getResources().getString(R.string.compose_cancel_button),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }
                ).show();
    }

    private void confirmBlockUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getResources().getString(R.string.view_message_block_user))
                .setPositiveButton(getResources().getString(R.string.block), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                appModel.getCurrentUser().getInteractions().blockUser(message.getFrom());
                                Toast.makeText(getActivity(), String.format("User %s blocked.", message.getFrom().getName()), Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                                getFragmentManager().popBackStack();
                                FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
                                navManager.showFragmentInbox();
                            }
                        }
                )
                .setNegativeButton(
                        getResources().getString(R.string.compose_cancel_button),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }
                ).show();
    }
}