package com.decerto.demo.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

@Component
public class CsvFileReader {

    private static final String CSV_EXTENSION = ".csv";

    @Value("${numbers.filename}")
    private String filename;

    @Value("${numbers.valueseparator:,}")
    private String valueSeparator;

    public String[] getValueFromCsvFile() throws IOException {
        if(StringUtils.isEmpty(filename)) {
            throw new IllegalArgumentException("Filename cannot be empty.");
        }
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(checkFileName())){
            assert inputStream != null;
            int lineNumber = new Random().nextInt(countNumberOfLinesInInputStream(inputStream));
            return getRandomLineValue(classLoader, lineNumber);

        } catch (Exception e) {
            throw new IOException("Unable to get value from file: " + checkFileName() + ".");
        }
    }

    private String[] getRandomLineValue(ClassLoader classLoader, int lineNumber) {
        StringBuilder sb = new StringBuilder();
        final InputStream resourceAsStream = classLoader.getResourceAsStream(checkFileName());
        assert resourceAsStream != null;
        Scanner scanner = new Scanner(resourceAsStream);
        for (int i = 0; i < lineNumber; i++) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            } else {
                break;
            }
        }
        sb.append(scanner.nextLine());
        return sb.toString().split(valueSeparator);
    }

    @SneakyThrows
    private int countNumberOfLinesInInputStream(InputStream inputStream) {
        int summary = 0;
        final Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()){
            summary++;
            scanner.nextLine();
        }
        return summary;
    }

    private String checkFileName() {
        return filename.substring(filename.length() - CSV_EXTENSION.length()).equalsIgnoreCase(CSV_EXTENSION)
                ? filename : (filename + CSV_EXTENSION);
    }

}
