package edu.kosolobov.task2.builder;

import edu.kosolobov.task2.entity.Operator;
import edu.kosolobov.task2.entity.Parameters;
import edu.kosolobov.task2.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class TariffBuilder {
    private static final Logger log = LogManager.getLogger(TariffBuilder.class);
    private static TariffBuilder instance = new TariffBuilder();
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String id;
    private String tariffName;
    private float payroll;
    private float internet1MBPrice;
    private float insideCallPrice;
    private float outsideCallPrice;
    private float favoriteCallPrice;
    private float homeCallPrice;
    private float smsPrice;
    private float roamingCallPrice;
    private float roamingSmsPrice;
    private Operator operator = new Operator();
    private Parameters parameters = new Parameters();

    private TariffBuilder() {

    }

    public static TariffBuilder getInstance() {
        return instance;
    }

    public static TariffBuilder newInstance() {
        instance = new TariffBuilder();
        return instance;
    }

    public TariffBuilder startDate(String startDate) {
        this.startDate = LocalDateTime.parse(startDate.replaceAll("[+]\\d{2}[:]\\d{2}", ""));
        return this;
    }

    public TariffBuilder endDate(String endDate) {
        this.endDate = LocalDateTime.parse(endDate.replaceAll("[+]\\d{2}[:]\\d{2}", ""));
        return this;
    }

    public TariffBuilder id(String id) {
        this.id = id;
        return this;
    }

    public TariffBuilder tariffName(String name) {
        this.tariffName = name;
        return this;
    }

    public TariffBuilder payroll(float payroll) {
        this.payroll = payroll;
        return this;
    }

    public TariffBuilder internet1MBPrice(float internet1MBPrice) {
        this.internet1MBPrice = internet1MBPrice;
        return this;
    }

    public TariffBuilder insideCallPrice(float insideCallPrice) {
        this.insideCallPrice = insideCallPrice;
        return this;
    }

    public TariffBuilder outsideCallPrice(float outsideCallPrice) {
        this.outsideCallPrice = outsideCallPrice;
        return this;
    }

    public TariffBuilder favoriteCallPrice(float favoriteCallPrice) {
        this.favoriteCallPrice = favoriteCallPrice;
        return this;
    }

    public TariffBuilder homeCallPrice(float homeCallPrice) {
        this.homeCallPrice = homeCallPrice;
        return this;
    }

    public TariffBuilder smsPrice(float smsPrice) {
        this.smsPrice = smsPrice;
        return this;
    }

    public TariffBuilder roamingCallPrice(float roamingCallPrice) {
        this.roamingCallPrice = roamingCallPrice;
        return this;
    }

    public TariffBuilder roamingSmsPrice(float roamingSmsPrice) {
        this.roamingSmsPrice = roamingSmsPrice;
        return this;
    }

    public TariffBuilder tariffication(float tariffication) {
        parameters.setTarification(tariffication);
        return this;
    }

    public TariffBuilder favoriteNumber(String number) {
        parameters.addFavoriteNumber(number);
        return this;
    }

    public TariffBuilder operatorName(String name) {
        operator.setName(name);
        return this;
    }

    public TariffBuilder country(String country) {
        operator.setCountry(country);
        return this;
    }

    public Tariff build() {
        return new Tariff(
                 startDate,
                 endDate,
                 id,
                 tariffName,
                 payroll,
                 internet1MBPrice,
                 insideCallPrice,
                 outsideCallPrice,
                 favoriteCallPrice,
                 homeCallPrice,
                 smsPrice,
                 roamingCallPrice,
                 roamingSmsPrice,
                 operator,
                 parameters);
    }
}
