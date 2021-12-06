package edu.kosolobov.task2.validator;

import edu.kosolobov.task2.reader.TariffFileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TariffFileValidator {
    private static final Logger log = LogManager.getLogger();
    private static final String XSD_PATH = "files/TariffSchema.xsd";

    private TariffFileValidator() {
    }

    public static boolean isValid(File file) {
        StreamSource source = new StreamSource(file);

        SchemaFactory factory = SchemaFactory.newDefaultInstance();
        try {
            Schema schema = factory.newSchema(getXSD());
            Validator validator = schema.newValidator();
            validator.validate(source);
        } catch (SAXException | IOException e) {
            log.log(Level.ERROR, "File {} is invalid (schema: {})", file.getAbsolutePath(), XSD_PATH);
            return false;
        }

        log.log(Level.INFO, "File {} is valid", file.getAbsolutePath());
        return true;
    }

    private static File getXSD() {
        URL fileUrl = TariffFileReader.class
                .getClassLoader()
                .getResource(XSD_PATH);
        if (fileUrl == null) {
            log.log(Level.ERROR, "Invalid filepath to XSD: {}", XSD_PATH);
            return null;
        } else if (fileUrl.getFile() == null) {
            log.log(Level.ERROR, "XSD {} does not exist", XSD_PATH);
            return null;
        }

        return new File(fileUrl.getFile());
    }
}