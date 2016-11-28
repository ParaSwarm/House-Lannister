package edu.uco.houselannister.saveasingle.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.domain.Ages;
import edu.uco.houselannister.saveasingle.domain.EducationLevel;
import edu.uco.houselannister.saveasingle.domain.Gender;
import edu.uco.houselannister.saveasingle.domain.Interests;
import edu.uco.houselannister.saveasingle.domain.Language;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Religion;
import edu.uco.houselannister.saveasingle.domain.SearchDistances;
import edu.uco.houselannister.saveasingle.domain.Status;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.helpers.DummyUserCreator;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteria;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaAge;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaAnd;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaGenger;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaHasCats;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaHasDogs;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaHasPhotos;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaLanguage;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaRelationhip;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaReligion;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by ryan on 9/27/2016.
 */
public class SearchCriteriaFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener{
    private Model appModel;
    private Switch singleSwitch;
    private Switch polySwitch;
    private Button searchButton;
    private CheckBox hasCats, hasDogs, hasNone, wantsKids, mightWantKids, doesNotWantKids, hasKids, doesNotHaveKids,
            highschool, associates, bachelors, masters;
    private GoogleApiClient apiClient;
    private Location mLastLocation;
    private LocationRequest locationRequest;
    private final long UPDATE_INTERVAL = 10 * 1000;
    private final long FASTEST_INTERVAL = 2000;
    private static final int REQUEST_CODE = 1;


    public SearchCriteriaFragment() {

    }

    public static SearchCriteriaFragment newInstance() {
        SearchCriteriaFragment fragment = new SearchCriteriaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        if (apiClient == null) {
            apiClient = new GoogleApiClient.Builder(this.getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            apiClient.connect();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Spinner languageSpinner = (Spinner) view.findViewById(R.id.language_spinner);
        DummyUserCreator creator = new DummyUserCreator();


        ArrayAdapter<CharSequence> languageAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Language.GetNames());
        //ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.language_spinner_text, android.R.layout.simple_spinner_dropdown_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);

        //region reglion spinner
        final Spinner religionSpinner = (Spinner) view.findViewById(R.id.religion_spinner);
        ArrayAdapter<CharSequence> religionAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Religion.GetIsm());
        //ArrayAdapter<CharSequence> religionAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.religion_spinner_text, android.R.layout.simple_spinner_dropdown_item);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        religionSpinner.setAdapter(religionAdapter);
        //endregion reglion spinner

        //age spinner
        final Spinner minAgeSpinner = (Spinner) view.findViewById(R.id.min_age_spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Ages.GetAges());
        minAgeSpinner.setAdapter(adapter);
        final Spinner maxAgeSpinner = (Spinner) view.findViewById(R.id.max_age_spinner);
        maxAgeSpinner.setAdapter(adapter);
        //distance spinner
        final Spinner distanceSpinner = (Spinner) view.findViewById(R.id.distance_to_search_spinner);
        ArrayAdapter<CharSequence> distanceAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, SearchDistances.GetDistances());
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceSpinner.setAdapter(distanceAdapter);

        final Spinner genderSpinner = (Spinner)view.findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> genderAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Gender.GetGenders());
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        singleSwitch = (Switch) view.findViewById(R.id.single_switch);
        polySwitch = (Switch) view.findViewById(R.id.switch_monogamous);
        hasCats = (CheckBox) view.findViewById(R.id.hasCatsCheckbox);
        hasDogs = (CheckBox) view.findViewById(R.id.hasDogsCheckbox);
        hasNone = (CheckBox) view.findViewById(R.id.hasNoPetsCheckbox);
        wantsKids = (CheckBox) view.findViewById(R.id.wantsKidsCheckbox);
        mightWantKids = (CheckBox) view.findViewById(R.id.mightWantKidsCheckbox);
        doesNotWantKids = (CheckBox) view.findViewById(R.id.doesNotWantKidsCheckbox);
        hasKids = (CheckBox) view.findViewById(R.id.hasKidsCheckbox);
        doesNotHaveKids = (CheckBox) view.findViewById(R.id.doesNotHaveKidsCheckbox);
        highschool = (CheckBox) view.findViewById(R.id.highSchoolCheckbox);
        associates = (CheckBox) view.findViewById(R.id.associatesCheckbox);
        bachelors = (CheckBox) view.findViewById(R.id.bachelorsCheckbox);
        masters = (CheckBox) view.findViewById(R.id.mastersCheckbox);
        final Switch photosSwitch = (Switch) view.findViewById(R.id.hasPhotosSwitch);

        if (appModel.getCurrentUser().getUserPreferences().preferencesSet()) {
            languageSpinner.setSelection(Language.valueOf(appModel.getCurrentUser().getUserDemographics().getMyLanguage().toString()).ordinal());
            religionSpinner.setSelection(Religion.valueOf(appModel.getCurrentUser().getUserDemographics().getMyReligion().toString()).ordinal());
            if (appModel.getCurrentUser().getUserPreferences().getAgeLow() < 18) {
                appModel.getCurrentUser().getUserPreferences().setAgeLow(18);
            }
            if (appModel.getCurrentUser().getUserPreferences().getAgeHigh() > 80) {
                appModel.getCurrentUser().getUserPreferences().setAgeHigh(80);
            }
            minAgeSpinner.setSelection(Ages.valueOf(appModel.getCurrentUser().getUserPreferences().getAgeLow().toString()).ordinal());
            maxAgeSpinner.setSelection(Ages.valueOf(appModel.getCurrentUser().getUserPreferences().getAgeHigh().toString()).ordinal());
            singleSwitch.setChecked(appModel.getCurrentUser().getUserPreferences().getPrefersPhotos());
            polySwitch.setChecked(appModel.getCurrentUser().getUserPreferences().getOpenToPoly());
        }
        ArrayAdapter<CharSequence> tokenAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_dropdown_item_1line, Interests.GetInterests());
        final MultiAutoCompleteTextView multiAutoCompleteTextView = (MultiAutoCompleteTextView) view.findViewById(R.id.interests_multitoken);
        multiAutoCompleteTextView.setAdapter(tokenAdapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        searchButton = (Button) view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> userList = appModel.getUsers();
                ArrayList<User> matchingUsers = new ArrayList<User>();
                userList.get(0).setLocation(mLastLocation);
                if(mLastLocation == null) {
                    gpsOff();
                    return;
                }
                Location location = new Location(mLastLocation);
                location.setLatitude(65.9669);
                location.setLongitude(-18.5333);
                Location location2 = new Location(mLastLocation);
                location2.setLatitude(65.94752127);
                location2.setLongitude(-18.51041794);
                userList.get(0).setLocation(location2);
                userList.get(1).setLocation(location);
                Location location1 = new Location(mLastLocation);
                location1.setLatitude(65.96297876);
                location1.setLongitude(-18.50715637);
                userList.get(3).setLocation(location2);
                userList.get(0).getUserDemographics().setMyGender(Gender.MALE);
                userList.get(1).getUserDemographics().setMyGender(Gender.FEMALE);
                userList.get(2).setLocation(location1);
                userList.get(2).getUserDemographics().setMyGender(Gender.OTHER);
                //get the selected options from the view and apply them to the user's preferences
                appModel.getCurrentUser().getUserPreferences().setLanguagePreference(Language.valueOf(languageSpinner.getSelectedItem().toString().toUpperCase()));
                ArrayList<Gender> genders = new ArrayList<Gender>();
                genders.add(Gender.valueOf(genderSpinner.getSelectedItem().toString().toUpperCase()));
                appModel.getCurrentUser().getUserPreferences().setGenders(genders);
                SearchCriteria genderCriteria = new SearchCriteriaGenger(genders);
                matchingUsers.addAll(genderCriteria.meetsSearchCriteria(userList));

                //gets distances and checks matches are in that range
                SearchDistances distances = SearchDistances.ONEMILE;
                String temp = distanceSpinner.getSelectedItem().toString();
                switch (temp) {
                    case "1 Mile": distances = SearchDistances.ONEMILE; break;
                    case "5 Miles": distances = SearchDistances.FIVEMILES; break;
                    case "10 Miles": distances = SearchDistances.TENMILES; break;
                    case "15 Miles": distances = SearchDistances.FIFTEENMILES; break;
                    case "25 Miles": distances = SearchDistances.TWENTYFIVEMILES; break;
                    case "50 Miles": distances = SearchDistances.FIFTYMILES; break;
                }
                float distanceRange = 0;
                switch (distances) {
                    case ONEMILE: distanceRange = 1609; break;
                    case FIVEMILES: distanceRange = 8046; break;
                    case TENMILES: distanceRange = 16093; break;
                    case FIFTEENMILES: distanceRange = 24140; break;
                    case TWENTYFIVEMILES: distanceRange = 40233; break;
                    case FIFTYMILES: distanceRange = 80467; break;
                }
                if(mLastLocation != null) {
                    for (int i = 0; i < userList.size(); i++) {
                        float distanceToMatch = mLastLocation.distanceTo(userList.get(i).getLocation());
                        if (distanceToMatch <= distanceRange) {
                            matchingUsers.add(userList.get(i));
                        }
                    }
                }
                else {
                    for (int i = 0; i < userList.size(); i++) {
                        Location tempLocation = new Location(mLastLocation);
                        tempLocation.setLatitude(65.9669);
                        tempLocation.setLongitude(-18.5333);
                        float distanceToMatch = tempLocation.distanceTo(userList.get(i).getLocation());
                        if (distanceToMatch <= distanceRange) {
                            matchingUsers.add(userList.get(i));
                        }
                    }
                }


//                appModel.getCurrentUser().getUserPreferences().setReligions(religionSpinner.getSelectedItem().toString());
                appModel.getCurrentUser().getUserPreferences().setOpenToPoly(polySwitch.isChecked());
                ArrayList<Status> statuses = new ArrayList<Status>();
                if (singleSwitch.isChecked()) {
                    statuses.add(Status.SINGLE);
                } else {
                    statuses.add(Status.COMPLICATED);
                }
                if (polySwitch.isChecked()) {
                    statuses.add(Status.OPENRELATIONSHIP);
                }
                appModel.getCurrentUser().getUserPreferences().setStatus(statuses);


                //filter
                //typically people want these to be exclusive and don't want to have outside their selection
                SearchCriteria criteriaAge = new SearchCriteriaAge(Integer.valueOf(minAgeSpinner.getSelectedItem().toString()), Integer.valueOf(maxAgeSpinner.getSelectedItem().toString()));
                matchingUsers.addAll(criteriaAge.meetsSearchCriteria(userList));

                SearchCriteria religionCriteria = new SearchCriteriaReligion();
//                matchingUsers.addAll(religionCriteria.meetsSearchCriteria(userList));

                SearchCriteria languageCriteria = new SearchCriteriaLanguage();
//                matchingUsers.addAll(languageCriteria.meetsSearchCriteria(userList));

                SearchCriteria statusCriteria = new SearchCriteriaRelationhip(appModel.getCurrentUser().getUserPreferences().getStatus());
//                matchingUsers.addAll(statusCriteria.meetsSearchCriteria(userList));

                appModel.getCurrentUser().getUserPreferences().setHasCats(hasCats.isChecked());
                SearchCriteriaHasCats hasCatsCriteria = new SearchCriteriaHasCats(appModel.getCurrentUser().getUserPreferences().isHasCats());
//                matchingUsers.addAll(hasCatsCriteria.meetsSearchCriteria(userList));

                appModel.getCurrentUser().getUserPreferences().setHasDogs(hasDogs.isChecked());
                SearchCriteriaHasDogs hasDogsCriteria = new SearchCriteriaHasDogs(appModel.getCurrentUser().getUserPreferences().isHasDogs());
//                matchingUsers.addAll(hasDogsCriteria.meetsSearchCriteria(userList));

                appModel.getCurrentUser().getUserPreferences().setHasNoPets(hasNone.isChecked());
                if (appModel.getCurrentUser().getUserPreferences().isHasNoPets()) {
                    //only want people that have no pets
                } else {
                    //don't care if they don't have pets

                }
                appModel.getCurrentUser().getUserPreferences().setWantsKids(wantsKids.isChecked());
                appModel.getCurrentUser().getUserPreferences().setMightWantKids(mightWantKids.isChecked());
                appModel.getCurrentUser().getUserPreferences().setDoesNotWantKids(doesNotWantKids.isChecked());
                appModel.getCurrentUser().getUserPreferences().setCurrentlyHasKids(hasKids.isChecked());
                appModel.getCurrentUser().getUserPreferences().setDoesNotCurrentlyHaveKids(doesNotHaveKids.isChecked());
                //criteria for the different combos of kids and pets

                ArrayList<EducationLevel> educationLevels = new ArrayList<EducationLevel>();
                if (highschool.isChecked()) {
                    educationLevels.add(EducationLevel.HSDIPLOMA);
                }
                if (associates.isChecked()) {
                    educationLevels.add(EducationLevel.SOMECOLLEGE);
                }
                if (bachelors.isChecked()) {
                    educationLevels.add(EducationLevel.BACHELORS);
                }
                if (masters.isChecked()) {
                    educationLevels.add(EducationLevel.MASTERS);
                }
                appModel.getCurrentUser().getUserPreferences().setEduLevels(educationLevels);
                //criteria for education
//                appModel.getCurrentUser().getUserPreferences().setSearchDistances(SearchDistances.valueOf(distanceSpinner.getSelectedItem().toString().toUpperCase()));
                //criteria for distance
                if (photosSwitch.isChecked()) {
                    SearchCriteria photoCriteria = new SearchCriteriaHasPhotos();
                    matchingUsers.addAll(photoCriteria.meetsSearchCriteria(userList));
                }
                String tokensEntered = multiAutoCompleteTextView.getText().toString();
                //check tokens entered match with enum values, currently throw out others that aren't existent
                FragmentNavigationManager manager = FragmentNavigationManager.getsInstance();
//                LatLng latLng = new LatLng(35.4676, -97.5164);

                Set<User> hastSet = new HashSet<User>();
                hastSet.addAll(matchingUsers);
                matchingUsers.clear();
                matchingUsers.addAll(hastSet);
                manager.showFragmentMap(mLastLocation, matchingUsers);
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            if (mLastLocation != null) {
//                mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
                double lastLatitude = mLastLocation.getLatitude();
//                mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
                double lastLongitude = mLastLocation.getLongitude();
            }
            else {
            }
            startLocationUpdates();
            }
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            return;


    }

    private void startLocationUpdates() {
        locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(UPDATE_INTERVAL).setFastestInterval(FASTEST_INTERVAL);
        if (ContextCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

            // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    String result = connectionResult.toString();
    }

    @Override
    public void onStart() {
        super.onStart();
//        apiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        apiClient.disconnect();
    }

    @Override
    public void onLocationChanged(Location location) {
            // New location has now been determined
            String msg = "Updated Location: " +
                    Double.toString(location.getLatitude()) + "," +
                    Double.toString(location.getLongitude());
            Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
            // You can now create a LatLng Object for use with maps
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the task you need to do.

                } else {

                    // permission denied, boo! Disable the functionality that depends on this permission.
                }
                return;
            }
        }
    }

    public void gpsOff() {
        String msg = MainActivity.getMainInstance().getResources().getString(R.string.gpsServices);
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
