package edu.uco.houselannister.saveasingle.fragments;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class Display_UserProfile extends Fragment {

    private Model appModel;

    private User profileUser;
    private View view;

    @BindView(R.id.block_profile_button)
    Button blockButton;

    @BindView(R.id.compose_button)
    Button composeButton;

    public Display_UserProfile() {

    }

    public static Display_UserProfile newInstance() {
        return new Display_UserProfile();
    }
    public static Display_UserProfile newInstance(User user) {
        Display_UserProfile fragment = new Display_UserProfile();
        Bundle args = new Bundle();

        // Craptastic hack because parcelable overrides for User isn't fully implemented
        if(user.getAge() == 0) {
            Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
            user = appModel.getUser(user.getName());
        }

        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_display_user_profile, container, false);
        ButterKnife.bind(this, view);

        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());

        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeMessage();
            }
        });

        blockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockOrUnblockUser();
            }
        });

        try {
            profileUser = getArguments().getParcelable("user");
        }
        catch (Exception e) {
            e.toString();
        }
        if(profileUser == null) {
            profileUser = appModel.getCurrentUser();
        }

        setBlockText();

        //using a get user that is just getting the current user
        displayUser(profileUser);

        return view;
    }

    private void setBlockText() {
        blockButton.setText(userIsBlocked()
                ? getResources().getString(R.string.unblock)
                : getResources().getString(R.string.block));
    }

    private boolean userIsBlocked() {
        return appModel.getCurrentUser().getInteractions().isBlocked(profileUser);
    }

    private void composeMessage() {

        Bundle data = new Bundle();
        data.putSerializable("User", profileUser);

        FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
        navManager.showFragmentComposeMessage(data);
    }

    private void blockOrUnblockUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(userIsBlocked() ? getResources().getString(R.string.unblock) : getResources().getString(R.string.view_message_block_user))
                .setPositiveButton(userIsBlocked() ?  getResources().getString(R.string.unblock) : getResources().getString(R.string.block), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(userIsBlocked()) {
                                    appModel.getCurrentUser().getInteractions().unblockUser(profileUser);
                                    Toast.makeText(getActivity(), String.format("User %s unblocked.", profileUser.getName()), Toast.LENGTH_SHORT).show();
                                    setBlockText();
                                } else {
                                    appModel.getCurrentUser().getInteractions().blockUser(profileUser);
                                    Toast.makeText(getActivity(), String.format("User %s blocked.", profileUser.getName()), Toast.LENGTH_SHORT).show();
                                    getFragmentManager().popBackStack();
                                    getFragmentManager().popBackStack();
                                    FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
                                    navManager.showFragmentWhoLikesMe();
                                }
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

    private void displayUser(User user) {
        profileUser = user;

        TextView fullName = (TextView)view.findViewById(R.id.FullName_UserProfile_TextView);
        fullName.setText(profileUser.getFullName());
        TextView displayName = (TextView)view.findViewById(R.id.DisplayName_UserProfile);
        displayName.setText(String.valueOf(profileUser.getName()));
        TextView age = (TextView)view.findViewById(R.id.Age_UserProfile);
        age.setText(String.valueOf(profileUser.getAge()));
        TextView gender = (TextView)view.findViewById(R.id.Gender_UserProfile_TextView);
        gender.setText(String.valueOf(User.GenderValues[profileUser.getGender()]));
        TextView height = (TextView)view.findViewById(R.id.height_userprofile);
        height.setText(String.valueOf(profileUser.getHeight()));
        TextView location = (TextView)view.findViewById(R.id.location_userprofile);
        location.setText(String.valueOf(profileUser.getPosition()));
        TextView education = (TextView)view.findViewById(R.id.education_userprofile);
        education.setText(String.valueOf(User.EducationValues[profileUser.getEducation()]));
        TextView religion = (TextView)view.findViewById(R.id.Religion_userprofile);
        religion.setText(String.valueOf(profileUser.getReligion()));
        TextView ethnicity = (TextView)view.findViewById(R.id.Ethnicity_userprofile);
        ethnicity.setText(String.valueOf(profileUser.getEthnicity()));
        TextView smoking = (TextView)view.findViewById(R.id.Smoking_userprofile);
        smoking.setText(String.valueOf(User.SmokingValues[profileUser.getSmoking()]));
        TextView bodyType = (TextView)view.findViewById(R.id.bodyType_userprofile);
        bodyType.setText(String.valueOf(User.BodyTypeValues[profileUser.getBodyType()]));
        TextView work = (TextView)view.findViewById(R.id.work_userprofile);
        work.setText(String.valueOf(profileUser.getWork()));
        TextView income = (TextView)view.findViewById(R.id.income_userprofile);
        income.setText(String.valueOf(User.IncomeValues[profileUser.getIncome()]));
        TextView marriedStatus = (TextView)view.findViewById(R.id.marriedStatus_userprofile);
        marriedStatus.setText(String.valueOf(User.MarriedStatusValues[profileUser.getMarriedStatus()]));
        TextView children = (TextView)view.findViewById(R.id.numChildren_userprofile);
        children.setText(String.valueOf(profileUser.getChildren()));
        TextView story = (TextView)view.findViewById(R.id.story_user);
        story.setText(String.valueOf(profileUser.getStory()));
        TextView perfectMatch = (TextView)view.findViewById(R.id.perfectmatch_user);
        perfectMatch.setText(String.valueOf(profileUser.getPerfectMatch()));
        ImageView imgPhoto = (ImageView)view.findViewById(R.id.imgPhoto);

        if (profileUser.getProfilePhoto().getPhoto() != null) {
            imgPhoto.setImageBitmap(profileUser.getProfilePhoto().getPhoto());
        }

        sendNotification(getContext(), ((MainActivity)getActivity()).getNotificationManager());
    }

    public void sendNotification(Context context, NotificationManager notificationManager) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setContentTitle(profileUser.getName() + " has viewed your profile!")
                        .setSmallIcon(R.drawable.stylebutton);

        Intent resultIntent = new Intent(context, MainActivity.class);

        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.putExtra("VisitingUser", (Parcelable) profileUser);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(1, mBuilder.build());
    }
}
