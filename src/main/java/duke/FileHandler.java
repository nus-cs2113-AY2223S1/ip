package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class FileHandler {
    public static String HOME_DIRECTORY = new File("").getAbsolutePath();

    public static void createFile(Path path) {
        try {
            Path newPath = Files.createFile(path);
        } catch (IOException e) {
            System.out.println(Message.CREATE_FILE_FAIL_ERROR_MESSAGE);
        }
    }

    public static void createDirectory(Path path) {
        try {
            Path newDirectory = Files.createDirectory(path);
        } catch (IOException e) {
            System.out.println(Message.CREATE_DIRECTORY_FAIL_ERROR_MESSAGE);
        }
    }

}
