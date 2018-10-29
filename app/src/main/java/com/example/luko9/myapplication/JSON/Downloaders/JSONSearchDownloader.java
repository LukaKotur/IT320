package com.example.luko9.myapplication.JSON.Downloaders;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luko9.myapplication.JSON.Connector;
import com.example.luko9.myapplication.JSON.Parsers.JSONSearchParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Klasa zaduzena za ASYNC primanje informaicja za pretrazene serije sa API
 */
public class JSONSearchDownloader extends AsyncTask<Void, Void, String> {
    private final Context context;
    private final String jsonURL;
    private final ListView lv;

    private ProgressDialog pd;


    public JSONSearchDownloader(Context context, String jsonURL, ListView lv) {
        this.context = context;
        this.jsonURL = jsonURL;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(context);
        pd.setTitle("Downloading items");
        pd.setMessage("Downloading...please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return download();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();

        if (jsonData.startsWith("Error")) {
            Toast.makeText(context, jsonData, Toast.LENGTH_SHORT).show();
        } else {
            new JSONSearchParser(context, jsonData, lv).execute();
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
