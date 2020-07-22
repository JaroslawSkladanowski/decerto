package com.decerto.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Component
public class CsvFileReader {

    private static final String CSV_EXTENSION = ".csv";

    @Value("${numbers.filename}")
    private String filename;

    @Value("${numbers.valueseparator:,}")
    private String valueSeparator;

    public String[] getValuesFromCsvFile() throws IOException {
        if(StringUtils.isEmpty(filename)) {
            throw new IllegalArgumentException("Filename cannot be empty.");
        }
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(checkFileName())){
            assert inputStream != null;
            final Scanner scanner = new Scanner(inputStream);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            return sb.toString().split(valueSeparator);

        } catch (Exception e) {
            throw new IOException("Unable to get values from file: " + checkFileName() + ".");
        }
    }

    private String checkFileName() {
        return filename.substring(filename.length() - CSV_EXTENSION.length()).equalsIgnoreCase(CSV_EXTENSION)
                ? filename : (filename + CSV_EXTENSION);
    }

}
