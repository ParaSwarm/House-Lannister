package edu.uco.houselannister.saveasingle.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.Response;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by gordon on 11/11/16.
 */

public class AnswerAdapter extends ArrayAdapter<String>{

    List<String> responses;


    public AnswerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        responses = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_user_question_response, null);
        }

        String i = responses.get(position);
        if(i != null){
            TextView responseText = (TextView) view.findViewById(R.id.question_response_text);
            responseText.setText(i);

            CheckBox responseChecked = (CheckBox) view.findViewById(R.id.question_response);
        }

        return view;
    }


}
