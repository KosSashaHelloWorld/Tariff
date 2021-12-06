package edu.kosolobov.task2.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;

public class Tariff {
    private static final Logger log = LogManager.getLogger(Tariff.class);
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String id;
    private final String name;
    private final double payroll;
    private final double internet1MBPrice;
    private final double insideCallPrice;
    private final double outsideCallPrice;
    private final double favoriteCallPrice;
    private final double homeCallPrice;
    private final double smsPrice;
    private final double roamingCallPrice;
    private final double roamingSmsPrice;
    private final Operator operator;
    private final Parameters parameters;

    public Tariff(LocalDateTime startDate,
                  LocalDateTime endDate,
                  String id,
                  String name,
                  double payroll,
                  double internet1MBPrice,
                  double insideCallPrice,
                  double outsideCallPrice,
                  double favoriteCallPrice,
                  double homeCallPrice,
                  double smsPrice,
                  double roamingCallPrice,
                  double roamingSmsPrice,
                  Operator operator,
                  Parameters parameters) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
        this.name = name;
        this.payroll = payroll;
        this.internet1MBPrice = internet1MBPrice;
        this.insideCallPrice = insideCallPrice;
        this.outsideCallPrice = outsideCallPrice;
        this.favoriteCallPrice = favoriteCallPrice;
        this.homeCallPrice = homeCallPrice;
        this.smsPrice = smsPrice;
        this.roamingCallPrice = roamingCallPrice;
        this.roamingSmsPrice = roamingSmsPrice;
        this.operator = operator;
        this.parameters = parameters;
        log.log(Level.INFO, "{} was created", this);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPayroll() {
        return payroll;
    }

    public double getInternet1MBPrice() {
        return internet1MBPrice;
    }

    public double getInsideCallPrice() {
        return insideCallPrice;
    }

    public double getOutsideCallPrice() {
        return outsideCallPrice;
    }

    public double getFavoriteCallPrice() {
        return favoriteCallPrice;
    }

    public double getHomeCallPrice() {
        return homeCallPrice;
    }

    public double getSmsPrice() {
        return smsPrice;
    }

    public double getRoamingCallPrice() {
        return roamingCallPrice;
    }

    public double getRoamingSmsPrice() {
        return roamingSmsPrice;
    }

    public Operator getOperator() {
        return operator;
    }

    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public int hashCode() {
        double prime = 4.2;
        double result = 1;
        result = result * prime + payroll;
        result = result * prime + insideCallPrice;
        result = result * prime + insideCallPrice;
        result = result * prime + outsideCallPrice;
        result = result * prime + favoriteCallPrice;
        result = result * prime + insideCallPrice;
        result = result * prime + homeCallPrice;
        result = result * prime + smsPrice;
        result = result * prime + roamingCallPrice;
        result = result * prime + roamingSmsPrice;
        result += parameters.hashCode();
        result += operator.hashCode();
        return (int) result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Double.compare(tariff.payroll, payroll) == 0 &&
                Double.compare(tariff.internet1MBPrice, internet1MBPrice) == 0 &&
                Double.compare(tariff.insideCallPrice, insideCallPrice) == 0 &&
                Double.compare(tariff.outsideCallPrice, outsideCallPrice) == 0 &&
                Double.compare(tariff.favoriteCallPrice, favoriteCallPrice) == 0 &&
                Double.compare(tariff.homeCallPrice, homeCallPrice) == 0 &&
                Double.compare(tariff.smsPrice, smsPrice) == 0 &&
                Double.compare(tariff.roamingCallPrice, roamingCallPrice) == 0 &&
                Double.compare(tariff.roamingSmsPrice, roamingSmsPrice) == 0 &&
                //         id.equals(tariff.id) &&
                //         name.equals(tariff.name) &&
                operator.equals(tariff.operator) &&
                parameters.equals(tariff.parameters);
    }

    @Override
    public String toString() {
        return String.format("Tariff{id:%s, name:%s, operator:%s}", id, name, operator);
    }
}
