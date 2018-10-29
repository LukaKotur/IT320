package com.example.luko9.myapplication.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luko9.myapplication.Entity.TvShowEpisodes;
import com.example.luko9.myapplication.R;
import com.example.luko9.myapplication.TvShows.IndividualStuff.EpisodeProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomEpisodesListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> _listDataHeader;
    HashMap<String, ArrayList<TvShowEpisodes>> _listDataChild;

    public CustomEpisodesListAdapter(Context context, List<String> _listDataHeader, HashMap<String, ArrayList<TvShowEpisodes>> _listDataChild) {
        this.context = context;
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_header, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupId, int position) {
        return position;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        }

        TextView nameText = (TextView) convertView.findViewById(R.id.nameTxt);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageList);
        TextView summaryText = (TextView) convertView.findViewById(R.id.summaryTxt);


        TvShowEpisodes episode = (TvShowEpisodes) this.getChild(groupPosition, childPosition);


        final String name = episode.getEpisodeName();

        final String eNumber = episode.getEpisodeNumber();

        final String sNumber = episode.getSeasonNumber();

        final String img = episode.getImg();

        final String url = episode.getUrl();

        final String summary = episode.getSummary();

        String summaryExcerpt = summary;

        if (summaryExcerpt.length() > 150) {
            summaryExcerpt = summaryExcerpt.substring(0, 150);
        }
        summaryExcerpt = summaryExcerpt.trim();
        summaryExcerpt += "...";


        String fullName = name + ", Season " + sNumber + ", Episode " + eNumber;

        nameText.setText(fullName);

        summaryText.setText(summaryExcerpt);

        Picasso.with(context).load(img).into(imageView);


        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openProfileActivity(url, name);
            }
        });

        return convertView;
    }


    private void openProfileActivity(String... details) {
        if (!details[0].equals("blank")) {
            Intent i = new Intent(context, EpisodeProfileActivity.class);
            i.putExtra("URL_KEY", details[0]);
            i.putExtra("NAME_KEY", details[1]);
            context.startActivity(i);
        }
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
