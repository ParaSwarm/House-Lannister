package edu.uco.houselannister.saveasingle.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @BindView(R.id.block_profile_button)
    Button blockButton;

    @BindView(R.id.compose_button)
    Button composeButton;

    public Display_UserProfile() {
    }

    public static Display_UserProfile newInstance() {
        return new Display_UserProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_display__user_profile, container, false);
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
                confirmBlockUser();
            }
        });

        profileUser = appModel.getUser(appModel.getCurrentUser().getUsernameForProfile());

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

        return view;
    }

    private void composeMessage() {

        Bundle data = new Bundle();
        data.putSerializable("User", profileUser);

        FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
        navManager.showFragmentComposeMessage(data);
    }

    private void confirmBlockUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getResources().getString(R.string.view_message_block_user))
                .setPositiveButton(getResources().getString(R.string.block), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                appModel.getCurrentUser().getInteractions().blockUser(profileUser);
                                Toast.makeText(getActivity(), String.format("User %s blocked.", profileUser.getName()), Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                                getFragmentManager().popBackStack();
                                FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
                                navManager.showFragmentWhoLikesMe();
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
