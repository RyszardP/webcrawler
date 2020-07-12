package com.example;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.example.controller.Controller;

import java.io.IOException;


public class Main {


    public static void main(String[] args) {
        try {
            new Controller().startApplication();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
}
