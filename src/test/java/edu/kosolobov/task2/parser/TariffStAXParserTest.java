package edu.kosolobov.task2.parser;

import edu.kosolobov.task2.entity.Tariff;
import edu.kosolobov.task2.reader.FileReader;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TariffStAXParserTest {

    @Test
    void parse() throws XMLStreamException, FileNotFoundException {
        FileReader reader = new FileReader();
        TariffStAXParser parser = new TariffStAXParser();

        String filePath = "files/TariffsExample.xml";
        File file = reader.read(filePath);

        List<Tariff> tariffs = parser.parse(file);
        assertFalse(tariffs.isEmpty());
    }
}