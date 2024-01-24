package main.classes;

import java.nio.file.Path;
import java.util.List;

// Will (probably) parse user input and then decide which modules to point to
public class MemoController {
    public static void parseInput(String input) {
        if (input.toLowerCase().startsWith("!find")){
            String searchTarget = input.substring(5);
            Path root = Path.of(MemoConfig.getBasePath()+ "logs");
            List<Path> pathToMemos = MemoSearcher.searchMemosFor(root, searchTarget);
            pathToMemos.forEach(System.out::println); //TODO: Make this only print like 10 at a time, maybe move into a memoprinter class
        }
        else {
            MemoLogger.logMemo(input, new MultiDate());
        }
    }
}
