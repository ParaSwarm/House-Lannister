package edu.uco.houselannister.saveasingle.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Message;

/**
 * Created by ParaSwarm on 10/27/2016.
 */
public class MessageItemAdapter extends ArrayAdapter<Message> {

    Context context;
    int layoutResourceId;
    Message data[] = null;

    public MessageItemAdapter(Context context, int layoutResourceId, Message[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MessageHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new MessageHolder();
            holder.fromTextView = (TextView)row.findViewById(R.id.messageFrom);
            holder.subjectTextView = (TextView)row.findViewById(R.id.messageSubject);
            holder.textTextView = (TextView)row.findViewById(R.id.messageText);
            holder.divider = row.findViewById(R.id.divider);

            row.setTag(holder);
        }
        else
        {
            holder = (MessageHolder)row.getTag();
        }

        Message message = data[position];

        holder.fromTextView.setText(trimAndEllipsis(message.getFrom().getName(), 40));
        holder.subjectTextView.setText(trimAndEllipsis(message.getSubject(), 55));
        holder.textTextView.setText(trimAndEllipsis(message.getMessage(), 70));
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

    static class MessageHolder
    {
        TextView fromTextView;
        TextView subjectTextView;
        TextView textTextView;
        public View divider;
    }
}