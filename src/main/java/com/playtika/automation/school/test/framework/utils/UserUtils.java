package com.playtika.automation.school.test.framework.utils;

import java.util.Random;

public class UserUtils {

    private static final String SYMBOLS = "abcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();
    private static final Integer LENGTH = 9;

    public static String getRandomEmail(){
        StringBuilder sb = new StringBuilder(LENGTH);
        for(int i = 0; i < 9; i++)
            sb.append(SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length())));
        return sb.toString() + "@gmail.com";
    }

    public static String getRandomPassword(){
        StringBuilder sb = new StringBuilder(LENGTH);
        for(int i = 0; i < 9; i++)
            sb.append(SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length())));
        return sb.toString();
    }
}