package com.example.luko9.myapplication.JSON.Parsers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luko9.myapplication.CustomAdapters.CustomActorsListAdapter;
import com.example.luko9.myapplication.Entity.TvShowActor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Klasa zaduzena za parsiranje dobijenih informacija od API
 */
public class JSONActorsParser extends AsyncTask<Void, Void, Boolean> {
    private final Context context;
    private final String jsonData;
    private final ListView lv;

    private ProgressDialog pd;
    private final ArrayList<TvShowActor> actors = new ArrayList<>();

    public JSONActorsParser(Context context, String jsonData, ListView lv) {
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
            lv.setAdapter(new CustomActorsListAdapter(context, actors));
        } else {
            Toast.makeText(context, "Unable to Parse, check your log output", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parse() {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            actors.clear();

            //Create and add actors to the "actors" ArrayList
            createAndAddActors(jsonArray);

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createAndAddActors(JSONArray jsonArray) throws JSONException {
        TvShowActor actor;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);
            JSONObject personObj = jObj.getJSONObject("person");
            JSONObject characterObj = jObj.getJSONObject("character");
            String image;
            if (personObj.isNull("image")) {
                image = "https://www.nextpointbearing.com/wp-content/themes/DiviFX/images/placeholder.jpg";
            } else {
                JSONObject imageObj = personObj.getJSONObject("image");
                image = imageObj.getString("medium");
            }

            String name = personObj.getString("name");
            String character = characterObj.getString("name");
            String url = personObj.getString("url");

            actor = new TvShowActor(name, character, image, url);
            actors.add(actor);
        }
    }
}
