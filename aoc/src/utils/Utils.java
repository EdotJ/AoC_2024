package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String RELATIVE_DIR = "aoc/resources/";

    public static List<String> readAllLines(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = getFileReader(fileName)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }
    public static BufferedReader getFileReader(String fileName) throws FileNotFoundException {
        String filePath = RELATIVE_DIR + fileName;
        File file = new File(filePath);
        return new BufferedReader(new FileReader(file));
    }
}
