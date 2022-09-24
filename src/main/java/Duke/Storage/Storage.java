package Duke.Storage;

import Duke.Duke;
import Duke.Tasks.TaskManager;
import Duke.Tasks.Tasks;
import Duke.Tasks.ToDo;
import Duke.Tasks.Event;
import Duke.Tasks.Deadline;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private final static String FILEPATH = "./data/dukesaved.txt";
    private static File file = new File(FILEPATH);
    private static String directory;

    public Storage() {
        this.directory = FILEPATH;
    }

    public void loadData(TaskManager taskManager) throws IOException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                readFromFile(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            fileNotFoundMessage();
            createFile();
        }

    }

    public void createFile() throws IOException {
        Path pathToFile = Paths.get(FILEPATH);
        Files.createDirectories(pathToFile.getParent());
        file.createNewFile();
    }

    public void fileNotFoundMessage() {
        System.out.println(
                Duke.PRINT_LINE
                        + "File not Found.\n"
                        + "Creating " + FILEPATH + "\n"
                        + Duke.PRINT_LINE
        );
    }

    public static void readFromFile(String data) throws FileNotFoundException {
        String[] dataBreakdown = data.split("\\|");
        switch (dataBreakdown[0]) {
        case "T":
            addFileToDo(dataBreakdown[1],dataBreakdown[2]);
            break;
        case "E":
            addFileEvent(dataBreakdown[1],dataBreakdown[2],dataBreakdown[3]);
            break;
        case "D":
            addFileDeadline(dataBreakdown[1],dataBreakdown[2],dataBreakdown[3]);
            break;
        }
    }


    public static void writeToFile() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH);
            for (int i = 0; i < TaskManager.taskList.size(); i++) {
                fileWriter.write(TaskManager.taskList.get(i).toFile());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("IO Error writing to file");
        }
    }

    public static void addFileToDo(String mark, String description) {
        ToDo toDo = new ToDo(description);
        insertTaskFromFile(toDo);
        if (mark.equals("1")) {
            toDo.insertDone();
        } else if (mark.equals("0")) {
            toDo.insertNotDone();
        }

    }


    public static void addFileEvent(String mark, String description, String at) {
        Event event = new Event(description, at);
        insertTaskFromFile(event);
        if (mark.equals("1")) {
            event.insertDone();
        } else if (mark.equals("0")) {
            event.insertNotDone();
        }
    }

    public static void addFileDeadline(String mark, String description, String by) {
        Deadline deadline = new Deadline(description, by);
        insertTaskFromFile(deadline);
        if (mark.equals("1")) {
            deadline.insertDone();
        } else if (mark.equals("0")) {
            deadline.insertNotDone();
        }
    }

    public static void insertTaskFromFile (Tasks task) {
        TaskManager.taskList.add(TaskManager.numOfTasks, task);
        TaskManager.numOfTasks++;
    }


}

