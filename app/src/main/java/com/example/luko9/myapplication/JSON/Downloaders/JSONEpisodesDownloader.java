package com.example.luko9.myapplication.JSON.Downloaders;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.luko9.myapplication.JSON.Connector;
import com.example.luko9.myapplication.JSON.Parsers.JSONEpisodesParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Klasa zaduzena za ASYNC primanje informaicja za epizode sa API
 */
public class JSONEpisodesDownloader extends AsyncTask<Void, Void, String> {

    private final Context context;
    private final String jsonURL;
    private ExpandableListView listView;
//    private List<String> listDataHeader;
//    private HashMap<String, ArrayList<TvShowEpisodes>> listDataChild;

    ProgressDialog pd;

    public JSONEpisodesDownloader(Context context, String jsonURL,
                                  ExpandableListView listView) {
        this.context = context;
        this.jsonURL = jsonURL;
        this.listView = listView;
//        this.listDataHeader = listDataHeader;
//        this.listDataChild = listDataChild;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(Void... params) {
        return download();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);


        if (jsonData.startsWith("Error")) {
            Toast.makeText(context, jsonData, Toast.LENGTH_SHORT).show();
        } else {
            new JSONEpisodesParser(context, jsonData, listView).execute();
        }
    }

    private String download() {
        Object connection = Connector.connect(jsonURL);
        if (connection.toString().startsWith("Error"))

        {
            return connection.toString();
        }
        try

        {
            HttpURLConnection con = (HttpURLConnection) connection;
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer jsonData = new StringBuffer();

                while ((line = br.readLine()) != null) {
                    jsonData.append(line).append("\n");
                }

                br.close();
                is.close();

                return jsonData.toString();
            } else {
                return "Error " + con.getResponseMessage();
            }
        } catch (
                IOException e)

        {
            e.printStackTrace();
            return "Error " + e.getMessage();
        }
    }

}
