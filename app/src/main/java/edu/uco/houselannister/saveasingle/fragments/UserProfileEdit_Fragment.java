package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserProfileEdit_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserProfileEdit_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfileEdit_Fragment extends Fragment implements View.OnClickListener {

    View v;
    private static final String ARG_PARAM1 = "";
    String First_Name;

    public UserProfileEdit_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_user_profile_edit_, container, false);
        Button b = (Button) v.findViewById(R.id.UserProfileSave_Button);
        b.setOnClickListener(this);

        First_Name = getArguments().getString("first_name");
        EditText fstName = (EditText) v.findViewById(R.id.Firstname_userprofile_edittext);
        fstName.setText(First_Name);


        return v;
    }

    @Override
    public void onClick(View v) {

        //Bundle data = new Bundle();
        //data.putString("FirstName", First_Name);
        //data.putString("Subject", selectedMessage.getSubject());
        //data.putString("Message", selectedMessage.getMessage());

        //FragmentNavigationManager navManager = FragmentNavigationManager.obtain((MainActivity) getActivity());
        //navManager.showFragmentUserEditProfile(data);
        // Create new fragment and transaction
        Fragment newFragment = new UserProfile_Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

}
