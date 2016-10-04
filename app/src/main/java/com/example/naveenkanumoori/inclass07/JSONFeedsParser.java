package com.example.naveenkanumoori.inclass07;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Naveenkanumoori on 10/3/16.
 */
public class JSONFeedsParser {
    static ArrayList<Feed> parseFeeds(String jsonString){
        ArrayList<Feed> feeds = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONObject feedsObject = root.getJSONObject("feed");
            JSONArray feedItems = feedsObject.getJSONArray("entry");
            System.out.print(feedItems.length());

            for (int i=0; i<feedItems.length(); i++){
                JSONObject feedObject = feedItems.getJSONObject(i);

                JSONObject labelObject = feedObject.getJSONObject("title");
                String title = labelObject.getString("label");

                JSONArray imageList = feedObject.getJSONArray("im:image");

                String smallImage="";
                String largeImage="";
                int max = 0,min = Integer.MAX_VALUE;
                for (int j=0;j<imageList.length();j++){
                    JSONObject imageObject = imageList.getJSONObject(j);
                    String height = imageObject.getJSONObject("attributes").getString("height");
                    int imgHeight = Integer.parseInt(height);
                    if (min>imgHeight){
                        smallImage = imageObject.getString("label");
                    }
                    if (max<imgHeight){
                        largeImage = imageObject.getString("label");
                    }
                }

                String description = feedObject.getJSONObject("summary").getString("label");
                String dateString = feedObject.getJSONObject("im:releaseDate").getString("label");

                Date releaseDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(dateString);

                Feed feed = new Feed(title,smallImage,largeImage,description,releaseDate);

                feeds.add(feed);
            }
        }catch (Exception e){
            Log.d("Exception",e.getLocalizedMessage());
        }

        return feeds;
    }
}
