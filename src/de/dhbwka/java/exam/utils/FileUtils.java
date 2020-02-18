package de.dhbwka.java.exam.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class FileUtils {

    public static String readFromFile(final File file) {
        String result = "";
        if (file.exists()) {
            try {
                result = new String(Files.readAllBytes(file.toPath()), "UTF-8");
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
        	System.err.println("File does not exist");
        }
        return result;
    }

    public static String[] readLinesFromFile(final File file) {
        if (file.exists()) {
            try (Stream<String> lines = Files.lines(file.toPath())) {
                return lines.toArray(String[]::new);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String[]{};
    }

    public static void writeToFile(final File file, final String data, final boolean append, final boolean lineBreak) {
        try (FileWriter writer = new FileWriter(file, append)) {
            file.createNewFile();
            writer.append(data + (lineBreak ? System.lineSeparator() : ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile(final File file, final String[] lines, final boolean append) {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(lines).forEach(line -> stringBuilder.append(line + System.lineSeparator()));
        writeToFile(file, stringBuilder.toString(), append, false);
    }

}