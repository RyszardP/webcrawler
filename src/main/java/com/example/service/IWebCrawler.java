package com.example.service;



import com.example.model.Crawler;

import java.io.IOException;
import java.util.List;


public interface IWebCrawler {

    List<Crawler> crawlPages() throws IOException;


    void crawlRequestedPage(String pageUrl) throws IOException;
}
