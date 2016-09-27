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
        Spinner reglionSpinner = (Spinner) view.findViewById(R.id.relgion_spinner);
        ArrayAdapter<CharSequence> religionAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.religion_spinner_text, android.R.layout.simple_spinner_dropdown_item);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reglionSpinner.setAdapter(religionAdapter);
    }
}
