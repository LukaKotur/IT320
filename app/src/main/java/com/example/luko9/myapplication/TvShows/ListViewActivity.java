package com.example.luko9.myapplication.TvShows;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.luko9.myapplication.CustomAdapters.CustomShowListAdapter;
import com.example.luko9.myapplication.DB.DBHelper;
import com.example.luko9.myapplication.Entity.TvShows;
import com.example.luko9.myapplication.R;

import java.util.ArrayList;



public class ListViewActivity extends AppCompatActivity {
    String jsonURL = "http://api.tvmaze.com/shows?page=";
    ListView lv;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dbHelper = new DBHelper(this);
        setTitle("Popular Shows");
        lv = (ListView) findViewById(R.id.lv);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<TvShows> tvShows = dbHelper.getAllShows();
        if (tvShows.size() > 0) {
            lv.setAdapter(new CustomShowListAdapter(this, tvShows));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ArrayList<TvShows> tvShows = dbHelper.getAllShows();
        lv.setAdapter(new CustomShowListAdapter(this, tvShows));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
