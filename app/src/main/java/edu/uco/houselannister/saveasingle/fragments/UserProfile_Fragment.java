package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Bibash Lama
 */
public class UserProfile_Fragment extends Fragment{

    View v;
    public UserProfile_Fragment() {
        // Required empty public constructor
    }

    public static UserProfile_Fragment newInstance(){
        return new UserProfile_Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        TextView fullname = (TextView)v.findViewById(R.id.FullName_UserProfile_TextView);
        fullname.setText(appModel.getCurrentUser().getFullName());
        TextView displayName = (TextView)v.findViewById(R.id.DisplayName_UserProfile);
        displayName.setText(String.valueOf(appModel.getCurrentUser().getName()));
        TextView age = (TextView)v.findViewById(R.id.Age_UserProfile);
        age.setText(String.valueOf(appModel.getCurrentUser().getAge()));
        TextView gender = (TextView)v.findViewById(R.id.Gender_UserProfile_TextView);
        gender.setText(String.valueOf(User.GenderValues[appModel.getCurrentUser().getGender()]));
        TextView height = (TextView)v.findViewById(R.id.height_userprofile);
        height.setText(String.valueOf(appModel.getCurrentUser().getHeight()));
        TextView location = (TextView)v.findViewById(R.id.location_userprofile);
        location.setText(String.valueOf(appModel.getCurrentUser().getPosition()));
        TextView education = (TextView)v.findViewById(R.id.education_userprofile);
        education.setText(String.valueOf(User.EducationValues[appModel.getCurrentUser().getEducation()]));
        TextView religion = (TextView)v.findViewById(R.id.Religion_userprofile);
        religion.setText(String.valueOf(appModel.getCurrentUser().getReligion()));
        TextView ethnicity = (TextView)v.findViewById(R.id.Ethnicity_userprofile);
        ethnicity.setText(String.valueOf(appModel.getCurrentUser().getEthnicity()));
        TextView smoking = (TextView)v.findViewById(R.id.Smoking_userprofile);
        smoking.setText(String.valueOf(User.SmokingValues[appModel.getCurrentUser().getSmoking()]));
        TextView bodyType = (TextView)v.findViewById(R.id.bodyType_userprofile);
        bodyType.setText(String.valueOf(User.BodyTypeValues[appModel.getCurrentUser().getBodyType()]));
        TextView work = (TextView)v.findViewById(R.id.work_userprofile);
        work.setText(String.valueOf(appModel.getCurrentUser().getWork()));
        TextView income = (TextView)v.findViewById(R.id.income_userprofile);
        income.setText(String.valueOf(User.IncomeValues[appModel.getCurrentUser().getIncome()]));
        TextView marriedStatus = (TextView)v.findViewById(R.id.marriedStatus_userprofile);
        marriedStatus.setText(String.valueOf(User.MarriedStatusValues[appModel.getCurrentUser().getMarriedStatus()]));
        TextView children = (TextView)v.findViewById(R.id.numChildren_userprofile);
        children.setText(String.valueOf(appModel.getCurrentUser().getChildren()));
        TextView story = (TextView)v.findViewById(R.id.story_user);
        story.setText(String.valueOf(appModel.getCurrentUser().getStory()));
        TextView perfectMatch = (TextView)v.findViewById(R.id.perfectmatch_user);
        perfectMatch.setText(String.valueOf(appModel.getCurrentUser().getPerfectMatch()));
        ImageView imgPhoto = (ImageView)v.findViewById(R.id.imgPhoto);
        if (appModel.getCurrentUser().getProfilePhoto().getPhoto() != null) {
            imgPhoto.setImageBitmap(appModel.getCurrentUser().getProfilePhoto().getPhoto());
        }

        Button btnEdit = (Button) v.findViewById(R.id.UserProfileEdit_Button);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new UserProfileEdit_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }
}
