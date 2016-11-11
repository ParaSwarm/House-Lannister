package edu.uco.houselannister.saveasingle.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by gordon on 11/11/16.
 */

public class QuestionExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<String> listDataHeader;
    private final HashMap<String, List<Question>> listChildData;
    private Context context;

    public QuestionExpandableListAdapter(Context context, List<String> listDataHeader,
                                         HashMap<String, List<Question>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listChildData = listChildData;
    }


    //region Child Overrides
    @Override
    public Question getChild(int groupPosition, int childPosition) {
        return this.listChildData.get(this.listDataHeader.get(groupPosition).toUpperCase()).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Question childQuestion = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_question, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        txtListChild.setText(childQuestion.getQuestion());


            Switch mEnabled = (Switch) convertView.findViewById(R.id.question_enabled);
            mEnabled.setChecked(childQuestion.getEnabled());
            mEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    childQuestion.setEnabled(isChecked);
                    AppModel.getAppModelInstance(AppService.getAppServiceInstance()).saveQuestion(childQuestion);
                }
            });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        String header = this.listDataHeader.get(groupPosition).toUpperCase();
        List<Question> questions = this.listChildData.get(header);
        int s = questions.size();

        return s;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    //endregion Child Overrides


    //region Group Overrides
    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_question_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    //endregion Group Overrides


}
