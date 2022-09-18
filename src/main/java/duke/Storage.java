package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "data.txt";

    public static String loadData(TaskList taskList) throws FileNotFoundException {
        File data = new File(FILE_PATH);
        Scanner scanner = new Scanner(data);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                parseLine(line, taskList);
            } catch (DukeException.IllegalReadFromSaveData | DukeException.IllegalMarkTargetException e) {
                return e.getMessage();
            }
        }
        return "Successfully loaded your last save of tasks to do!\n" + taskList.listTasks();
    }

    private static void parseLine(String line, TaskList taskList)
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
            createNewTodo(arrOfLine, taskList);
            break;
        case "D":
            if (length == 3) {
                throw new DukeException.IllegalReadFromSaveData();
            }
            createNewDeadline(arrOfLine, taskList);
            break;
        case "E":
            if (length == 3) {
                throw new DukeException.IllegalReadFromSaveData();
            }
            createNewEvent(arrOfLine, taskList);
            break;
        default:
            throw new DukeException.IllegalReadFromSaveData();
        }
    }

    private static void createNewEvent(String[] arrOfLine, TaskList taskList)
            throws DukeException.IllegalMarkTargetException {
        taskList.addNewEvent(arrOfLine[1], arrOfLine[3], false);
        if (arrOfLine[2].equals("1")) {
            taskList.markTasks(true, taskList.getNumberOfTasks(), false);
        }
    }

    private static void createNewDeadline(String[] arrOfLine, TaskList taskList)
            throws DukeException.IllegalMarkTargetException {
        taskList.addNewDeadline(arrOfLine[1], arrOfLine[3], false);
        if (arrOfLine[2].equals("1")) {
            taskList.markTasks(true, taskList.getNumberOfTasks(), false);
        }
    }

    private static void createNewTodo(String[] arrOfLine, TaskList taskList)
            throws DukeException.IllegalMarkTargetException {
        taskList.addNewTodo(arrOfLine[1], false);
        if (arrOfLine[2].equals("1")) {
            taskList.markTasks(true, taskList.getNumberOfTasks(), false);
        }
    }

    public static void saveData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(taskList.saveTaskList());
        fw.close();
    }
}
