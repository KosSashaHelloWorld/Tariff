package edu.kosolobov.task2.parser;

import edu.kosolobov.task2.entity.Tariff;
import edu.kosolobov.task2.reader.TariffFileReader;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;


public class TariffParsersTest {
    @Test
    void parse() throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        TariffDOMParser parserDOM = new TariffDOMParser();
        TariffStAXParser parserSAX = new TariffStAXParser();
        TariffStAXParser parserStAX = new TariffStAXParser();


        TariffFileReader reader = new TariffFileReader();

        String filePath = "files/TariffsExample.xml";
        File file = reader.read(filePath);

        print(parserDOM.parse(file).get(0));
        print(parserSAX.parse(file).get(0));
        print(parserStAX.parse(file).get(0));
    }

    private void print(Tariff tariff) {
        System.out.println(tariff.getName());
        System.out.println(tariff.getPayroll());
        System.out.println(tariff.getInternet1MBPrice());
        System.out.println(tariff.getInsideCallPrice());
        System.out.println(tariff.getOutsideCallPrice());
        System.out.println(tariff.getFavoriteCallPrice());
        System.out.println(tariff.getHomeCallPrice());
        System.out.println(tariff.getSmsPrice());
        System.out.println(tariff.getRoamingCallPrice());
        System.out.println(tariff.getRoamingSmsPrice());
        System.out.println(tariff.getParameters().getTarification());
        System.out.println(tariff.getParameters().getFavoriteNumbers());
        System.out.println(tariff.getOperator().getName());
        System.out.println(tariff.getOperator().getCountry());
        System.out.println(tariff.getId());
        System.out.println(tariff.getStartDate());
        System.out.println(tariff.getEndDate());
    }
}
