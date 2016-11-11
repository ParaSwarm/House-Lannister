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
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class ComposeMessageFragment extends Fragment {
    public ComposeMessageFragment() {
    }

    private Model appModel;
    User toUser;
    Message messageBeingRepliedTo;

    @BindView(R.id.to_compose)
    TextView toText;

    @BindView(R.id.subject_compose)
    TextView subjectText;

    @BindView(R.id.message_compose)
    TextView messageText;

    @BindView(R.id.cancel_button_compose)
    Button cancelButton;

    @BindView(R.id.send_button_compose)
    Button sendButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compose_message, container, false);
        ButterKnife.bind(this, view);

        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());

        Bundle data = getArguments();

        toUser = (User) data.getSerializable("User");
        messageBeingRepliedTo = (Message) data.getSerializable("Message");

        toText.setText(toUser.getName());

        if(messageBeingRepliedTo != null) {
            subjectText.setText(messageBeingRepliedTo.getSubject());
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelMessage();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        return view;
    }
    private void cancelMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getResources().getString(R.string.compose_builder_title))
                .setPositiveButton(getResources().getString(R.string.compose_discard), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getFragmentManager().popBackStackImmediate();
                            }
                        }
                )
                .setNegativeButton(
                        getResources().getString(R.string.compose_not_yet),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }
                ).show();
    }

    private void sendMessage() {
        String subject = subjectText.getText().toString();
        String message = messageText.getText().toString();

        if(subject.isEmpty() || message.isEmpty()){
            Toast.makeText(getActivity(), getResources().getString(R.string.compose_must_enter_message), Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getResources().getString(R.string.compose_send_message))
                .setPositiveButton(getResources().getString(R.string.compose_send_button), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Message message = new Message();
                                message.setTo(toUser);
                                message.setFrom(appModel.getCurrentUser());
                                message.setSubject(subjectText.getText().toString());
                                message.setMessage(messageText.getText().toString());

                                if(messageBeingRepliedTo != null) {
                                    message.setReplyToMessage(messageBeingRepliedTo);
                                }

                                message.send(getContext(), ((MainActivity)getActivity()).getNotificationManager());

                                Toast.makeText(getActivity(), String.format("Message sent to %s.", toUser.getName()), Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                                getFragmentManager().popBackStack();
                                FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
                                navManager.showFragmentInbox();
                            }
                        }
                )
                .setNegativeButton(
                        getResources().getString(R.string.compose_not_yet),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }
                ).show();
    }
}