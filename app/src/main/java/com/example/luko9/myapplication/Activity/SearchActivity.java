package com.example.luko9.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luko9.myapplication.CustomAdapters.CustomShowListAdapter;
import com.example.luko9.myapplication.DB.DBHelper;
import com.example.luko9.myapplication.Entity.TvShows;
import com.example.luko9.myapplication.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText searchText;
    private Button btnSearch;
    private ListView lv;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Find a TV Show");

        dbHelper = new DBHelper(this);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        lv = (ListView) findViewById(R.id.listView);
        searchText = (EditText) findViewById(R.id.searchText);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        ArrayList<TvShows> shows = dbHelper.getShowsByName(searchText.getText().toString());
        lv.setAdapter(new CustomShowListAdapter(this, shows));
    }

    public void btnSearchClicked(View view) {
        ArrayList<TvShows> shows = dbHelper.getShowsByName(searchText.getText().toString());
        if(shows.size() == 0){
            Toast.makeText(this, "We coulnd't find what you were looking for, sorry!", Toast.LENGTH_SHORT).show();
        }
        lv.setAdapter(new CustomShowListAdapter(this, shows));
    }
}
