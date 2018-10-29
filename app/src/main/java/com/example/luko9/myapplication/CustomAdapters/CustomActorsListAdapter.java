package com.example.luko9.myapplication.CustomAdapters;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luko9.myapplication.Entity.TvShowActor;
import com.example.luko9.myapplication.R;
import com.example.luko9.myapplication.TvShows.IndividualStuff.ActorProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomActorsListAdapter extends BaseAdapter {
    Context context;
    ArrayList<TvShowActor> actors;

    public CustomActorsListAdapter(Context context, ArrayList<TvShowActor> actors) {
        this.context = context;
        this.actors = actors;
    }

    @Override
    public int getCount() {
        return actors.size();
    }

    @Override
    public Object getItem(int position) {
        return actors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.actors_custom_list_item, viewGroup, false);
        }

        TextView nameText = (TextView) convertView.findViewById(R.id.nameTxt);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageList);
        TvShowActor actor = (TvShowActor) this.getItem(position);

        final String name = actor.getName();

        final String character = actor.getCharacter();

        String fullName = name + " as " + character;

        final String img = actor.getImage();

        final String url = actor.getUrl();

        nameText.setText(fullName);

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
        Intent i = new Intent(context, ActorProfileActivity.class);
        i.putExtra("URL_KEY", details[0]);
        i.putExtra("NAME_KEY", details[1]);
        context.startActivity(i);
    }

}
