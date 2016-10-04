//In Class 07
//Naveen Kumar Kanumoori

package com.example.naveenkanumoori.inclass07;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements GetRSSFeeds.LoadData{

    private ProgressDialog progressDialog;
    ArrayList<Feed> listOfFeeds;
    ListView feedsListView;
    public static ArrayList<Feed> searchFeeds;
    ArrayList<Feed> noSearchFeeds;
    ArrayList<Feed> newList;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedsListView = (ListView)findViewById(R.id.listViewFeeds);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.loading));
        progressDialog.show();

        searchFeeds = new ArrayList<>();
        noSearchFeeds = new ArrayList<>();
        newList = new ArrayList<>();

        new GetRSSFeeds(this).execute("https://itunes.apple.com/us/rss/toppodcasts/limit=200/json");

        Button go = (Button)findViewById(R.id.buttonGO);
        Button clear = (Button)findViewById(R.id.buttonClear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView)findViewById(R.id.searchText)).setText("");
                searchFeeds = new ArrayList<>();
                noSearchFeeds = new ArrayList<>();
                newList = new ArrayList<>();
                Collections.sort(listOfFeeds,new MyFeedsSort());
                setFeeds(listOfFeeds);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = ((TextView)findViewById(R.id.searchText)).getText().toString();
                searchFeeds = new ArrayList<>();
                noSearchFeeds = new ArrayList<>();
                newList = new ArrayList<>();
                Log.d("Text",searchText);
                if (searchText==null || searchText.length()==0){
                    Toast.makeText(MainActivity.this, "Please Enter Some Text", Toast.LENGTH_LONG).show();
                }else {
                    if (listOfFeeds!=null) {
                        for (Feed feed : listOfFeeds) {
                            String title = feed.getTitle();
                            if (title.toLowerCase().contains(searchText.toLowerCase())) {
                                searchFeeds.add(feed);
                            }else {
                                noSearchFeeds.add(feed);
                            }
                        }

                        for (Feed feed : searchFeeds) {
                            newList.add(feed);
                        }for (Feed feed : noSearchFeeds) {
                            newList.add(feed);
                        }
                        if (searchFeeds.size()==0){
                            Toast.makeText(MainActivity.this, "No Feeds Found", Toast.LENGTH_LONG).show();
                        }
                        setFeeds(newList);
                    }
                }
            }
        });
    }

    @Override
    public void setFeeds(final ArrayList<Feed> feeds) {

        if (flag==0){
            progressDialog.dismiss();
            Collections.sort(feeds,new MyFeedsSort());
            listOfFeeds = feeds;
            flag = 1;
        }

        FeedAdapter feedAdapter = new FeedAdapter(this,R.layout.list_item,feeds);

        feedsListView.setAdapter(feedAdapter);

        feedsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("FEEDS",feeds.get(i));
                startActivity(intent);
            }
        });
    }

    class MyFeedsSort implements Comparator<Feed> {

        @Override
        public int compare(Feed f1, Feed f2) {
            if(f1.getDate().before(f2.getDate())){
                return 1;
            } else {
                return -1;
            }
        }
    }
}
