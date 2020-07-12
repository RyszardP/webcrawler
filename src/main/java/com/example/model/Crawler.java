package com.example.model;


import com.example.util.StringHelper;

import java.util.List;
import java.util.Map;


public class Crawler implements Comparable<Crawler> {

    private String scanningUrl;

    private List<String> terms;

    private Map<String, Integer> termsFrequency;

    private UrlFilter urlFilter;

    private int totalFrequencyOfTerms;


    public Crawler(String scanningUrl, List<String> terms, UrlFilter urlFilter) {
        this.scanningUrl = scanningUrl;
        this.terms = terms;
        this.urlFilter = urlFilter;
    }


    public String getScanningUrl() {
        return scanningUrl;
    }

    public void setScanningUrl(String scanningUrl) {
        this.scanningUrl = scanningUrl;
    }

    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public Map<String, Integer> getTermsFrequency() {
        return termsFrequency;
    }

    public void setTermsFrequency(Map<String, Integer> termsFrequency) {
        this.termsFrequency = termsFrequency;
    }

    public UrlFilter getUrlFilter() {
        return urlFilter;
    }

    public void setUrlFilter(UrlFilter urlFilter) {
        this.urlFilter = urlFilter;
    }


    public int getTotalFrequencyOfTerms() {
        return totalFrequencyOfTerms;
    }

    public void setTotalFrequencyOfTerms(int totalFrequencyOfTerms) {
        this.totalFrequencyOfTerms = totalFrequencyOfTerms;
    }

    @Override
    public int compareTo(Crawler o) {
        return o.getTotalFrequencyOfTerms() - this.getTotalFrequencyOfTerms();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getScanningUrl()).append(StringHelper.SPACE);
        this.getTermsFrequency().values().forEach(value -> stringBuilder.append(value).append(StringHelper.SPACE));
        stringBuilder.append(getTotalFrequencyOfTerms());
        return stringBuilder.toString();
    }
}
