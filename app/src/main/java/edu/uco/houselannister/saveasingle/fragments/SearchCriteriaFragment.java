package edu.uco.houselannister.saveasingle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import edu.uco.houselannister.saveasingle.R;

/**
 * Created by ryan on 9/27/2016.
 */
public class SearchCriteriaFragment extends Fragment {

    public SearchCriteriaFragment() {

    }

    public static SearchCriteriaFragment newInstance() {
        SearchCriteriaFragment fragment = new SearchCriteriaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Spinner languageSpinner = (Spinner) view.findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.language_spinner_text, android.R.layout.simple_spinner_dropdown_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);
        //reglion spinner
        Spinner religionSpinner = (Spinner) view.findViewById(R.id.religion_spinner);
        ArrayAdapter<CharSequence> religionAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.religion_spinner_text, android.R.layout.simple_spinner_dropdown_item);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        religionSpinner.setAdapter(religionAdapter);
        //age spinner
        Spinner minAgeSpinner = (Spinner) view.findViewById(R.id.min_age_spinner);
        Integer[] ages = new Integer[]{18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
                52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,64,65,66,67,68,69,70,71,72,73,74,75,
                76,77,78,79,80};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, ages);
        minAgeSpinner.setAdapter(adapter);
        Spinner maxAgeSpinner = (Spinner) view.findViewById(R.id.max_age_spinner);
        maxAgeSpinner.setAdapter(adapter);
        //distance spinner
        Spinner distanceSpinner = (Spinner) view.findViewById(R.id.distance_to_search_spinner);
        ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.distances_to_search, android.R.layout.simple_spinner_dropdown_item);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceSpinner.setAdapter(distanceAdapter);
    }
}
