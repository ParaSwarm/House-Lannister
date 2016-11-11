//package edu.uco.houselannister.saveasingle.adapters;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CompoundButton;
//import android.widget.Switch;
//import android.widget.TextView;
//
//import edu.uco.houselannister.saveasingle.R;
//import edu.uco.houselannister.saveasingle.domain.Question;
//import edu.uco.houselannister.saveasingle.fragments.QuestionFragment.OnListFragmentInteractionListener;
//import edu.uco.houselannister.saveasingle.model.AppModel;
//import edu.uco.houselannister.saveasingle.service.AppService;
//
//import java.util.List;
//
//public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
//
//    private final List<Question> mValues;
//    private final OnListFragmentInteractionListener mListener;
//
//    public QuestionAdapter(List<Question> items, OnListFragmentInteractionListener listener) {
//        mValues = items;
//        mListener = listener;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_question, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).getCategory().name());
//        holder.mContentView.setText(mValues.get(position).getQuestion());
//        holder.mEnabled.setChecked(mValues.get(position).getEnabled());
//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mValues.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;
//        public final TextView mIdView;
//        public final TextView mContentView;
//        public final Switch mEnabled;
//        public Question mItem;
//
//        public ViewHolder(View view) {
//            super(view);
//            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.question_category);
//            mContentView = (TextView) view.findViewById(R.id.question_text);
//            mEnabled = (Switch) view.findViewById(R.id.question_enabled);
//            mEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    mItem.setEnabled(isChecked);
//                    AppModel.getAppModelInstance(AppService.getAppServiceInstance()).saveQuestion(mItem);
//                }
//            });
//
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
//    }
//}
