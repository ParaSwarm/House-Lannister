package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uco.houselannister.saveasingle.R;

/**
 * Created by ryan on 9/22/2016.
 */
public class MainFragment extends Fragment {

//    private static final String KEY_MOVIE_TITLE = "key_title";


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        //i don't need to have arguments for this fragment
//        Bundle args = new Bundle();
//        args.putString(KEY_MOVIE_TITLE, movieTitle);
//        fragment.setArguments(args);

        return fragment;}

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
