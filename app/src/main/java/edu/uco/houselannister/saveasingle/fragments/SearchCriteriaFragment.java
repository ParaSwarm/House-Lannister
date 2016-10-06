package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Ages;
import edu.uco.houselannister.saveasingle.domain.EducationLevel;
import edu.uco.houselannister.saveasingle.domain.Interests;
import edu.uco.houselannister.saveasingle.domain.Language;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Religion;
import edu.uco.houselannister.saveasingle.domain.SearchDistances;
import edu.uco.houselannister.saveasingle.domain.Status;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteria;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaAge;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaAnd;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaLanguage;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaRelationhip;
import edu.uco.houselannister.saveasingle.helpers.SearchCriteriaReligion;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by ryan on 9/27/2016.
 */
public class SearchCriteriaFragment extends Fragment {
    private Model appModel;
    private Switch singleSwitch;
    private Switch polySwitch;
    private Button searchButton;
    private CheckBox hasCats, hasDogs, hasNone, wantsKids, mightWantKids, doesNotWantKids, hasKids, doesNotHaveKids,
    highschool, associates, bachelors, masters;

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

        singleSwitch = (Switch) view.findViewById(R.id.single_switch);
        polySwitch = (Switch)view.findViewById(R.id.switch_monogamous);
        hasCats = (CheckBox)view.findViewById(R.id.hasCatsCheckbox);
        hasDogs = (CheckBox)view.findViewById(R.id.hasDogsCheckbox);
        hasNone = (CheckBox)view.findViewById(R.id.hasNoPetsCheckbox);
        wantsKids = (CheckBox)view.findViewById(R.id.wantsKidsCheckbox);
        mightWantKids= (CheckBox)view.findViewById(R.id.mightWantKidsCheckbox);
        doesNotWantKids = (CheckBox)view.findViewById(R.id.doesNotWantKidsCheckbox);
        hasKids = (CheckBox)view.findViewById(R.id.hasKidsCheckbox);
        doesNotHaveKids = (CheckBox)view.findViewById(R.id.doesNotHaveKidsCheckbox);
        final Switch photosSwitch = (Switch)view.findViewById(R.id.hasPhotosSwitch);

        if(appModel.getCurrentUser().getUserPreferences().preferencesSet()) {
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
                //get the selected options from the view and apply them to the user's preferences
                appModel.getCurrentUser().getUserPreferences().setLanguagePreference(languageSpinner.getSelectedItem().toString());
//                appModel.getCurrentUser().getUserPreferences().setReligions(religionSpinner.getSelectedItem().toString());
                appModel.getCurrentUser().getUserPreferences().setOpenToPoly(polySwitch.isChecked());
                ArrayList<Status> statuses = new ArrayList<Status>();
                if(singleSwitch.isChecked()) {
                    statuses.add(Status.SINGLE);
                }
                else {
                    statuses.add(Status.COMPLICATED);
                }
                if(polySwitch.isChecked()) {
                   statuses.add(Status.OPENRELATIONSHIP);
                }
                appModel.getCurrentUser().getUserPreferences().setStatus(statuses);
                ArrayList<User> userList = appModel.getUsers();
                ArrayList<User> matchingUsers = new ArrayList<User>();

                //filter
                //typically people want these to be exclusive and don't want to have outside their selection
                SearchCriteria criteriaAge = new SearchCriteriaAge(Integer.valueOf(minAgeSpinner.getSelectedItem().toString()), Integer.valueOf(maxAgeSpinner.getSelectedItem().toString()));
                SearchCriteria religionCriteria = new SearchCriteriaReligion();
                SearchCriteria languageCriteria = new SearchCriteriaLanguage();
                SearchCriteria statusCriteria = new SearchCriteriaRelationhip(appModel.getCurrentUser().getUserPreferences().getStatus());
                appModel.getCurrentUser().getUserPreferences().setHasCats(hasCats.isChecked());
                appModel.getCurrentUser().getUserPreferences().setHasDogs(hasDogs.isChecked());
                appModel.getCurrentUser().getUserPreferences().setHasNoPets(hasNone.isChecked());
                appModel.getCurrentUser().getUserPreferences().setWantsKids(wantsKids.isChecked());
                appModel.getCurrentUser().getUserPreferences().setMightWantKids(mightWantKids.isChecked());
                appModel.getCurrentUser().getUserPreferences().setDoesNotWantKids(doesNotWantKids.isChecked());
                appModel.getCurrentUser().getUserPreferences().setCurrentlyHasKids(hasKids.isChecked());
                appModel.getCurrentUser().getUserPreferences().setDoesNotCurrentlyHaveKids(doesNotHaveKids.isChecked());
                //criteria for the different combos of kids and pets

                ArrayList<EducationLevel> educationLevels = new ArrayList<EducationLevel>();
                if(highschool.isChecked()) {
                    educationLevels.add(EducationLevel.HSDIPLOMA);
                }
                if(associates.isChecked()) {
                    educationLevels.add(EducationLevel.SOMECOLLEGE);
                }
                if(bachelors.isChecked()) {
                    educationLevels.add(EducationLevel.BACHELORS);
                }
                if(masters.isChecked()) {
                    educationLevels.add(EducationLevel.MASTERS);
                }
                appModel.getCurrentUser().getUserPreferences().setEduLevels(educationLevels);
                //criteria for education
                appModel.getCurrentUser().getUserPreferences().setSearchDistances(SearchDistances.valueOf(distanceSpinner.getSelectedItem().toString()));
                //criteria for distance
                if(photosSwitch.isChecked()) {
                    //add search criteria that has photos
                }
                String tokensEntered = multiAutoCompleteTextView.getText().toString();
                //check tokens entered match with enum values, currently throw out others that aren't existent
            }
        });
    }
}
