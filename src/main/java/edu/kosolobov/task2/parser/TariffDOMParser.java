package edu.kosolobov.task2.parser;

import edu.kosolobov.task2.builder.TariffBuilder;
import edu.kosolobov.task2.entity.Tariff;
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

public class TariffDOMParser {
    private static final Logger log = LogManager.getLogger(TariffDOMParser.class);

    public List<Tariff> parse(File file) throws ParserConfigurationException, IOException, SAXException {
        List<Tariff> tariffs = new ArrayList<>();

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
                builder.id(element.getAttribute("id"));
                builder.startDate(element.getAttribute("start"));
                builder.endDate(element.getAttribute("end"));
                NodeList items = element.getChildNodes();
                for (int j = 0; j < items.getLength(); j++) {
                    if (items.item(j) instanceof  Element item) {
                        String value = item.getTextContent();
                        switch (item.getTagName()) {
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
                            case "parameters" -> {
                                NodeList parameters = item.getChildNodes();
                                for (int k = 0; k < parameters.getLength(); k++) {
                                    if (parameters.item(k) instanceof Element parameter) {
                                        value = parameter.getTextContent();
                                        switch (parameter.getTagName()) {
                                            case "tarification_minutes" -> builder.tariffication(Float.parseFloat(value));
                                            case "favorite_number" -> builder.favoriteNumber(value);
                                        }
                                    }
                                }
                            }
                            case "operator" -> {
                                NodeList parameters = item.getChildNodes();
                                for (int k = 0; k < parameters.getLength(); k++) {
                                    if (parameters.item(k) instanceof Element parameter) {
                                        value = parameter.getTextContent();
                                        switch (parameter.getTagName()) {
                                            case "operator-name" -> builder.operatorName(value);
                                            case "country" -> builder.country(value);
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
