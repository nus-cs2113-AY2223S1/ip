package Duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

public class DataManager {

    private String path = "data/userData.txt";
    private ArrayList<String> userData;

    public DataManager() {
        userData = new ArrayList<String>();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void loadData() {

        try {
            Scanner sc = new Scanner(new FileInputStream(path));

            while (sc.hasNextLine()) {
                String pastCommand = sc.nextLine();
                userData.add(pastCommand);
            }

        } catch (FileNotFoundException e) {
            System.out.println("no past data.....welcome new user!!");
        }

    }

    public ArrayList<String> getData() {
        return userData;
    }

    public void writeData(ArrayList<String> history) {
        try {
            File dataFile = new File(path);

            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }

            FileWriter writer = new FileWriter(dataFile);

            for (String pastCommand : history) {
                writer.append(pastCommand);
                writer.append("\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

}
