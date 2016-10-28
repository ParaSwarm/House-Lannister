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
public class UserProfile_Fragment extends Fragment {

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
        TextView fullname = (TextView)v.findViewById(R.id.lblFullName);
        fullname.setText("Full Name: " + appModel.getCurrentUser().getFullName());
        TextView displayName = (TextView)v.findViewById(R.id.lblDisplayName);
        displayName.setText("Display Name: " + appModel.getCurrentUser().getName());
        TextView age = (TextView)v.findViewById(R.id.lblAge);
        age.setText("Age: " + appModel.getCurrentUser().getAge());
        TextView gender = (TextView)v.findViewById(R.id.lblGender);
        gender.setText("Gender: " + User.GenderValues[appModel.getCurrentUser().getGender()]);
        TextView height = (TextView)v.findViewById(R.id.lblHeight);
        height.setText("Height: " + appModel.getCurrentUser().getHeight());
        TextView location = (TextView)v.findViewById(R.id.lblLocation);
        location.setText("Location: " + appModel.getCurrentUser().getPosition());
        TextView education = (TextView)v.findViewById(R.id.lblEducation);
        education.setText("Education: " + appModel.getCurrentUser().getEducation());
        TextView religion = (TextView)v.findViewById(R.id.lblReligion);
        religion.setText("Religion: " + appModel.getCurrentUser().getReligion());
        TextView ethnicity = (TextView)v.findViewById(R.id.lblethnicity);
        ethnicity.setText("Ethnicity: " + appModel.getCurrentUser().getEthnicity());
        TextView smoking = (TextView)v.findViewById(R.id.lblSmoking);
        smoking.setText("Smoking: " + User.SmokingValues[appModel.getCurrentUser().getSmoking()]);
        TextView bodyType = (TextView)v.findViewById(R.id.lblBodyType);
        bodyType.setText("Body Type: " + User.BodyTypeValues[appModel.getCurrentUser().getBodyType()]);
        TextView work = (TextView)v.findViewById(R.id.lblWork);
        work.setText("Work: " + appModel.getCurrentUser().getWork());
        TextView income = (TextView)v.findViewById(R.id.lblIncome);
        income.setText("Income: " + appModel.getCurrentUser().getIncome());
        TextView marriedStatus = (TextView)v.findViewById(R.id.lblMarriedStatus);
        marriedStatus.setText("Married Status: " + User.MarriedStatusValues[appModel.getCurrentUser().getMarriedStatus()]);
        TextView children = (TextView)v.findViewById(R.id.lblChildren);
        children.setText("Children: " + appModel.getCurrentUser().getChildren());
        TextView story = (TextView)v.findViewById(R.id.lblStory);
        story.setText("Story: " + appModel.getCurrentUser().getStory());
        TextView perfectMatch = (TextView)v.findViewById(R.id.lblPerfectMatch);
        perfectMatch.setText("Perfect Match: " + appModel.getCurrentUser().getPerfectMatch());
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
