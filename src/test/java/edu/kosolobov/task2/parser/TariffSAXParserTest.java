package edu.kosolobov.task2.parser;

import edu.kosolobov.task2.entity.Tariff;
import edu.kosolobov.task2.reader.FileReader;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class TariffSAXParserTest {

    @Test
    void parse() throws ParserConfigurationException, IOException, SAXException {
        FileReader reader = new FileReader();
        TariffSAXParser parser = new TariffSAXParser();

        String filePath = "files/TariffsExample.xml";
        File file = reader.read(filePath);

        List<Tariff> tariffs = parser.parse(file);
        assertFalse(tariffs.isEmpty());
    }

}