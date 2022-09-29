package duke.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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

    public void loadDataFromFile() {

        try {
            Scanner sc = new Scanner(new FileInputStream(filePath));

            while (sc.hasNextLine()) {
                String pastCommand = sc.nextLine();
                commandHistory.add(pastCommand);
            }

        } catch (FileNotFoundException e) {
            System.out.println("no past data.....welcome new user!!");
        }

    }

    public void setLoadStatus(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public ArrayList<String> getData() {
        return commandHistory;
    }

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
