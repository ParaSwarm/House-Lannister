package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by ryan on 9/22/2016.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.starting_fragment_text)
    TextView mStartingFragmentText;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        User cu = AppModel.getAppModelInstance(AppService.getAppServiceInstance()).getCurrentUser();
        User lu = AppModel.getAppModelInstance(AppService.getAppServiceInstance()).getAuthenticatedUser();
        mStartingFragmentText.append(String.format("\nCurrent Impersonated User: %1$s",cu.getName()));
        mStartingFragmentText.append(String.format("\nCurrent Authenticated User: %1$s",lu.getName()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
