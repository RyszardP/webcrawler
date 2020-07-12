package com.example.controller;

import com.example.model.Crawler;

import java.util.List;
import java.util.Scanner;


public class ViewController {

    private Scanner scanner;

    public ViewController() {
        scanner = new Scanner(System.in);
    }

    public void showSentence(String sentence) {
        System.out.println(sentence);
    }

    public String enterString() {
        return scanner.nextLine();
    }

    public int enterNumber() {
        return scanner.hasNextInt() ? scanner.nextInt() : 0;
    }

    public boolean isWantToContinue(String sentence) {
        String answer;
        showSentence(sentence);
        answer = enterString();
        return answer.equalsIgnoreCase("y");
    }

    public void showStatistics(List<Crawler> limitedSortedStatistics) {
        limitedSortedStatistics.forEach(System.out::println);
    }
}
