package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class Display_UserProfile extends Fragment {

    View v;

    public Display_UserProfile() {
        // Required empty public constructor
    }

    public static Display_UserProfile newInstance() {
        return new Display_UserProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_display__user_profile, container, false);
        Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());


        String username = appModel.getCurrentUser().getUsernameForProfile();


        TextView fullname = (TextView)v.findViewById(R.id.FullName_UserProfile_TextView);
        fullname.setText(appModel.getUser(username).getFullName());
        TextView displayName = (TextView)v.findViewById(R.id.DisplayName_UserProfile);
        displayName.setText(String.valueOf(appModel.getUser(username).getName()));
        TextView age = (TextView)v.findViewById(R.id.Age_UserProfile);
        age.setText(String.valueOf(appModel.getUser(username).getAge()));
        TextView gender = (TextView)v.findViewById(R.id.Gender_UserProfile_TextView);
        gender.setText(String.valueOf(User.GenderValues[appModel.getUser(username).getGender()]));
        TextView height = (TextView)v.findViewById(R.id.height_userprofile);
        height.setText(String.valueOf(appModel.getUser(username).getHeight()));
        TextView location = (TextView)v.findViewById(R.id.location_userprofile);
        location.setText(String.valueOf(appModel.getUser(username).getPosition()));
        TextView education = (TextView)v.findViewById(R.id.education_userprofile);
        education.setText(String.valueOf(User.EducationValues[appModel.getUser(username).getEducation()]));
        TextView religion = (TextView)v.findViewById(R.id.Religion_userprofile);
        religion.setText(String.valueOf(appModel.getUser(username).getReligion()));
        TextView ethnicity = (TextView)v.findViewById(R.id.Ethnicity_userprofile);
        ethnicity.setText(String.valueOf(appModel.getUser(username).getEthnicity()));
        TextView smoking = (TextView)v.findViewById(R.id.Smoking_userprofile);
        smoking.setText(String.valueOf(User.SmokingValues[appModel.getUser(username).getSmoking()]));
        TextView bodyType = (TextView)v.findViewById(R.id.bodyType_userprofile);
        bodyType.setText(String.valueOf(User.BodyTypeValues[appModel.getUser(username).getBodyType()]));
        TextView work = (TextView)v.findViewById(R.id.work_userprofile);
        work.setText(String.valueOf(appModel.getUser(username).getWork()));
        TextView income = (TextView)v.findViewById(R.id.income_userprofile);
        income.setText(String.valueOf(User.IncomeValues[appModel.getUser(username).getIncome()]));
        TextView marriedStatus = (TextView)v.findViewById(R.id.marriedStatus_userprofile);
        marriedStatus.setText(String.valueOf(User.MarriedStatusValues[appModel.getUser(username).getMarriedStatus()]));
        TextView children = (TextView)v.findViewById(R.id.numChildren_userprofile);
        children.setText(String.valueOf(appModel.getUser(username).getChildren()));
        TextView story = (TextView)v.findViewById(R.id.story_user);
        story.setText(String.valueOf(appModel.getUser(username).getStory()));
        TextView perfectMatch = (TextView)v.findViewById(R.id.perfectmatch_user);
        perfectMatch.setText(String.valueOf(appModel.getUser(username).getPerfectMatch()));
        ImageView imgPhoto = (ImageView)v.findViewById(R.id.imgPhoto);
        if (appModel.getUser(username).getProfilePhoto().getPhoto() != null) {
            imgPhoto.setImageBitmap(appModel.getUser(username).getProfilePhoto().getPhoto());
        }
        return v;
    }


}
