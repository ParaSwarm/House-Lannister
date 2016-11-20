package edu.uco.houselannister.saveasingle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.Response;
import edu.uco.houselannister.saveasingle.fragments.UserQuestionFragment.OnUserQuestionListFragmentInteractionListener;
import edu.uco.houselannister.saveasingle.helpers.Utility;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;


import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Question} and makes a call to the
 * specified {@link OnUserQuestionListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class UserQuestionRecyclerViewAdapter extends RecyclerView.Adapter<UserQuestionRecyclerViewAdapter.ViewHolder> {

    private final List<Question> mValues;
    private final OnUserQuestionListFragmentInteractionListener mListener;

    public UserQuestionRecyclerViewAdapter(List<Question> items, OnUserQuestionListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_question, parent, false);
        ViewHolder ret= new ViewHolder(view);

        return ret;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getQuestion());
        holder.addResponses();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onUserQuestionListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public Question mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        public void addResponses(){
            ListView listView = (ListView) mView.findViewById(R.id.user_question_response_listview);
            AnswerAdapter adapter = new AnswerAdapter(mView.getContext(), R.layout.fragment_user_question_response, mItem.getResponses());
            listView.setAdapter(adapter);
            Utility.setListViewHeightBasedOnChildren(listView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
