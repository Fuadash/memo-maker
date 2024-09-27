package main.classes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemoPrinter {
    public String getAll() {
        Path root = Path.of(MemoConfig.getBasePath()+ "logs");
        List<Path> pathToMemos = MemoSearcher.searchMemosFor(root, "");
        try {
            StringBuilder concatenatedContent = new StringBuilder();

            for (Path path : pathToMemos) {
                String regex = "\\d{4}-\\d{2}-\\d{2}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(path.toString());

                if (matcher.find()) {
                    // Extracted date
                    String date = matcher.group(0);
                    String content = date + ": \n" + Files.readString(path) + "\n"; // Read file content as a string
                    concatenatedContent.append(content);
                } else {
                    System.out.println("Extracted Date: Could not parse: " + path.toString());
                    String content = "XXXX-XX-XX" + ": \n" + Files.readString(path) + "\n"; // Read file content as a string
                    concatenatedContent.append(content);
                }

            }

            return concatenatedContent.toString();

        } catch (Exception e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
            return null;
        }
    }
}
