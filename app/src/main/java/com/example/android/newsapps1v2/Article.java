package com.example.android.newsapps1v2;

public class Article {
    //variable for the Section Name(found in API) of the Article
    private String ArticleSectionName;
    //variable for the Title(found in API) of the Article
    private String ArticleTitle;
    //variable for the Publication Date(found in API) of the Article
    private String ArticlePublicationDate;
    //variable for the Url(found in API) of the Article
    private String ArticleUrl;

    //required public constructor of the class
    public Article(String Section, String Title, String PublicationDate, String Url){
        ArticleSectionName = Section;
        ArticleTitle = Title;
        ArticlePublicationDate = PublicationDate;
        ArticleUrl = Url;
    }
    //get Section from constructor and return it's value(ArticleSectionName)
    public String getSection(){
        return ArticleSectionName;
    }
    //get Title from constructor and return it's value(ArticleTitle)
    public String getTitle(){
        return ArticleTitle;
    }
    //get PublicationDate from constructor and return it's value(ArticlePublicationDate)
    public String getPublicationDate(){
        return ArticlePublicationDate;
    }
    //get Url from constructor and return it's value(ArticleUrl)
    public String getUrl(){
        return ArticleUrl;
    }
}
