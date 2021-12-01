package edu.kosolobov.task2.validator;

import edu.kosolobov.task2.reader.FileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
public class FileValidator {
    private static final Logger log = LogManager.getLogger();

    private FileValidator() {
    }

    public static boolean isValid(String filePath) {
        URL fileUrl = FileReader.class
                .getClassLoader()
                .getResource(filePath);
        if (fileUrl == null) {
            log.log(Level.ERROR, "File {} does not exist", filePath);
            return false;
        } else if (fileUrl.getFile() == null) {
            log.log(Level.ERROR, "File {} is not valid", filePath);
            return false;
        } else {
            log.log(Level.INFO, "File {} is valid", filePath);
            return true;
        }
    }
}