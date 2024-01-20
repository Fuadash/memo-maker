package main;

import main.classes.MemoConfig;
import main.classes.MultiDate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO: Add tests
        //TODO: A way to search for the last occurrence of a word in a memo
        //TODO: Better error handling?
        //TODO: Add some way to mark a day as important then give it visibility
        //TODO: Make a UI?
        //TODO: Make it work from my phone, gonna need to have it running on a server or something idk

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("What to add to the memo: ");
            String input = scanner.nextLine();

            logMemo(input);
        }
    }

    public static void logMemo(String input) {
        MultiDate currentMulti = new MultiDate();

        // Gets the base path based on APP_ENV
        String basePath = MemoConfig.getBasePath();

        ensureFolderExists(basePath + "logs");
        boolean createdFolder = ensureFolderExists(basePath + "logs/" + currentMulti.month);

        String fileName = basePath + "logs/" + currentMulti.month + "/memo_" + currentMulti.date + ".txt";

        if (createdFolder){
            addToMemo(fileName, input, currentMulti.time);
        }
        else {
            System.err.println("Failed to make the folder for whatever reason idk");
        }
    }

    public static void addToMemo(String fileName, String input, String currentTime) {
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

    public static boolean ensureFolderExists(String path) {
        File folder = new File(path);

        if (folder.exists()) {
            return true;
        }
        else {
            if (folder.mkdir()) {
                System.out.println("Folder '" + folder + "' created successfully.");
                return true;
            }
            else {
                System.err.println("Failed to create the folder: " + folder);
                return false;
            }
        }
    }

    // Check if a file exists at the current date
    //TODO: Make this not a method? Maybe?
    public static boolean entryExists(String fileName) throws IOException {
        try (Scanner fileScanner = new Scanner(new java.io.File(fileName))) {
            return fileScanner.hasNextLine(); // Entry already exists for the current date
        }
    }
}
