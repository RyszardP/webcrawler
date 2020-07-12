package com.example.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.example.model.Crawler;
import com.example.model.UrlFilter;
import com.example.service.CrawlerServiceImpl;
import com.example.service.FileCrawlerServiceImpl;
import com.example.util.InputChecker;
import com.example.util.StringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    private ViewController viewController;

    private List<Crawler> statistics;

    private List<String> terms;

    private String url;

    private int linkDepth;

    private int maximumVisitedPages;

    public ViewController getViewController() {
        return viewController;
    }

    public List<String> getTerms() {
        return terms;
    }

    public String getUrl() {
        return url;
    }

    public int getLinkDepth() {
        return linkDepth;
    }

    public int getMaximumVisitedPages() {
        return maximumVisitedPages;
    }

    public Controller() {
        this.viewController = new ViewController();
        this.terms = new ArrayList<>();
        this.statistics = new ArrayList<>();
    }


    public void startApplication() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        CrawlerServiceImpl crawlerService;
        List<Crawler> sortedStatistics;
        FileCrawlerServiceImpl fileCrawlerService = new FileCrawlerServiceImpl();
        final int defaultLinkDepth = 8;
        final int topLimit = 10;
        final int defaultMaximumVisitedPages = 10000;
        do {
            viewController.showSentence(StringHelper.INPUT_URL);
            this.url = viewController.enterString();
        } while (!InputChecker.isCorrectUrl(this.url));
        do {
            viewController.showSentence(StringHelper.INPUT_TERM);
            this.terms.add(viewController.enterString());
        } while (viewController.isWantToContinue(StringHelper.ADDING_NEW_TERM_LOOP_SENTENCE));
        if (viewController.isWantToContinue(StringHelper.INPUT_INTEGERS_REQUEST)) {
            do {
                viewController.showSentence(StringHelper.INPUT_LINK_DEPTH);
                this.linkDepth = viewController.enterNumber();
                viewController.showSentence(StringHelper.INPUT_MAXIMUM_VISITED_PAGES);
                this.maximumVisitedPages = viewController.enterNumber();
            } while (!InputChecker.isCorrectPositiveNumber(this.linkDepth) &&
                    !InputChecker.isCorrectPositiveNumber((this.maximumVisitedPages)));
        }
        if (getLinkDepth() != 0) {
            Crawler crawler = new Crawler(getUrl(), getTerms(), new UrlFilter(getLinkDepth(), getMaximumVisitedPages()));
            crawlerService = new CrawlerServiceImpl(crawler);
        } else {
            Crawler crawler = new Crawler(getUrl(), getTerms(), new UrlFilter(defaultLinkDepth, defaultMaximumVisitedPages));
            crawlerService = new CrawlerServiceImpl(crawler);
        }
        statistics = crawlerService.crawlPages();
        fileCrawlerService.writeStatisticsToCSVFile(statistics, "statistics");
        sortedStatistics = crawlerService.getRequestedTopCrawlerStatistics(statistics, topLimit);
        fileCrawlerService.writeStatisticsToCSVFile(sortedStatistics, "Top" + topLimit + "Statistics");
        viewController.showStatistics(sortedStatistics);
    }
}
