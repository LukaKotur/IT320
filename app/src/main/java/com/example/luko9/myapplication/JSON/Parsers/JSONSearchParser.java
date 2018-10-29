package com.example.luko9.myapplication.JSON.Parsers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luko9.myapplication.CustomAdapters.CustomSearchActorsAdapter;
import com.example.luko9.myapplication.Entity.TvShowActor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Klasa zaduzena za parsiranje dobijenih informacija od API
 */
public class JSONSearchParser extends AsyncTask<Void, Void, Boolean> {
    private final Context context;
    private final String jsonData;
    private final ListView lv;

    private ProgressDialog pd;
    private final ArrayList<TvShowActor> actors = new ArrayList<>();

    public JSONSearchParser(Context context, String jsonData, ListView lv) {
        this.context = context;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(context);
        pd.setTitle("Parse");
        pd.setMessage("Parsing....please wait");
        pd.show();

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        pd.dismiss();

        if (isParsed) {
            if (actors.size() > 0) {
                lv.setAdapter(new CustomSearchActorsAdapter(context, actors));
            } else {
                Toast.makeText(context, "We coulnd't find the show you were looking for, sorry!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Unable to Parse, check your log output", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parse() {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            actors.clear();

            createAndAddActors(jsonArray);

            return true;

        } catch (
                JSONException e)

        {
            e.printStackTrace();
            return false;
        }
    }

    private void createAndAddActors(JSONArray jsonArray) throws JSONException {
        TvShowActor actor;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);
            JSONObject jPerson = jObj.getJSONObject("person");

            String name = jPerson.getString("name");
            String url = jPerson.getString("url");
            String character = "";
            String image;
            if (jPerson.isNull("image")) {
                image = "https://www.nextpointbearing.com/wp-content/themes/DiviFX/images/placeholder.jpg";

            } else {
                JSONObject jImage = jPerson.getJSONObject("image");
                image = jImage.getString("medium");
            }

            actor = new TvShowActor(name, character, image, url);

            actors.add(actor);
        }
    }

}
