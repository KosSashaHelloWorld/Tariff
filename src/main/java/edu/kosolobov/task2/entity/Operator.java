package edu.kosolobov.task2.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Operator {
    private static final Logger log = LogManager.getLogger(Operator.class);
    private final String name;
    private final String country;
    private final List<Tariff> tariffs = new ArrayList<>();

    public Operator(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void addTariff(Tariff tariff) {
        tariffs.add(tariff);
        log.log(Level.INFO, "{} added to {}", tariff, this);
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return Objects.equals(name, operator.name) && Objects.equals(country, operator.country) && Objects.equals(tariffs, operator.tariffs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, tariffs);
    }

    @Override
    public String toString() {
        return String.format("Operator{name:%s, country:%s}", name, country);
    }
}
