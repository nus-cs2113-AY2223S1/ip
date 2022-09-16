package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileHandler {
    private static final String HOME_DIRECTORY = new File("").getAbsolutePath();
    private static final String DATA_DIRECTORY = "data";
    private static final String DATA_FILE_NAME = "duke.txt";

    private static final Path DATA_DIRECTORY_PATH = Paths.get(HOME_DIRECTORY, DATA_DIRECTORY);
    public static final Path DATA_FILE_PATH = Paths.get(HOME_DIRECTORY, DATA_DIRECTORY, DATA_FILE_NAME);

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

    public static void initDataDirectory() {
        if (!Files.exists(DATA_DIRECTORY_PATH)) {
            FileHandler.createDirectory(DATA_DIRECTORY_PATH);
        }
    }

    public static void initDataFile() {
        initDataDirectory();

        if (!Files.exists(DATA_FILE_PATH)) {
            FileHandler.createFile(DATA_FILE_PATH);
        }
    }
}
