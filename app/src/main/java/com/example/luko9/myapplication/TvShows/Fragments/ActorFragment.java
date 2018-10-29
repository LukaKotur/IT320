package com.example.luko9.myapplication.TvShows.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.luko9.myapplication.JSON.Downloaders.JSONActorDownloader;
import com.example.luko9.myapplication.R;


public class ActorFragment extends Fragment {
    private ListView listView;
    final String jsonURL = "http://api.tvmaze.com/shows/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actor_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        String id = getArguments().getString("id");

        new JSONActorDownloader(getActivity(), jsonURL + id + "/cast", listView).execute();
        return view;
    }
}
