package edu.kosolobov.task2.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TariffIdGeneratorTest {

    @Test
    void generateId() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            result.add(TariffIdGenerator.generateId());
        }
        System.out.println(result);
    }
}