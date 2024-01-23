package main.classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MemoLogger {
    public static void logMemo(String input, MultiDate logDate) {

        // Gets the base path based on APP_ENV
        String basePath = MemoConfig.getBasePath();

        // Check if the folders that are required exist, and create them if not
        boolean createdFolders = FolderManager.ensureFolderExists(basePath + "logs") &&
                FolderManager.ensureFolderExists(basePath + "logs/" + logDate.month);

        if (createdFolders) {
            String fileName = basePath + "logs/" + logDate.month + "/memo_" + logDate.date + ".txt";
            addToMemo(fileName, input, logDate.time);

            // Check for importance, add to important folder if it is important
            if (isImportantInput(input) || isImportantMemo(logDate)) {
                if (FolderManager.ensureFolderExists(basePath + "logs/important")) {
                    String importantFileName = basePath + "logs/important/memo_" + logDate.date + ".txt";
                    addToMemo(importantFileName, input, logDate.time);
                } else {
                    System.err.println("Failed to make the important folder"); //TODO: BETTER ERROR HANDLING
                }
            }
        } else {
            System.err.println("Failed to make the logs folder for the month, for some reason"); //TODO: BETTER ERROR HANDLING
        }
    }

    private static void addToMemo(String fileName, String input, String currentTime) {
        // If the folder got created without errors or already existed, append X to the text document
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Check if the current date already has an entry
            if (entryExists(fileName)) {
                writer.newLine(); // Move to a new line to log there
            }

            writer.write(currentTime + " - " + input);
            System.out.println("New entry added at " + Path.of(fileName).toAbsolutePath().normalize());

        } catch (IOException e) {
            System.err.println("Error appending to the text document: " + e.getMessage());
        }
    }

    // Check if a file exists at the current date
    private static boolean entryExists(String fileName) throws IOException {
        try (Scanner fileScanner = new Scanner(new java.io.File(fileName))) {
            return fileScanner.hasNextLine(); // Entry already exists for the current date
        } catch (IOException e) {
            // TODO: Some better error handling
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isImportantInput(String input){
        return input.toLowerCase().contains("!important");
    }

    private static boolean isImportantMemo(MultiDate memoMultiDate){
        String basePath = MemoConfig.getBasePath();
        Path memoPath = Path.of(basePath + "logs/important/memo_" + memoMultiDate.date + ".txt");
        return Files.exists(memoPath);
    }
}
