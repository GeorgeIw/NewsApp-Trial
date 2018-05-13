package com.example.android.newsapps1v2;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){
    }

    private static URL createUrl(String aUrl){
        URL url = null;
        try {
            url = new URL(aUrl);
        } catch(MalformedURLException e){
            Log.e(LOG_TAG,"Problem building the Url",e);
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG,"Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e){
            Log.e(LOG_TAG,"Problem retrieving the Article JSON results.",e);
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static List<Article> extractFeatureFromJson(String jsonItems){
        if(TextUtils.isEmpty(jsonItems)){
            return null;
        }
        List<Article> articles = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(jsonItems);
            JSONObject jsonObjectResponse = jsonObject.getJSONObject("response");
            JSONArray articlesArray = jsonObjectResponse.getJSONArray("results");

            for(int i = 0;i < articlesArray.length();i++){
                JSONObject jsonResponse = articlesArray.getJSONObject(i);
                String section = jsonResponse.optString("sectionName");
                String title = jsonResponse.optString("webTitle");
                String date = jsonResponse.optString("webPublicationDate");
                String url = jsonResponse.optString("webUrl");

                Article article = new Article(section,title,date,url);
                articles.add(article);
            }
        } catch(JSONException e){
            Log.e(LOG_TAG,"Problem parsing the articles from JSON",e);
        }
        return articles;
    }

    public static List<Article> fetchArticleData(String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch(IOException e){
            Log.e(LOG_TAG,"Problem making the HTTP request.",e);
        }
        List<Article> articles = extractFeatureFromJson(jsonResponse);
        return articles;
    }

}
