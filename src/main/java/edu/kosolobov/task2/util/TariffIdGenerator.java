package edu.kosolobov.task2.util;

public class TariffIdGenerator {
    private static int id = 0;

    private TariffIdGenerator() {
    }

    public static String generateId() {
        return "tar".concat(String.valueOf(id++));
    }
}
