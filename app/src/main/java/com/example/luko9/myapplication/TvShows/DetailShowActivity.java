package com.example.luko9.myapplication.TvShows;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luko9.myapplication.CustomAdapters.SectionsPageAdapter;
import com.example.luko9.myapplication.DB.DBHelper;
import com.example.luko9.myapplication.Entity.TvShows;
import com.example.luko9.myapplication.R;
import com.example.luko9.myapplication.TvShows.Fragments.ActorFragment;
import com.example.luko9.myapplication.TvShows.Fragments.EpisodesFragment;
import com.example.luko9.myapplication.TvShows.Fragments.SummaryFragment;
import com.squareup.picasso.Picasso;

public class DetailShowActivity extends AppCompatActivity {
    TextView nameTxt, showingDetailTxt, statusDetailTxt, genresDetailTxt, runtimeDetailTxt, ratingDetailTxt;
    ImageView img;
    FloatingActionButton actionButton;
    TvShows tvShow;

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(this);

        /*
        * Sections page adapter
        * */
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tableLayout = (TabLayout) findViewById(R.id.tabs);
        tableLayout.setupWithViewPager(mViewPager);

        /*
        * Filling out the data
        * */
        actionButton = (FloatingActionButton) findViewById(R.id.actionButton);
        nameTxt = (TextView) findViewById(R.id.nameDetailTxt);
        showingDetailTxt = (TextView) findViewById(R.id.showingDetailTxt);
        statusDetailTxt = (TextView) findViewById(R.id.statusDetailTxt);
        genresDetailTxt = (TextView) findViewById(R.id.genresDetailTxt);
        runtimeDetailTxt = (TextView) findViewById(R.id.runtimeDetailTxt);
        ratingDetailTxt = (TextView) findViewById(R.id.ratingDetailTxt);
        img = (ImageView) findViewById(R.id.img);

        Intent i = this.getIntent();

        String name = i.getExtras().getString("NAME_KEY");
        String image = i.getExtras().getString("BIGIMG_KEY");
        String days = i.getExtras().getString("DAYS_KEY");
        String status = i.getExtras().getString("STATUS_KEY");
        String genres = i.getExtras().getString("GENRES_KEY");
        String runtime = i.getExtras().getString("RUNTIME_KEY");
        String rating = i.getExtras().getString("RATING_KEY");
        tvShow = (TvShows) i.getExtras().getSerializable("TVSHOW_OBJECT");

        rating = "Average rating: " + rating;
        System.out.println(tvShow.getIsFavourite());

        if (tvShow.getIsFavourite().equals("true")) {
            actionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.success_green)));
            actionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_white_36dp));
        } else {
            actionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            actionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_white_36dp));
        }


        nameTxt.setText(name);

        showingDetailTxt.setText(days);
        if (status.equals("Status: Ended")) {
            showingDetailTxt.setText("");
        }
        statusDetailTxt.setText(status);
        genresDetailTxt.setText(genres);
        runtimeDetailTxt.setText(runtime);
        ratingDetailTxt.setText(rating);

        Picasso.with(DetailShowActivity.this).load(image).into(img);


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

    private void setupViewPager(ViewPager viewPager) {
        Intent i = this.getIntent();
        String summary = i.getExtras().getString("URL_KEY");
        String weight = i.getExtras().getString("WEIGHT");

        String id = i.getExtras().getString("ID_KEY");
        Bundle bundle = new Bundle();
        bundle.putString("summary", summary);
        bundle.putString("id", id);

        SummaryFragment summaryFragment = new SummaryFragment();
        summaryFragment.setArguments(bundle);

        ActorFragment actorFragment = new ActorFragment();
        actorFragment.setArguments(bundle);

        EpisodesFragment episodesFragment = new EpisodesFragment();
        episodesFragment.setArguments(bundle);


        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(summaryFragment, "Summary");
        adapter.addFragment(actorFragment, "Actors");
        adapter.addFragment(episodesFragment, "Episodes");
        viewPager.setAdapter(adapter);
    }

    public void clickedAddToFavourites(View view) {
        if (tvShow.getIsFavourite().equals("true")) {
            tvShow.setIsFavourite("false");
            dbHelper.updateShow(tvShow);

            actionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            actionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_white_36dp));

            Toast.makeText(this, tvShow.getName() + " removed from favourites", Toast.LENGTH_SHORT).show();
        } else {
            tvShow.setIsFavourite("true");
            dbHelper.updateShow(tvShow);

            actionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.success_green)));
            actionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_white_36dp));

            Toast.makeText(this, tvShow.getName() + " added to favourites", Toast.LENGTH_LONG).show();
        }


    }

}

