package edu.kosolobov.task2.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tariff {
    private static final Logger log = LogManager.getLogger(Tariff.class);
    private final String name;
    private final Operator operator;
    private final double payroll;
    private final double callPrice;
    private final double homeCallPrice;
    private final double outdoorCallPrice;
    private final double smsPrice;
    private final Parameters parameters;

    public Tariff(String name,
                  Operator operator,
                  double payroll,
                  double callPrice,
                  double homeCallPrice,
                  double outdoorCallPrice,
                  double smsPrice,
                  Parameters parameters) {
        this.name = name;
        this.operator = operator;
        this.payroll = payroll;
        this.callPrice = callPrice;
        this.homeCallPrice = homeCallPrice;
        this.outdoorCallPrice = outdoorCallPrice;
        this.smsPrice = smsPrice;
        this.parameters = parameters;

        operator.addTariff(this);

        log.log(Level.INFO, "{} was created", this);
    }

    public String getName() {
        return name;
    }

    public Operator getOperator() {
        return operator;
    }

    public double getPayroll() {
        return payroll;
    }

    public double getCallPrice() {
        return callPrice;
    }

    public double getHomeCallPrice() {
        return homeCallPrice;
    }

    public double getOutdoorCallPrice() {
        return outdoorCallPrice;
    }

    public double getSmsPrice() {
        return smsPrice;
    }

    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public int hashCode() {
        double prime = 14.2;
        double result = 1;
        result = result * prime + payroll;
        result = result * prime + callPrice;
        result = result * prime + homeCallPrice;
        result = result * prime + outdoorCallPrice;
        result = result * prime + smsPrice;
        for (char c : name.toCharArray()) {
            result += c;
        }
        result += operator.hashCode();
        result += parameters.hashCode();
        return (int) result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Double.compare(tariff.payroll, payroll) == 0 &&
                Double.compare(tariff.callPrice, callPrice) == 0 &&
                Double.compare(tariff.homeCallPrice, homeCallPrice) == 0 &&
                Double.compare(tariff.outdoorCallPrice, outdoorCallPrice) == 0 &&
                Double.compare(tariff.smsPrice, smsPrice) == 0 &&
                name.equals(tariff.name) &&
                operator.equals(tariff.operator) &&
                parameters.equals(tariff.parameters);
    }

    @Override
    public String toString() {
        return String.format("Tariff{name:%s, operator:%s}", name, operator);
    }
}
