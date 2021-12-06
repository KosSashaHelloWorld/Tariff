package edu.kosolobov.task2.parser;

import edu.kosolobov.task2.builder.TariffBuilder;
import edu.kosolobov.task2.entity.Tariff;
import edu.kosolobov.task2.validator.TariffFileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static edu.kosolobov.task2.parser.TariffConstants.*;

public class TariffDOMParser {
    private static final Logger log = LogManager.getLogger(TariffDOMParser.class);

    public List<Tariff> parse(File file) throws ParserConfigurationException, IOException, SAXException {
        List<Tariff> tariffs = new ArrayList<>();

        TariffFileValidator.isValid(file);

        Element root = getRootElementFromFactory(file);

        if (root == null) {
            log.log(Level.ERROR, "Root element == null");
            log.log(Level.WARN, "Empty List<Tariff> returned");
            return tariffs;
        }


        NodeList tariffNodes = root.getChildNodes();
        for (int i = 0; i < tariffNodes.getLength(); i++) {
            if (tariffNodes.item(i) instanceof Element element && element.getTagName().equals("tariff")) {
                TariffBuilder builder = TariffBuilder.newInstance();
                builder.id(element.getAttribute(ID_ATTRIBUTE));
                builder.startDate(element.getAttribute(START_ATTRIBUTE));
                builder.endDate(element.getAttribute(END_ATTRIBUTE));
                NodeList items = element.getChildNodes();
                for (int j = 0; j < items.getLength(); j++) {
                    if (items.item(j) instanceof Element item) {
                        String value = item.getTextContent();
                        switch (item.getTagName()) {
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
                            case PARAMETERS -> {
                                NodeList parameters = item.getChildNodes();
                                for (int k = 0; k < parameters.getLength(); k++) {
                                    if (parameters.item(k) instanceof Element parameter) {
                                        value = parameter.getTextContent();
                                        switch (parameter.getTagName()) {
                                            case TARIFICATION -> builder.tariffication(Float.parseFloat(value));
                                            case FAVORITE_NUMBER -> builder.favoriteNumber(value);
                                        }
                                    }
                                }
                            }
                            case OPERATOR -> {
                                NodeList parameters = item.getChildNodes();
                                for (int k = 0; k < parameters.getLength(); k++) {
                                    if (parameters.item(k) instanceof Element parameter) {
                                        value = parameter.getTextContent();
                                        switch (parameter.getTagName()) {
                                            case OPERATOR_NAME -> builder.operatorName(value);
                                            case COUNTRY -> builder.country(value);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                tariffs.add(builder.build());
            }
        }

        return tariffs;
    }

    private Element getRootElementFromFactory(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "false");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "true");
        builder = factory.newDocumentBuilder();
        document = builder.parse(file);

        return document.getDocumentElement();
    }
}
