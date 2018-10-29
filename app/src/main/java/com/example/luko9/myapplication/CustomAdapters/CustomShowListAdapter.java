package com.example.luko9.myapplication.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luko9.myapplication.Entity.TvShows;
import com.example.luko9.myapplication.R;
import com.example.luko9.myapplication.TvShows.DetailShowActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class CustomShowListAdapter extends BaseAdapter {

    Context context;
    ArrayList<TvShows> tvShows;

    public CustomShowListAdapter(Context context, ArrayList<TvShows> tvshows) {
        this.context = context;
        Collections.sort(tvshows, new Comparator<TvShows>() {
            @Override
            public int compare(TvShows lhs, TvShows rhs) {
                Double first = Double.parseDouble(rhs.getWeight());
                Double second = Double.parseDouble(lhs.getWeight());

                return first.compareTo(second);
            }
        });
        this.tvShows = tvshows;

    }

    @Override
    public int getCount() {
        return tvShows.size();
    }

    @Override
    public Object getItem(int position) {
        return tvShows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tvshow_custom_list_item, viewGroup, false);
        }

        TextView nameText = (TextView) convertView.findViewById(R.id.nameTxt);
        TextView urlText = (TextView) convertView.findViewById(R.id.urlTxt);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageList);
        final TvShows tvShow = (TvShows) this.getItem(position);

        final String name = tvShow.getName();
        final String url = tvShow.getSummary();
        final String img = tvShow.getImage();
        final String bigImg = tvShow.getBigImage();
        final String days = tvShow.getDays();
        final String status = tvShow.getStatus();
        final String genres = tvShow.getGenres();
        final String runtime = tvShow.getRuntime();
        final int id = tvShow.getId();
        final String rating = tvShow.getRating();
        final String isFavourite = tvShow.getIsFavourite();
        final String weight = tvShow.getWeight();

        String urlExcerpt = url;

        nameText.setText(tvShow.getName());
        if (urlExcerpt.length() > 100) {
            urlExcerpt = url.substring(0, 100);
        }
        urlExcerpt += "...";
        urlText.setText(urlExcerpt);
        Picasso.with(context).load(img).into(imageView);

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openDetailActivity(tvShow, name, url, img, bigImg, days, status, genres, runtime, String.valueOf(id), rating, isFavourite, weight);
            }
        });
        return convertView;
    }


    private void openDetailActivity(TvShows tvshow, String... details) {
        Intent i = new Intent(context, DetailShowActivity.class);
        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("URL_KEY", details[1]);
        i.putExtra("IMG_KEY", details[2]);
        i.putExtra("BIGIMG_KEY", details[3]);
        i.putExtra("DAYS_KEY", details[4]);
        i.putExtra("STATUS_KEY", details[5]);
        i.putExtra("GENRES_KEY", details[6]);
        i.putExtra("RUNTIME_KEY", details[7]);
        i.putExtra("ID_KEY", details[8]);
        i.putExtra("RATING_KEY", details[9]);
        i.putExtra("ISFAVOURITE_KEY", details[10]);
        i.putExtra("WEIGHT", details[11]);
        i.putExtra("TVSHOW_OBJECT", tvshow);
        System.out.println(details[8]);
        context.startActivity(i);
    }
}
