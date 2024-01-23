package main;

import main.classes.MemoLogger;
import main.classes.MultiDate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO: Add some way to mark a day as important then give it visibility
        //TODO: output absolute file paths
        //TODO: Make the memos markdown files?
        //TODO: Add tests
        //TODO: A way to search memos for strings of text, add regex functionality too, think about how many results to return
        //TODO: Better error handling?
        //TODO: Tagging memos with certain inputs so you can search them later sounds like a vibe? But maybe this is included under search already?
        //TODO: Export to...?
        //TODO: Displaying memos probably is something that could be done
        //TODO: Make it work from my phone, gonna need to have it running on a server or something idk
        //TODO: Make it possible to enter memos at different times other than the current moment
        //TODO: Maybe a way to set alarms/notifications/get emailed at a certain time if you set an alarm memo in the future
        //TODO: AI summary feature I GUESS
        //TODO: Let it ask for multiple memos
        //TODO: "Automatically capture certain system events (e.g., shutdown, startup) and log them as memos" sounds interesting?
        //TODO: Memo shortcuts so I can repeat memos with quick inputs
        //TODO: Make a UI?
        //TODO: Discord webhook or something

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("What to add to the memo: ");
            String input = scanner.nextLine();

            MemoLogger.logMemo(input, new MultiDate());
        }
    }

}
