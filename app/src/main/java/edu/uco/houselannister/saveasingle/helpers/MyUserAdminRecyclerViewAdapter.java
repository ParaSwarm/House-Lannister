package edu.uco.houselannister.saveasingle.helpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.fragments.AdminUsersFragment.OnUserAdminListFragmentInteractionListener;
import java.util.List;

public class MyUserAdminRecyclerViewAdapter extends RecyclerView.Adapter<MyUserAdminRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;
    private final OnUserAdminListFragmentInteractionListener mListener;

    public MyUserAdminRecyclerViewAdapter(List<User> items, OnUserAdminListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_useradmin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mUsername.setText(mValues.get(position).getName());
        holder.mEmail.setText(mValues.get(position).getEmailAddress());
        holder.mEnabled.setChecked(mValues.get(position).getEnabled());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onUserAdminListFragmentInteraction(holder.mItem);
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
        public final TextView mUsername;
        public final TextView mEmail;
        public final Switch mEnabled;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mUsername = (TextView) view.findViewById(R.id.admin_username);
            mEmail = (TextView) view.findViewById(R.id.admin_email);
            mEnabled = (Switch) view.findViewById(R.id.admin_enabled);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mEmail.getText() + "'";
        }
    }
}
