package edu.uco.houselannister.saveasingle.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.R;

public class ViewMessageFragment extends Fragment
{
    public ViewMessageFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_view_message, container, false);
        ButterKnife.bind(this, view);

        Bundle data = getArguments();

        TextView fromTextView = (TextView)view.findViewById(R.id.from);
        fromTextView.setText(data.getString("From"));

        TextView subjectTextView = (TextView)view.findViewById(R.id.subject);
        subjectTextView.setText(data.getString("Subject"));

        TextView messageTextView = (TextView)view.findViewById(R.id.message);
        messageTextView.setText(data.getString("Message"));
        return view;
    }
}