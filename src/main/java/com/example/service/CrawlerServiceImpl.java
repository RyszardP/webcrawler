package com.example.service;

import com.example.model.Crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

public class CrawlerServiceImpl implements ICrawlerService {

    private LinkedList<String> pagesForCrawling;

    private Crawler defaultPageCrawler;

    private int visitedPages;

    private List<Crawler> crawlingStatistics;

    public CrawlerServiceImpl(Crawler crawler) {
        pagesForCrawling = new LinkedList<>();
        crawlingStatistics = new ArrayList<>();
        this.defaultPageCrawler = crawler;
        pagesForCrawling.add(defaultPageCrawler.getScanningUrl());
    }

    @Override
    public void extractUrlsFromDocument(Document document) {
        final byte firstTwoSlashes = 2;
        Elements elements = document.select("a");
        for (Element element : elements) {
            String linkUrl = element.attr("href");
            if (linkUrl.contains("http") && !pagesForCrawling.contains(linkUrl)) {
                if ((linkUrl.split("/").length - firstTwoSlashes - 1)
                        < defaultPageCrawler.getUrlFilter().getLinkDepth()) {
                    pagesForCrawling.addLast(linkUrl);
                }
            }
        }
    }


    @Override
    public String extractTextFromDocument(Document document) {
        return document.body().text();
    }


    @Override
    public List<String> getWordsFromText(String text) {
        return Arrays.stream(text.split("[^a-zA-Zа-яА-Я+]+")).collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> getFrequencyOfTermsInTheText(List<String> words) {
        return words.stream()
                .filter(word -> defaultPageCrawler.getTerms().contains(word))
                .collect(Collectors.groupingBy(word -> word, Collectors.summingInt(p -> 1)));
    }

    @Override
    public void crawlRequestedPage(String pageUrl) throws IOException {
        Crawler crawler = new Crawler(pageUrl, defaultPageCrawler.getTerms(), defaultPageCrawler.getUrlFilter());
        URL url = new URL(crawler.getScanningUrl());
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        Document document = Jsoup.parse(inputStream, "UTF-8", "");
        extractUrlsFromDocument(document);
        crawler.setTermsFrequency(getFrequencyOfTermsInTheText(getWordsFromText(extractTextFromDocument(document))));
        crawler.setTotalFrequencyOfTerms(calculateTotalFrequencyOfTerms(crawler));
        crawlingStatistics.add(crawler);
    }

    @Override
    public int calculateTotalFrequencyOfTerms(Crawler crawler) {
        return crawler.getTermsFrequency().values().stream().mapToInt(i -> i).sum();
    }


    @Override
    public List<Crawler> getRequestedTopCrawlerStatistics(List<Crawler> statistics, int topLimit) {
        Collections.sort(statistics);
        return statistics.stream().limit(topLimit).collect(Collectors.toList());
    }

    @Override
    public List<Crawler> crawlPages() throws IOException {
        do {
            String crawlingUrl = pagesForCrawling.getFirst();
            crawlRequestedPage(crawlingUrl);
            pagesForCrawling.removeFirst();
            visitedPages++;
        } while ((pagesForCrawling.size() != 0) &&
                (visitedPages < defaultPageCrawler.getUrlFilter().getMaximumVisitedPages()));
        return this.crawlingStatistics;
    }
}
