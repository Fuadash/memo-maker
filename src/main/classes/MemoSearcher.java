package main.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Searches and returns memos
public class MemoSearcher {
    public static List<Path> searchMemosFor(Path root, String target) {
        List<Path> matchingFiles = new ArrayList<>();

        // Add all folders under the root folder to a stream
        try (Stream<Path> monthFolders = Files.list(root)){
            monthFolders.forEach(monthFolder -> {
                if(Files.isDirectory(monthFolder)){
                    // Collect all files for the month that have matching text
                    try (Stream<Path> memoFiles = Files.list(monthFolder)){
                        List<Path> filesWithText = memoFiles
                                .filter(file -> containsTargetText(file, target))
                                .collect(Collectors.toList());
                        matchingFiles.addAll(filesWithText);
                    } catch (IOException e) {
                        e.printStackTrace();
                        //todo: Do stuff
                    }
                }
            });
        } catch (IOException e){
            e.printStackTrace();
            //todo: Do stuff
        }

        return matchingFiles;
    }

    private static boolean containsTargetText (Path file, String target) {
        try (Stream<String> lines = Files.lines(file)) {
            return lines.anyMatch(line -> line.contains(target));
        } catch (IOException e) {
            e.printStackTrace(); //todo: DO STUFF
            return false;
        }
    }
}
