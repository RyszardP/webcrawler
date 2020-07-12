package com.example.model;


public class UrlFilter {

    private int linkDepth;

    private int maximumVisitedPages;

    public UrlFilter(int linkDepth, int maximumVisitedPages) {
        this.linkDepth = linkDepth;
        this.maximumVisitedPages = maximumVisitedPages;
    }

    public int getLinkDepth() {
        return linkDepth;
    }

    public void setLinkDepth(int linkDepth) {
        this.linkDepth = linkDepth;
    }

    public int getMaximumVisitedPages() {
        return maximumVisitedPages;
    }

    public void setMaximumVisitedPages(int maximumVisitedPages) {
        this.maximumVisitedPages = maximumVisitedPages;
    }
}
