package com.example.luko9.myapplication.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.luko9.myapplication.JSON.Downloaders.JSONSearchDownloader;
import com.example.luko9.myapplication.R;

public class SearchActorsActivity extends AppCompatActivity {

    private EditText searchText;
    private Button btnSearch;
    private String jsonURL = "http://api.tvmaze.com/search/people?q=";
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        lv = (ListView) findViewById(R.id.listView);
        searchText = (EditText) findViewById(R.id.searchText);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Find Actors");

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

    public void btnSearchClicked(View view) {
        new JSONSearchDownloader(this, jsonURL + searchText.getText().toString(), lv).execute();
    }
}
