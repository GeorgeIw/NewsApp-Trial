package com.example.android.newsapps1v2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import java.util.List;

public class CategoriesHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_holder);

        ViewPager viewPager = findViewById(R.id.viewpager);
       ArticleCategoryAdapter adapter = new ArticleCategoryAdapter(this, getSupportFragmentManager());

       viewPager.setAdapter(adapter);

       TabLayout tabLayout = findViewById(R.id.tabs);
       tabLayout.setupWithViewPager(viewPager);

       if(getIntent().hasExtra("LatestNewsFragment")){
           viewPager.setCurrentItem(0);
       } else if(getIntent().hasExtra("LatestNewsFragment")){
           viewPager.setCurrentItem(1);
       } else if(getIntent().hasExtra("LatestNewsFragment")){
           viewPager.setCurrentItem(2);
       } else {
           viewPager.setCurrentItem(3);
       }
    }
}
