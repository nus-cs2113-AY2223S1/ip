package duke.util;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to read from userData file for commands from previous sessions
 * and write to userData file at the end of programme to store the task list
 */
public class Storage {

    private static String filePath;
    private ArrayList<String> commandHistory;
    private boolean isLoaded;

    public Storage(String filePath) {
        this.filePath = filePath;
        commandHistory = new ArrayList<>();
        isLoaded = false;
    }

    public Storage() {
        this.filePath = "data/userData.txt";
        commandHistory = new ArrayList<>();
        isLoaded = false;
    }

    /**
     * To load past user commands into the command history buffer
     *
     * @throws FileNotFoundException if user file is not found
     */
    public void loadDataFromFile() throws DukeException {

        try {
            Scanner sc = new Scanner(new FileInputStream(filePath));

            while (sc.hasNextLine()) {
                String pastCommand = sc.nextLine();
                commandHistory.add(pastCommand);
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("User file not found! New user!");
        }

    }

    /**
     * Help to set the loading status of the programme
     * Help for messages to be displayed only after command history has been added
     * @param isLoaded desired storage loading status
     */
    public void setLoadStatus(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public ArrayList<String> getData() {
        return commandHistory;
    }

    /**
     * Write the commands onto the userData file
     *
     * @param commands the list of commands to be written onto the storage file
     */
    public void writeData(List<String> commands) {
        try {
            File dataFile = new File(filePath);


            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }

            FileWriter writer = new FileWriter(dataFile);

            for (String command : commands) {
                writer.append(command);
                writer.append("\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

}
