package com.example.android.newsapps1v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView latestNewsView = findViewById(R.id.latest_news_category);
        latestNewsView.setOnClickListener(this);

        TextView sportsView = findViewById(R.id.sports_category);
        sportsView.setOnClickListener(this);

        TextView politicsView = findViewById(R.id.politics_category);
        politicsView.setOnClickListener(this);

        TextView lifestyleView = findViewById(R.id.lifetyle_category);
        lifestyleView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.latest_news_category:
                Intent latestNewsIntent = new Intent(MainActivity.this,CategoriesHolderActivity.class);
                latestNewsIntent.putExtra("LatestNewsFragment",1);
                startActivity(latestNewsIntent);
                break;
            case R.id.sports_category:
               Intent sportsIntent = new Intent(MainActivity.this,CategoriesHolderActivity.class);
               sportsIntent.putExtra("LatestNewsFragment",2);
               startActivity(sportsIntent);
               break;
            case R.id.politics_category:
                Intent politicsIntent = new Intent(MainActivity.this,CategoriesHolderActivity.class);
                politicsIntent.putExtra("LatestNewsFragment",3);
                startActivity(politicsIntent);
                break;
            case R.id.lifetyle_category:
               Intent lifestyleIntent = new Intent(MainActivity.this,CategoriesHolderActivity.class);
               lifestyleIntent.putExtra("LatestNewsFragment",4);
               startActivity(lifestyleIntent);
                break;
        }
    }
}
