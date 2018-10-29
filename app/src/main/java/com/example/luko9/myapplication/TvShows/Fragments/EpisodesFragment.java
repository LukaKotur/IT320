package com.example.luko9.myapplication.TvShows.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.luko9.myapplication.CustomAdapters.CustomEpisodesListAdapter;
import com.example.luko9.myapplication.Entity.TvShowEpisodes;
import com.example.luko9.myapplication.JSON.Downloaders.JSONEpisodesDownloader;
import com.example.luko9.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EpisodesFragment extends Fragment {
    List<String> listDataHeader;
    HashMap<String, ArrayList<TvShowEpisodes>> listDataChild;
    ExpandableListView listView;
    final String jsonURL = "http://api.tvmaze.com/shows/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.episodes_fragment, container, false);

        listView = (ExpandableListView) view.findViewById(R.id.expListView);


        String id = getArguments().getString("id");

        new JSONEpisodesDownloader(getActivity(), jsonURL + id + "/episodes", listView).execute();

        return view;
    }
}

