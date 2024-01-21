package main;

import main.classes.MemoLogger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO: Add tests
        //TODO: A way to search memos
        //TODO: Do I need an edit/delete functionality? It's probably more work than doing it manually
        //TODO: Better error handling?
        //TODO: Add some way to mark a day as important then give it visibility
        //TODO: Tagging memos with certain inputs sounds like a vibe
        //TODO: Export
        //TODO: Make a UI?
        //TODO: Make it work from my phone, gonna need to have it running on a server or something idk

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("What to add to the memo: ");
            String input = scanner.nextLine();

            MemoLogger.logMemo(input);
        }
    }

}
