package edu.kosolobov.task2.parser;

import edu.kosolobov.task2.builder.TariffBuilder;
import edu.kosolobov.task2.entity.Tariff;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TariffSAXParser {

    public TariffSAXParser() {
    }

    public List<Tariff> parse(File file) throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        TariffHandler handler = new TariffHandler();
        parser.parse(file, handler);

        return handler.getResult();
    }

    private static class TariffHandler extends DefaultHandler {
        private List<Tariff> tariffs = new ArrayList<>();
        private String current;

        public TariffHandler() {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            current = qName;
            if (current.equals("tariff")) {
                TariffBuilder builder = TariffBuilder.newInstance();
                builder.id(attributes.getValue("id"));
                builder.startDate(attributes.getValue("start"));
                builder.endDate(attributes.getValue("end"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("tariff")) {
                TariffBuilder builder = TariffBuilder.getInstance();
                tariffs.add(builder.build());
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            TariffBuilder builder = TariffBuilder.getInstance();
            String value = new String(ch, start, length);
            value = value.replace("\n", "").trim();

            if (!value.isEmpty()) {
                switch (current) {
                    case "tariff-name" -> builder.tariffName(value);
                    case "payroll" -> builder.payroll(Float.parseFloat(value));
                    case "internet-1mb-price" -> builder.internet1MBPrice(Float.parseFloat(value));
                    case "inside-call-price" -> builder.insideCallPrice(Float.parseFloat(value));
                    case "outside-call-price" -> builder.outsideCallPrice(Float.parseFloat(value));
                    case "favorite-call-price" -> builder.favoriteCallPrice(Float.parseFloat(value));
                    case "home-call-price" -> builder.homeCallPrice(Float.parseFloat(value));
                    case "sms-price" -> builder.smsPrice(Float.parseFloat(value));
                    case "roaming-call-price" -> builder.roamingCallPrice(Float.parseFloat(value));
                    case "roaming-sms-price" -> builder.roamingSmsPrice(Float.parseFloat(value));
                    case "tarification_minutes" -> builder.tariffication(Float.parseFloat(value));
                    case "favorite_number" -> builder.favoriteNumber(value);
                    case "operator-name" -> builder.operatorName(value);
                    case "country" -> builder.country(value);
                }
            }
        }

        public List<Tariff> getResult() {
            return tariffs;
        }
    }

}
