package com.example.android.newsapps1v2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.LoaderManager.LoaderCallbacks;

import java.util.ArrayList;
import java.util.List;

public class LatestNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Article> {

    private TextView EmptyTextView;
    private ArticleAdapter adapter;
    private static final String LOG_TAG = LatestNewsFragment.class.getSimpleName();
    private static final String GUARDIAN_LATEST_NEWS_URL = "http://content.guardianapis.com/search?use-date=published&order-by=newest&page-size=20&api-key=test";
    private static final int LOADER_VALUE = 0;

    public LatestNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.single_article_layout, container, false);

        ListView articleListView = rootView.findViewById(R.id.list);

        EmptyTextView = rootView.findViewById(R.id.empty_view);
        articleListView.setEmptyView(EmptyTextView);

        adapter = new ArticleAdapter(getContext(), new ArrayList<Article>());
        articleListView.setAdapter(adapter);

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article currentArticle = adapter.getItem(position);
                Uri articleUri = Uri.parse(currentArticle.getUrl());
                Intent loadWebsiteIntent = new Intent(Intent.ACTION_VIEW,articleUri);
                startActivity(loadWebsiteIntent);
            }
        });

        ConnectivityManager connMngr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMngr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_VALUE, null, this);
        } else {
            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            EmptyTextView.setText(R.string.no_internet);
        }
        return rootView;
}

    @NonNull
    @Override
    public Loader<Article> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new Loader<>(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Article> loader, Article articles) {
        View loadingIndicator = getView().findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        EmptyTextView.setText(R.string.no_articles_found);

        if(articles != null){
            adapter.addAll(articles);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Article> loader) {
        adapter.clear();
    }
}
