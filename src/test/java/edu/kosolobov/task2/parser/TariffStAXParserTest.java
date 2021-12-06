package edu.kosolobov.task2.parser;

import edu.kosolobov.task2.entity.Tariff;
import edu.kosolobov.task2.reader.TariffFileReader;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TariffStAXParserTest {

    @Test
    void parse() throws XMLStreamException, IOException, SAXException {
        TariffFileReader reader = new TariffFileReader();
        TariffStAXParser parser = new TariffStAXParser();

        String filePath = "files/TariffsExample.xml";
        File file = reader.read(filePath);

        List<Tariff> tariffs = parser.parse(file);
        assertFalse(tariffs.isEmpty());
    }
}