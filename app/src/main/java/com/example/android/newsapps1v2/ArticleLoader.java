package com.example.android.newsapps1v2;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class ArticleLoader extends android.content.AsyncTaskLoader<List<Article>> {

    private static final String LOG_TAG = ArticleLoader.class.getName();

    private String ArticleUrl;

    public ArticleLoader(Context context, String url) {
        super(context);
        ArticleUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        if(ArticleUrl == null){
            return null;
        }

        List<Article> articles = QueryUtils.fetchArticleData(ArticleUrl);
        return articles;
    }
}
