package edu.kosolobov.task2.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Operator {
    private static final Logger log = LogManager.getLogger(Operator.class);
    private String name;
    private String country;

    public Operator() {
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return name.equals(operator.name) && country.equals(operator.country);
    }

    @Override
    public int hashCode() {
        char[] prime = name.concat(country).toCharArray();
        int result = 0;
        for (char i : prime) {
            result += i;
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("Operator{name:%s, country:%s}", name, country);
    }
}
