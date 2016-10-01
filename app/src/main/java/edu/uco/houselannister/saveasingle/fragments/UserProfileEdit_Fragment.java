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

        return v;
    }

    @Override
    public void onClick(View v) {

        // Create new fragment and transaction
        Fragment newFragment = new UserProfile_Fragment();
        /*
        Bundle args = new Bundle();
        EditText firstName = (EditText) v.findViewById(R.id.Firstname_userprofile_edittext);
        String first_Name = firstName.getText().toString();
        args.putString("First Name",first_Name);
        newFragment.setArguments(args);*/
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
/*
        Bundle bundle = new Bundle();
        bundle.putString("AAA", "T");
        Fragment UserProfile_Fragment = new UserProfile_Fragment();
        UserProfile_Fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.FirstName_UserProfile_TextView, UserProfile_Fragment, "t")
                .addToBackStack("t").commit();
*/
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

}
