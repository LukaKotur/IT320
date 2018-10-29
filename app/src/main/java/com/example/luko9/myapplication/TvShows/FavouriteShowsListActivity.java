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


public class FavouriteShowsListActivity extends AppCompatActivity {
    DBHelper dbHelper;
    private ListView lv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(this);
        setTitle("Favourite TV Shows");


        lv = (ListView) findViewById(R.id.lv);
        ArrayList<TvShows> tvShows = dbHelper.getAllFavouriteShows();
        lv.setAdapter(new CustomShowListAdapter(this, tvShows));
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        ArrayList<TvShows> tvShows = dbHelper.getAllFavouriteShows();
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
