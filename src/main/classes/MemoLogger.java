package main.classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MemoLogger {
    public static void logMemo(String input, MultiDate logDate) {

        // Gets the base path based on APP_ENV
        String basePath = MemoConfig.getBasePath();

        FolderManager.ensureFolderExists(basePath + "logs");
        boolean createdFolder = FolderManager.ensureFolderExists(basePath + "logs/" + logDate.month);

        String fileName = basePath + "logs/" + logDate.month + "/memo_" + logDate.date + ".txt";

        if (createdFolder) {
            addToMemo(fileName, input, logDate.time);
        } else {
            System.err.println("Failed to make the folder for whatever reason idk");
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
            System.out.println("New entry added at " + fileName);

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
}