package com.example.luko9.myapplication.TvShows.IndividualStuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.luko9.myapplication.R;

/**
 * Created by luko9 on 02/06/2017.
 */

public class ActorProfileActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actor_profile_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String url = i.getExtras().getString("URL_KEY");
        String name = i.getExtras().getString("NAME_KEY");

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url);

        setTitle(name);
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
