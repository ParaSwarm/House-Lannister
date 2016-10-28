package edu.uco.houselannister.saveasingle.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ParaSwarm on 10/27/2016.
 */
public class FavoriteUserItemAdapter extends ArrayAdapter<User> {

    Context context;
    int layoutResourceId;
    User data[] = null;
    final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public FavoriteUserItemAdapter(Context context, int layoutResourceId, User[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FavoriteHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new FavoriteHolder();
            holder.nameTextView = (TextView)row.findViewById(R.id.favoriteName);
            holder.lastOnlineTextView = (TextView)row.findViewById(R.id.favoriteLastOnline);
            holder.divider = row.findViewById(R.id.divider);

            row.setTag(holder);
        }
        else
        {
            holder = (FavoriteHolder)row.getTag();
        }

        User user = data[position];

        holder.nameTextView.setText(trimAndEllipsis(user.getName(), 40));
        holder.lastOnlineTextView.setText("Last Seen: " + dateFormat.format(user.getStatus().getLastOnlineDate()));
        holder.divider.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimary));

        return row;
    }

    private String trimAndEllipsis(String text, int trimLength) {
        int maxLength = text.length() < trimLength ? text.length() : trimLength;

        text = text.substring(0, maxLength);

        if(maxLength >= trimLength) {
            text += "...";
        }

        return text;
    }

    static class FavoriteHolder
    {
        TextView nameTextView;
        TextView lastOnlineTextView;
        public View divider;
    }
}