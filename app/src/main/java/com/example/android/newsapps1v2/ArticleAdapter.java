package com.example.android.newsapps1v2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(@NonNull Context context, List<Article> articles) {
        super(context, 0,articles);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View articleView = convertView;
        if(articleView == null){
            articleView = LayoutInflater.from(getContext()).inflate(R.layout.activity_categories_holder, parent, false);
        }
        Article currentArticle = getItem(position);

        TextView sectionView = articleView.findViewById(R.id.section);
        sectionView.setText(currentArticle.getSection());

        TextView titleView = articleView.findViewById(R.id.title);
        titleView.setText(currentArticle.getTitle());

        TextView publicationDateView = articleView.findViewById(R.id.date);
        titleView.setText(currentArticle.getPublicationDate());

        return articleView;
    }
}
