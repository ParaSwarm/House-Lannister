package edu.uco.houselannister.saveasingle.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.LoginActivity;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by ryan on 9/21/2016.
 */
public class SettingsFragment extends Fragment {

    private Model appModel;
    //View v;
    Button btnDelete, btnReveal;
    TextView userName, email, password;

    private static final String KEY_MOVIE_TITLE = "key_title";

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FragmentAction.
     */
    public static SettingsFragment newInstance(String movieTitle) {
        SettingsFragment fragmentSearch = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        fragmentSearch.setArguments(args);

        return fragmentSearch;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        User cu = AppModel.getAppModelInstance(AppService.getAppServiceInstance()).getCurrentUser();
        User ce = AppModel.getAppModelInstance(AppService.getAppServiceInstance()).getAuthenticatedUser();
        //User cp = AppModel.getAppModelInstance(AppService.getAppServiceInstance()).getCurrentUser();

        //userName = (TextView) view.findViewById(R.id.userNameText);
        ((TextView) view.findViewById(R.id.userNameText)).setText(cu.getName());
        ((TextView) view.findViewById(R.id.emailText)).setText(ce.getEmailAddress());
        //password = (TextView) view.findViewById(R.id.passwordText);

        //userName.setText("Hello");
        //email.setText(cu.getEmailAddress());
        //password.setText("Password");
        /*btnReveal = (Button) v.findViewById(R.id.btnRevealation);
        btnReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextView tv = (TextView) v.findViewById(R.id.emailText);
                //tv.setText("Clicked");
                ((TextView) v.findViewById(R.id.emailText)).setText("Done");
            }
        });*/
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User u = appModel.getCurrentUser();
                //User cu = AppModel.getAppModelInstance(AppService.getAppServiceInstance()).getCurrentUser();
                /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setMessage("Do you Want to delete !!!")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        User u = appModel.getCurrentUser();
                                        appModel.deleteUser(u);
                                        //dialog.dismiss();
                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alertDialog.create();
                alert.setTitle("Delete Account!!!");
                alert.show();
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Deleted!!!!");
                alertDialog.setMessage("Are You Sure?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                User u = appModel.getCurrentUser();
                                appModel.deleteUser(u);
                                //dialog.dismiss();
                            }
                        });
                alertDialog.show();*/
                User u = appModel.getCurrentUser();
                appModel.deleteUser(u);
                Toast.makeText(getActivity(),R.string.Deleted, Toast.LENGTH_SHORT).show();
                Intent i  = new Intent (getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String movieTitle = getArguments().getString(KEY_MOVIE_TITLE);
        //((TextView) view.findViewById(R.id.movie_title)).setText(movieTitle);
    }
}