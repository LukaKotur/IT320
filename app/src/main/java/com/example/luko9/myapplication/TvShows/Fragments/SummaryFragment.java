package com.example.luko9.myapplication.TvShows.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luko9.myapplication.R;


public class SummaryFragment extends Fragment {
    private TextView summaryText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment, container, false);
        String summary = getArguments().getString("summary");


        summaryText = (TextView) view.findViewById(R.id.summaryText);
        summaryText.setText(summary);
        return view;
    }
}
