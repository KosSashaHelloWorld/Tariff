package edu.kosolobov.task2.reader;

import edu.kosolobov.task2.validator.TariffFileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;

public class TariffFileReader {
    private static final Logger log = LogManager.getLogger(TariffFileReader.class);

    public TariffFileReader() {
        super();
    }

    public File read(String filePath) {
        URL fileUrl = TariffFileReader.class
                .getClassLoader()
                .getResource(filePath);

        File file = new File(fileUrl.getFile());

        if (TariffFileValidator.isValid(file)) {
            return file;
        } else {
            return null;
        }
    }
}
