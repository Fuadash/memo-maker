package test;
import main.classes.FolderManager;
import main.classes.MemoConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
public class MainTest {
    @Test
    void testCreateFolders() throws IOException {
        String folderPath = MemoConfig.getBasePath() + "logs";
        FolderManager.ensureFolderExists(folderPath);

        Path folderPathObject = Path.of(folderPath);
        assertTrue(Files.exists(folderPathObject));

        Files.deleteIfExists(folderPathObject);
    }
}
