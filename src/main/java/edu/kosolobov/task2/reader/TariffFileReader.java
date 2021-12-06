package edu.kosolobov.task2.reader;

import edu.kosolobov.task2.validator.TariffFileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TariffFileReader {
    private static final Logger log = LogManager.getLogger(TariffFileReader.class);

    public TariffFileReader() {
        super();
    }

    public File read(String filePath) throws IOException, SAXException {
        TariffFileValidator.isValid(filePath);

        URL fileUrl = TariffFileReader.class
                .getClassLoader()
                .getResource(filePath);

        return new File(fileUrl.getPath());
    }
}
