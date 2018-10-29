package com.example.luko9.myapplication.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.luko9.myapplication.DB.DBHelper;
import com.example.luko9.myapplication.Entity.TvShows;
import com.example.luko9.myapplication.JSON.Downloaders.JSONDownloader;
import com.example.luko9.myapplication.R;
import com.example.luko9.myapplication.TvShows.FavouriteShowsListActivity;
import com.example.luko9.myapplication.TvShows.ListViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String jsonURL = "http://api.tvmaze.com/shows?page=";
    private SwitchCompat aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#24292f"));
        DBHelper dbHelper = new DBHelper(this);
        aSwitch = (SwitchCompat) findViewById(R.id.aSwitch);


        ArrayList<TvShows> shows = dbHelper.getAllShows();
        if (shows.size() == 0) {
            new JSONDownloader(this, jsonURL).execute();
        }


    }


    public void clickedDetail(View view) {
        Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
        startActivity(intent);
    }

    public void clickedSearch(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    public void clickedSearchPeople(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActorsActivity.class);
        startActivity(intent);
    }

    public void clickedFavourite(View view) {
        Intent intent = new Intent(MainActivity.this, FavouriteShowsListActivity.class);
        startActivity(intent);
    }

    public void setAlarm() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
}