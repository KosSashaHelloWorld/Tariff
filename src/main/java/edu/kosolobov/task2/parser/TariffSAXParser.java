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

import static edu.kosolobov.task2.parser.TariffConstants.*;

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
            if (current.equals(TARIFF_ELEMENT)) {
                TariffBuilder builder = TariffBuilder.newInstance();
                builder.id(attributes.getValue(ID_ATTRIBUTE));
                builder.startDate(attributes.getValue(START_ATTRIBUTE));
                builder.endDate(attributes.getValue(END_ATTRIBUTE));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals(TARIFF_ELEMENT)) {
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
                    case TARIFF_NAME -> builder.tariffName(value);
                    case PAYROLL -> builder.payroll(Float.parseFloat(value));
                    case INTERNET -> builder.internet1MBPrice(Float.parseFloat(value));
                    case INSIDE_CALL -> builder.insideCallPrice(Float.parseFloat(value));
                    case OUTSIDE_CALL -> builder.outsideCallPrice(Float.parseFloat(value));
                    case FAVORITE_CALL -> builder.favoriteCallPrice(Float.parseFloat(value));
                    case HOME_CALL -> builder.homeCallPrice(Float.parseFloat(value));
                    case SMS -> builder.smsPrice(Float.parseFloat(value));
                    case ROAMING_CALL -> builder.roamingCallPrice(Float.parseFloat(value));
                    case ROAMING_SMS -> builder.roamingSmsPrice(Float.parseFloat(value));
                    case TARIFICATION -> builder.tariffication(Float.parseFloat(value));
                    case FAVORITE_NUMBER -> builder.favoriteNumber(value);
                    case OPERATOR_NAME -> builder.operatorName(value);
                    case COUNTRY -> builder.country(value);
                }
            }
        }

        public List<Tariff> getResult() {
            return tariffs;
        }
    }

}
