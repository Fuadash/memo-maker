package main.classes;

import java.io.File;

public class FolderManager {
    public static boolean ensureFolderExists(String path) {
        File folder = new File(path);

        if (folder.exists()) {
            return true;
        } else {
            if (folder.mkdir()) {
                System.out.println("Folder '" + folder + "' created successfully.");
                return true;
            } else {
                System.err.println("Failed to create the folder: " + folder);
                return false;
            }
        }
    }
}