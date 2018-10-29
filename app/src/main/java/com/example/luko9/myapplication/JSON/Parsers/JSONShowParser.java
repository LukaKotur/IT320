package com.example.luko9.myapplication.JSON.Parsers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.luko9.myapplication.DB.DBHelper;
import com.example.luko9.myapplication.Entity.TvShows;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Klasa zaduzena za parsiranje dobijenih informacija od API
 */
public class JSONShowParser extends AsyncTask<Void, Integer, Boolean> {
    private final Context context;
    private final String jsonData;
    private final DBHelper dbHelper;


    private ProgressDialog pd;

    public JSONShowParser(Context context, String jsonData) {
        this.context = context;
        this.jsonData = jsonData;
        dbHelper = new DBHelper(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(context);
        pd.setTitle("Parsing data");
        pd.setProgress(0);
        pd.setMessage("Parsing/Persisting files to the database, please wait for this one time process...");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
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
            Toast.makeText(context, "It was a success, thank you for waiting!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Unable to Parse, check your log output", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parse() {
        String lines[] = jsonData.split("\\r?\\n");
        int counter = 0;
        try {
            for (String line : lines) {
                JSONArray jsonArray = new JSONArray(line);
                createAndInsertShowIntoDB(jsonArray);
                counter += 10;
                publishProgress(counter);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createAndInsertShowIntoDB(JSONArray jsonArray) throws JSONException {
        TvShows tvShow;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);

            JSONObject jScheduleObj = jObj.getJSONObject("schedule");
            JSONObject jRatingObj = jObj.getJSONObject("rating");

            String weight = jObj.getString("weight");

            if (Integer.parseInt(weight) > 90) {
                int id = Integer.parseInt(jObj.getString("id"));

                String days = jScheduleObj.getString("days");
                days = days.replace("[", "").replaceAll("]", "").replaceAll("\"", "");
                days = "Schedule: " + jScheduleObj.getString("time") + " on " + days;
                String genres = jObj.getString("genres");

                genres = genres.replace("[", "").replaceAll("]", "").replaceAll("\"", "");
                genres = "Genres: " + genres;

                String runtime = jObj.getString("runtime");
                runtime = "Runtime: " + runtime + "minutes";

                String status = jObj.getString("status");
                String name = jObj.getString("name");
                String url = jObj.getString("summary");

                String image;
                String bigImage = "";
                if (jObj.isNull("image")) {
                    image = "https://www.nextpointbearing.com/wp-content/themes/DiviFX/images/placeholder.jpg";
                } else {
                    JSONObject jImgObj = jObj.getJSONObject("image");
                    image = jImgObj.getString("medium");
                    bigImage = jImgObj.getString("original");
                }


                if (weight.equals("null")) {
                    weight = "0";
                }
                String rating = jRatingObj.getString("average");
                if (rating.equals("null")) {
                    rating = "0.0";
                }

                url = android.text.Html.fromHtml(url).toString();
                String isFavourite = "false";

                status = "Status: " + status;
                tvShow = new TvShows(id, name, url, image, bigImage, days, status, genres, runtime, weight, isFavourite, rating);

                dbHelper.insertShow(tvShow);
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        pd.setProgress(values[0]);
    }
}
