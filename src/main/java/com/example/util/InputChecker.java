package com.example.util;


public class InputChecker {

    public static boolean isCorrectPositiveNumber(int userInputNumber) {
        return userInputNumber > 0;
    }


    public static boolean isCorrectUrl(String userInputUrl) {
        return userInputUrl != null && userInputUrl
                .matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }
}
