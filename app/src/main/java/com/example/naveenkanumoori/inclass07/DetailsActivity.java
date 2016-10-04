package com.example.naveenkanumoori.inclass07;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

public class DetailsActivity extends AppCompatActivity {
    Feed feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        feed = (Feed)getIntent().getSerializableExtra("FEEDS");
        ImageView imageView = (ImageView)findViewById(R.id.detailsImage);

        TextView title = (TextView)findViewById(R.id.detailsTitle);
        TextView date = (TextView)findViewById(R.id.detailsDate);

        TextView summary = (TextView)findViewById(R.id.detailsSummary);

        title.setText(feed.getTitle());
        Picasso.with(this).load(feed.getSmallImage()).into(imageView);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        String formattedDate = dateFormat.format(feed.getDate());

        date.setText(formattedDate);

        summary.setText(feed.getDesctription());
    }
}
