package test;
import main.classes.MemoLogger;
import main.classes.MultiDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestMemoLogger {

    /*TODO: Test for making the memo file itself, whether it adds the correct text to the file, whether it adds text to an
        already existing file, whether it makes the memo file when the folder already exists, test whether it goes to the right dates?
    */

    //TODO: Maybe make a test cleanup method that just nukes the logs folder and everything inside recursively?

    //TODO: Better error handling, maybe?
    @Test
    void testCreateMemoFile(){
        // Set up the dummy date
        String testDate = "1988-03-01 00:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dummyDate = null;
        try {
            dummyDate = dateFormat.parse(testDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        MemoLogger.logMemo("Test", new MultiDate(dummyDate));

        // Make the paths to the various folders that are created here
        Path pathToMemoFile = Path.of("./test/logs/1988-03/memo_1988-03-01.txt");

        // Check the memo file itself exists
        assertTrue(Files.exists(pathToMemoFile));
    }

    @Test
    void testCreateMemoText() throws ParseException {
        String testText = "test2";

        // Set up the dummy date
        String testDate = "1988-03-01 00:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dummyDate = dateFormat.parse(testDate);
        MultiDate dummyMultiDate = new MultiDate(dummyDate);

        MemoLogger.logMemo(testText, dummyMultiDate);

        // Make the paths to the various folders that are created here
        Path pathToMemoFile = Path.of("./test/logs/1988-03/memo_1988-03-01.txt");

        // Check the memo file itself exists
        try(BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(pathToMemoFile)))){
            String line = reader.readLine();
            assertEquals(dummyMultiDate.time + " - " + testText, line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    void testCreateMemoTwice() {
//        MemoLogger.logMemo("Test", new MultiDate());
//    }

    @AfterEach
    public void cleanup() {
        String currentDir = System.getProperty("user.dir");
        File logDirectory = new File("./test/logs");

        // Safety Checks as we're doing a destructive process
        if(currentDir.endsWith("src") && logDirectory.exists()){
            System.out.println("Cleaning up.");
            recursiveDelete(logDirectory);
            System.out.println("Cleanup complete.");
        }
        else {
            fail("Cleanup failed: This may be getting called from the wrong place, check the working directory and such");
        }
    }

    public static void recursiveDelete(File file) {
        if (file.isDirectory()) {
            // If it's a directory, list its content and delete each recursively
            File[] content = file.listFiles();
            if (content != null) {
                for (File subFile : content) {
                    recursiveDelete(subFile);
                }
            }
        }

        // Delete the file or an empty directory
        if (!file.delete()) {
            System.err.println("Failed to delete: " + file.getAbsolutePath());
        } else {
            System.out.println("Deleted: " + file.getAbsolutePath());
        }
    }
}
