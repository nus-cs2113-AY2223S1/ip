package duke.datafile;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class EditDataFile extends DataFile {

    public EditDataFile() {}

    @Override
    public void writeToFile(String taskDetail) throws IOException {
        FileWriter addTask = new FileWriter(dirPath);
        addTask.write(taskDetail);
        addTask.close();
    }

    @Override
    public void saveToFile(String taskDetail) {
        try {
            writeToFile(taskDetail);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<String> storeDataFileContents() {
        ArrayList<String> contentList = new ArrayList<>();
        try {
            contentList = getDataFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return contentList;
    }

    public ArrayList<String> getDataFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(dataFile);
        ArrayList<String> contentList = new ArrayList<>();
        while (s.hasNext()) {
            contentList.add(s.nextLine());
        }
        return contentList;
    }
}
