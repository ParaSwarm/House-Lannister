package edu.uco.houselannister.saveasingle.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.adapters.QuestionExpandableListAdapter;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.QuestionCategory;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class QuestionFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<Question>> listDataChild;

    public QuestionFragment() {
    }

    public static QuestionFragment newInstance(int columnCount) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);
        Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());

        // Set the adapter
        if (view instanceof ExpandableListView) {
            Context context = view.getContext();
            ExpandableListView expandableListView = (ExpandableListView) view;

            prepareListData(appModel);

            listAdapter = new QuestionExpandableListAdapter(context, listDataHeader, listDataChild);
            expandableListView.setAdapter(listAdapter);

        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Question item);
    }


    private void prepareListData(Model appModel) {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        listDataHeader = Arrays.asList(QuestionCategory.GetNames());
        for (QuestionCategory c : QuestionCategory.values()) {
            listDataChild.put(c.name(), new ArrayList<Question>());
        }

        for (Question q : appModel.getQuestionnaire().getQuestions()) {
            listDataChild.get(q.getCategory().name()).add(q);
        }
    }

}


//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import edu.uco.houselannister.saveasingle.R;
//import edu.uco.houselannister.saveasingle.adapters.QuestionAdapter;
//import edu.uco.houselannister.saveasingle.domain.Model;
//import edu.uco.houselannister.saveasingle.domain.Question;
//import edu.uco.houselannister.saveasingle.model.AppModel;
//import edu.uco.houselannister.saveasingle.service.AppService;
//
//
//public class QuestionFragment extends Fragment {
//
//    // TODO: Customize parameter argument names
//    private static final String ARG_COLUMN_COUNT = "column-count";
//    // TODO: Customize parameters
//    private int mColumnCount = 1;
//    private OnListFragmentInteractionListener mListener;
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public QuestionFragment() {
//    }
//
//    // TODO: Customize parameter initialization
//    @SuppressWarnings("unused")
//    public static QuestionFragment newInstance(int columnCount) {
//        QuestionFragment fragment = new QuestionFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_question_list, container, false);
//        Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
//
//        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new QuestionAdapter(appModel.getQuestionnaire().getQuestions(), mListener));
//        }
//        return view;
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnListFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onListFragmentInteraction(Question item);
//    }
//}
