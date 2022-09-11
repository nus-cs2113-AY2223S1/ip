package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "/src/main/java/duke/data.txt";

    public static void loadData(TaskManager taskManager) throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + FILE_PATH;
        File data = new File(filePath);
        Scanner scanner = new Scanner(data);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                parseLine(line, taskManager);
            } catch (DukeException.IllegalReadFromSaveData e) {
                System.out.println("Oops, something went wrong, unable to read from last save");
                return;
            } catch (DukeException.IllegalMarkTargetException e) {
                System.out.println("Oops, something went wrong, unable to read from last save");
                return;
            }
        }
        System.out.println("Successfully loaded your last save of tasks to do!");
        taskManager.listTasks();
    }

    private static void parseLine(String line, TaskManager taskManager)
            throws DukeException.IllegalReadFromSaveData, DukeException.IllegalMarkTargetException {
        String[] arrOfLine = line.split("\\|");
        int length = arrOfLine.length;

        if (length < 3 || length > 4) {
            throw new DukeException.IllegalReadFromSaveData();
        }
        String taskType = arrOfLine[0];

        switch (taskType) {
        case "T":
            if (length == 4) {
                throw new DukeException.IllegalReadFromSaveData();
            }
            createNewTodo(arrOfLine, taskManager);
            break;
        case "D":
            if (length == 3) {
                throw new DukeException.IllegalReadFromSaveData();
            }
            createNewDeadline(arrOfLine, taskManager);
            break;
        case "E":
            if (length == 3) {
                throw new DukeException.IllegalReadFromSaveData();
            }
            createNewEvent(arrOfLine, taskManager);
            break;
        default:
            throw new DukeException.IllegalReadFromSaveData();
        }
    }

    private static void createNewEvent(String[] arrOfLine, TaskManager taskManager)
            throws DukeException.IllegalMarkTargetException {
        taskManager.addNewEvent(arrOfLine[1], arrOfLine[3], false);
        if (arrOfLine[2].equals("1")) {
            taskManager.markTasks(true, taskManager.getNumberOfTasks(), false);
        }
    }

    private static void createNewDeadline(String[] arrOfLine, TaskManager taskManager)
            throws DukeException.IllegalMarkTargetException {
        taskManager.addNewDeadline(arrOfLine[1], arrOfLine[3], false);
        if (arrOfLine[2].equals("1")) {
            taskManager.markTasks(true, taskManager.getNumberOfTasks(), false);
        }
    }

    private static void createNewTodo(String[] arrOfLine, TaskManager taskManager)
            throws DukeException.IllegalMarkTargetException {
        taskManager.addNewTodo(arrOfLine[1], false);
        if (arrOfLine[2].equals("1")) {
            taskManager.markTasks(true, taskManager.getNumberOfTasks(), false);
        }
    }

    public static void saveData(TaskManager taskManager) throws IOException {
        String filePath = System.getProperty("user.dir") + FILE_PATH;
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskManager.saveTaskList());
        fw.close();
    }
}
