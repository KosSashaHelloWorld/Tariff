package edu.kosolobov.task2.builder;

import edu.kosolobov.task2.entity.Operator;
import edu.kosolobov.task2.entity.Parameters;
import edu.kosolobov.task2.entity.Tariff;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffBuilder {
    private static final Logger log = LogManager.getLogger(TariffBuilder.class);
    private String name;
    private Operator operator;
    private double payroll;
    private double callPrice;
    private double homeCallPrice;
    private double outdoorCallPrice;
    private double smsPrice;
    private Parameters parameters;

    public TariffBuilder() {
        log.log(Level.INFO, "TariffBuilder initialized");
    }

    public TariffBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TariffBuilder operator(Operator operator) {
        this.operator = operator;
        return this;
    }

    public TariffBuilder payroll(double payroll) {
        this.payroll = payroll;
        return this;
    }

    public TariffBuilder callPrice(double callPrice) {
        this.callPrice = callPrice;
        return this;
    }

    public TariffBuilder homeCallPrice(double homeCallPrice) {
        this.homeCallPrice = homeCallPrice;
        return this;
    }

    public TariffBuilder roamingCallPrice(double outdoorCallPrice) {
        this.outdoorCallPrice = outdoorCallPrice;
        return this;
    }

    public TariffBuilder smsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
        return this;
    }

    public TariffBuilder parameters(Parameters parameters) {
        this.parameters = parameters;
        return this;
    }

    public Tariff build() {
        return new Tariff(name,
                          operator,
                          payroll,
                          callPrice,
                          homeCallPrice,
                          outdoorCallPrice,
                          smsPrice,
                          parameters);
    }
}
