package com.anu3dev.backend.service;

import java.security.SecureRandom;

public class CommonUtils {

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomNumber(int otpLenth, String baseString) {
        StringBuilder otp = new StringBuilder(otpLenth);
        for (int i = 0; i < otpLenth; i++) {
            otp.append(baseString.charAt(random.nextInt(baseString.length())));
        }
        return otp.toString();
    }

    public static String generateRandomNumberInLimit(int startNumber, int endNumber) {
        final SecureRandom random = new SecureRandom();
        // Generates a number between 100000 and 999999
        int number = random.nextInt(endNumber) + startNumber;
        return String.valueOf(number);
    }
}
