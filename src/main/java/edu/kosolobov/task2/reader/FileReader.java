package edu.kosolobov.task2.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;

public class FileReader {
    private static final Logger log = LogManager.getLogger(FileReader.class);

    public FileReader() {
        super();
    }

    public File read(String filePath) {
        URL fileUrl = FileReader.class
                .getClassLoader()
                .getResource(filePath);

        if (fileUrl == null) {
            log.log(Level.ERROR, "File {} is not exist", filePath);
            return null;
        }

        return new File(fileUrl.getPath());
    }
}
