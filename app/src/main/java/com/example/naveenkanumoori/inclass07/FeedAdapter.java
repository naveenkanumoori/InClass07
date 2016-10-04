package com.example.naveenkanumoori.inclass07;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Naveenkanumoori on 10/3/16.
 */
public class FeedAdapter extends ArrayAdapter<Feed>{
    Context mContext;
    int mResource;
    List<Feed> mData;
    public FeedAdapter(Context context, int resource, List<Feed> feeds) {
        super(context, resource,feeds);
        this.mContext = context;
        this.mResource = resource;
        this.mData = feeds;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.feedImage);
        TextView textView = (TextView)convertView.findViewById(R.id.feedText);

        Feed feed = mData.get(position);
        if (MainActivity.searchFeeds.contains(feed)){
            convertView.setBackgroundColor(Color.GREEN);
        }else {
            convertView.setBackgroundColor(Color.WHITE);
        }
        Picasso.with(mContext).load(feed.getSmallImage()).into(imageView);

        textView.setText(feed.getTitle());

        return convertView;
    }
}
