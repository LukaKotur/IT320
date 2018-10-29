package com.example.luko9.myapplication.JSON.Downloaders;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.luko9.myapplication.JSON.Connector;
import com.example.luko9.myapplication.JSON.Parsers.JSONShowParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Klasa zaduzena za ASYNC primanje informaicja za serije sa API
 */
public class JSONDownloader extends AsyncTask<Void, Void, String> {
    private final Context context;
    private final String jsonURL;
    private ProgressDialog pd;


    public JSONDownloader(Context context, String jsonURL) {
        this.context = context;
        this.jsonURL = jsonURL;
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
            new JSONShowParser(context, jsonData).execute();
        }
    }

    private String download() {
        StringBuffer jsonData = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            Object connection = Connector.connect(jsonURL + i);
            System.out.println(jsonURL);
            if (connection.toString().startsWith("Error"))

            {
                return connection.toString();
            }
            try {
                HttpURLConnection con = (HttpURLConnection) connection;
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = new BufferedInputStream(con.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String line;

                    while ((line = br.readLine()) != null) {
                        jsonData.append(line).append("\n");
                    }

                    br.close();
                    is.close();
                } else {
                    return "Error " + con.getResponseMessage();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "Error " + e.getMessage();
            }
        }
        System.out.println(jsonData.toString());
        return jsonData.toString();
    }
}
