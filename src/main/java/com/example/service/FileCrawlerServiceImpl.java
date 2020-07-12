package com.example.service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.example.model.Crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class FileCrawlerServiceImpl implements IFileCrawlerService {

    @Override
    public void writeStatisticsToCSVFile(List<Crawler> crawlers, String fileName) throws CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException, IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        ColumnPositionMappingStrategy<Crawler> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(Crawler.class);
        String[] columns = new String[]{"scanningUrl", "terms", "termsFrequency", "totalFrequencyOfTerms"};
        mappingStrategy.setColumnMapping(columns);
        StatefulBeanToCsvBuilder<Crawler> builder = new StatefulBeanToCsvBuilder<>(fileWriter);
        StatefulBeanToCsv<Crawler> beanWriter = builder.withMappingStrategy(mappingStrategy).build();
        beanWriter.write(crawlers);
        fileWriter.close();
    }
}
