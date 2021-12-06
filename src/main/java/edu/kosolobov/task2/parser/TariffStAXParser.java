package edu.kosolobov.task2.parser;

import edu.kosolobov.task2.builder.TariffBuilder;
import edu.kosolobov.task2.entity.Tariff;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TariffStAXParser {

    public List<Tariff> parse(File file) throws FileNotFoundException, XMLStreamException {
        List<Tariff> tariffs = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newFactory();

        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "false");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "true");

        XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                TariffBuilder builder = TariffBuilder.getInstance();
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();

                switch (tagName) {
                    case "tariff" -> {
                        builder = TariffBuilder.newInstance();
                        builder.startDate(startElement.getAttributeByName(new QName("start")).getValue());
                        builder.endDate(startElement.getAttributeByName(new QName("end")).getValue());
                        builder.id(startElement.getAttributeByName(new QName("id")).getValue());
                    }
                    case "tariff-name" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.tariffName(value);
                        break;
                    }
                    case "internet-1mb-price" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.payroll(Float.parseFloat(value));
                        break;
                    }
                    case "inside-call-price" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.insideCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case "outside-call-price" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.outsideCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case "favorite-call-price" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.favoriteCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case "home-call-price" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.homeCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case "sms-price" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.smsPrice(Float.parseFloat(value));
                        break;
                    }
                    case "roaming-call-price" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.roamingCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case "roaming-sms-price" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.roamingSmsPrice(Float.parseFloat(value));
                        break;
                    }
                    case "tarification_minutes" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.tariffication(Float.parseFloat(value));
                        break;
                    }
                    case "favorite_number" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.favoriteNumber(value);
                        break;
                    }
                    case "operator-name" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.operatorName(value);
                        break;
                    }
                    case "country" -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.country(value);
                        break;
                    }
                }
            }
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("tariff")) {
                    TariffBuilder builder = TariffBuilder.getInstance();
                    tariffs.add(builder.build());
                }
            }
        }

        return tariffs;
    }

}
