package com.example.service;

import com.example.model.Crawler;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;


public interface ICrawlerService extends IWebCrawler {

    void extractUrlsFromDocument(Document document);


    String extractTextFromDocument(Document document);

    List<String> getWordsFromText(String text);

    Map<String, Integer> getFrequencyOfTermsInTheText(List<String> words);

    int calculateTotalFrequencyOfTerms(Crawler crawler);

    List<Crawler> getRequestedTopCrawlerStatistics(List<Crawler> statistics, int topLimit);
}
