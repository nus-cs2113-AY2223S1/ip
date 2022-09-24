package duke.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Utilities{

    private static String filePath = "data/userData.txt";
    private static ArrayList<String> dataHistory = new ArrayList<>();

    //need to refactor to not record redundant input
    private static ArrayList<String> dataSession = new ArrayList<>();


    public Storage() {
    }

    public static void init() {
        dataHistory = new ArrayList<>();
        dataSession = new ArrayList<>();
    }

    public static void close() {
        dataHistory = new ArrayList<>();
        dataSession = new ArrayList<>();
    }

    public static void setPath(String path) {
        filePath = path;
    }

    public static void loadData() {

        try {
            Scanner sc = new Scanner(new FileInputStream(filePath));

            while (sc.hasNextLine()) {
                String pastCommand = sc.nextLine();
                dataHistory.add(pastCommand);
            }

        } catch (FileNotFoundException e) {
            System.out.println("no past data.....welcome new user!!");
        }

    }

    public static ArrayList<String> getHistory() {
        return dataHistory;
    }

    public static void addSessionCommand(String command) {
        dataSession.add(command);
    }

    public static void addSessionCommands(List<String> commands) {
        for(String command: commands){
            addSessionCommand(command);
        }
    }

    public static void writeData() {
        try {
            File dataFile = new File(filePath);


            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }

            FileWriter writer = new FileWriter(dataFile);

            for (String pastCommand : dataSession) {
                writer.append(pastCommand);
                writer.append("\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

}
