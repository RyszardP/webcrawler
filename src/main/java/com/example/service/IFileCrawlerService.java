package com.example.service;


import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.example.model.Crawler;

import java.io.IOException;
import java.util.List;


public interface IFileCrawlerService {

    void writeStatisticsToCSVFile(List<Crawler> crawlers, String fileName) throws CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException, IOException;
}
