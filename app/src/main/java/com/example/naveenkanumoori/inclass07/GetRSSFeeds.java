package com.example.naveenkanumoori.inclass07;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Naveenkanumoori on 10/3/16.
 */
public class GetRSSFeeds extends AsyncTask<String, Void, ArrayList<Feed>>{
    ArrayList<Feed> listOfFeeds;
    MainActivity activity;

    public GetRSSFeeds(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Feed> doInBackground(String... strings) {
        try{
            URL url= new URL(strings[0]);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line= "";
                StringBuilder sb= new StringBuilder();
                while ((line= reader.readLine())!= null)
                {
                    sb.append(line);
                }

                listOfFeeds = JSONFeedsParser.parseFeeds(sb.toString());

                return listOfFeeds;
            }else {
                return null;
            }

        }
        catch (Exception e){

        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Feed> feeds) {
        super.onPostExecute(feeds);
        activity.setFeeds(feeds);
    }
    interface LoadData
    {
        public void setFeeds(ArrayList<Feed> feeds);
    }
}
