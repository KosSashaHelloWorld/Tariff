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

import static edu.kosolobov.task2.parser.TariffConstants.*;

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
                    case TARIFF_ELEMENT -> {
                        builder = TariffBuilder.newInstance();
                        builder.startDate(startElement.getAttributeByName(new QName("start")).getValue());
                        builder.endDate(startElement.getAttributeByName(new QName("end")).getValue());
                        builder.id(startElement.getAttributeByName(new QName("id")).getValue());
                    }
                    case TARIFF_NAME -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.tariffName(value);
                        break;
                    }
                    case PAYROLL -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.payroll(Float.parseFloat(value));
                        break;
                    }
                    case INSIDE_CALL -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.insideCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case OUTSIDE_CALL -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.outsideCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case FAVORITE_CALL -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.favoriteCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case HOME_CALL -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.homeCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case SMS -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.smsPrice(Float.parseFloat(value));
                        break;
                    }
                    case ROAMING_CALL -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.roamingCallPrice(Float.parseFloat(value));
                        break;
                    }
                    case ROAMING_SMS -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.roamingSmsPrice(Float.parseFloat(value));
                        break;
                    }
                    case TARIFICATION -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.tariffication(Float.parseFloat(value));
                        break;
                    }
                    case FAVORITE_NUMBER -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.favoriteNumber(value);
                        break;
                    }
                    case OPERATOR_NAME -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.operatorName(value);
                        break;
                    }
                    case COUNTRY -> {
                        event = reader.nextEvent();
                        String value = event.asCharacters().getData();
                        builder.country(value);
                        break;
                    }
                }
            }
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals(TARIFF_ELEMENT)) {
                    TariffBuilder builder = TariffBuilder.getInstance();
                    tariffs.add(builder.build());
                }
            }
        }

        return tariffs;
    }

}
