package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.uco.houselannister.saveasingle.R;

/**
 * Bibash Lama
 */
public class UserProfile_Fragment extends Fragment implements View.OnClickListener{

    View v;
    TextView tv;
    String name;
    public UserProfile_Fragment() {
        // Required empty public constructor
    }

    public static UserProfile_Fragment newInstance(){
        return new UserProfile_Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//trying to transfer values from one fragment to another
//        String value = getArguments().getString("First Name");
  //      Toast.makeText(getActivity(),value,Toast.LENGTH_LONG).show();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_user_profile, container, false);

        Button b = (Button) v.findViewById(R.id.UserProfileEdit_Button);
        b.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        // Create new fragment and transaction
        Fragment newFragment = new UserProfileEdit_Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }
}
