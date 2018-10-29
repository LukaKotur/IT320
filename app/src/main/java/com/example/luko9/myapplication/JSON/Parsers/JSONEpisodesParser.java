package com.example.luko9.myapplication.JSON.Parsers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.luko9.myapplication.CustomAdapters.CustomEpisodesListAdapter;
import com.example.luko9.myapplication.Entity.TvShowEpisodes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Klasa zaduzena za parsiranje dobijenih informacija od API
 */
public class JSONEpisodesParser extends AsyncTask<Void, Void, Boolean> {
    private final Context context;
    private final String jsonData;
    public static String path;

    private final ExpandableListView listView;
    List<String> listDataHeader;
    HashMap<String, ArrayList<TvShowEpisodes>> listDataChild;
    ProgressDialog pd;
    private final ArrayList<TvShowEpisodes> episodes = new ArrayList<>();

    public JSONEpisodesParser(Context context, String jsonData, ExpandableListView listView) {
        this.context = context;
        this.jsonData = jsonData;
        this.listView = listView;
//        this.listDataHeader = listDataHeader;
//        this.listDataChild = listDataChild;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);


        if (isParsed) {

            listView.setAdapter(new CustomEpisodesListAdapter(context, listDataHeader, listDataChild));

        } else {
            Toast.makeText(context, "Unable to Parse, check your log output", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parse() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);

            episodes.clear();

            //Create and add episodes to "episodes" ArrayList
            createAndAddEpisodes(jsonArray);

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createAndAddEpisodes(JSONArray jsonArray) throws JSONException {
        TvShowEpisodes episode;
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        String sNumberLast = "0";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);
            String image;


            String name = jObj.getString("name");


            if (jObj.isNull("image")) {
                image = "https://www.nextpointbearing.com/wp-content/themes/DiviFX/images/placeholder.jpg";
            } else {
                JSONObject imageObj = jObj.getJSONObject("image");
                image = imageObj.getString("original");
            }

            String sNumber = jObj.getString("season");
            if (Integer.parseInt(sNumberLast) <= Integer.parseInt(sNumber)) {
                if (!listDataHeader.contains("Season " + sNumber)) {
                    listDataHeader.add("Season " + sNumber);
                }
                sNumberLast = sNumber;
            }
            String eNumber = jObj.getString("number");

            String url = jObj.getString("url");
            String summary = jObj.getString("summary");
            summary = android.text.Html.fromHtml(summary).toString();

            episode = new TvShowEpisodes(name, eNumber, sNumber, summary, image, url);
            episodes.add(episode);
        }

        for (int i = 0; i < listDataHeader.size(); i++) {
            ArrayList<TvShowEpisodes> seasonEpisodes = new ArrayList<>();
            for (TvShowEpisodes ep : episodes) {
                if (Integer.parseInt(ep.getSeasonNumber()) == i+1 ) {
                    seasonEpisodes.add(ep);
                }
            }
            listDataChild.put(listDataHeader.get(i), seasonEpisodes);
        }
    }

}
