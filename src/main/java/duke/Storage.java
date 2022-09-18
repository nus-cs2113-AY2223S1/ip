package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Runs all storage related operations such as saving and loading data.
 */
public class Storage {
    private static final String FILE_PATH = "data.txt";
    private static final int TODOS_LENGTH = 3;
    private static final int EVENTS_DEADLINES_LENGTH =  4;

    /**
     * Tries to find and load past data into the task list of the current running program.
     *
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @return Message to user for success or failure to load past save.
     * @throws FileNotFoundException If specified file for past save is not found in the file path.
     */
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
        return Printables.SUCCESSFUL_LOAD_MESSAGE + taskList.listTasks();
    }

    /**
     * Makes sense of a given line of data found in the past save and adds the data into the program.
     *
     * @param line Line of data from past save.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @throws DukeException.IllegalReadFromSaveData If line is invalid.
     * @throws DukeException.IllegalMarkTargetException If index for task marking is out of range.
     */
    private static void parseLine(String line, TaskList taskList)
            throws DukeException.IllegalReadFromSaveData, DukeException.IllegalMarkTargetException {
        String[] arrOfLine = line.split("\\|");
        int length = arrOfLine.length;

        if (length < TODOS_LENGTH || length > EVENTS_DEADLINES_LENGTH) {
            throw new DukeException.IllegalReadFromSaveData();
        }
        String taskType = arrOfLine[0];

        switch (taskType) {
        case "T":
            if (length == EVENTS_DEADLINES_LENGTH) {
                throw new DukeException.IllegalReadFromSaveData();
            }
            createNewTodo(arrOfLine, taskList);
            break;
        case "D":
            if (length == TODOS_LENGTH) {
                throw new DukeException.IllegalReadFromSaveData();
            }
            createNewDeadline(arrOfLine, taskList);
            break;
        case "E":
            if (length == TODOS_LENGTH) {
                throw new DukeException.IllegalReadFromSaveData();
            }
            createNewEvent(arrOfLine, taskList);
            break;
        default:
            throw new DukeException.IllegalReadFromSaveData();
        }
    }

    /**
     * Adds a new event task to the task list and marks it if necessary.
     *
     * @param arrOfLine Array of string containing the different components of the data.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @throws DukeException.IllegalMarkTargetException If index for task marking is out of range.
     */
    private static void createNewEvent(String[] arrOfLine, TaskList taskList)
            throws DukeException.IllegalMarkTargetException {
        taskList.addNewEvent(arrOfLine[1], arrOfLine[3], false);
        if (arrOfLine[2].equals("1")) {
            taskList.markTasks(true, taskList.getNumberOfTasks(), false);
        }
    }

    /**
     * Adds a new deadline task to the task list and marks it if necessary.
     *
     * @param arrOfLine Array of string containing the different components of the data.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @throws DukeException.IllegalMarkTargetException If index for task marking is out of range.
     */
    private static void createNewDeadline(String[] arrOfLine, TaskList taskList)
            throws DukeException.IllegalMarkTargetException {
        taskList.addNewDeadline(arrOfLine[1], arrOfLine[3], false);
        if (arrOfLine[2].equals("1")) {
            taskList.markTasks(true, taskList.getNumberOfTasks(), false);
        }
    }

    /**
     * Adds a new todos task to the task list and marks it if necessary.
     *
     * @param arrOfLine Array of string containing the different components of the data.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @throws DukeException.IllegalMarkTargetException If index for task marking is out of range.
     */
    private static void createNewTodo(String[] arrOfLine, TaskList taskList)
            throws DukeException.IllegalMarkTargetException {
        taskList.addNewTodo(arrOfLine[1], false);
        if (arrOfLine[2].equals("1")) {
            taskList.markTasks(true, taskList.getNumberOfTasks(), false);
        }
    }

    /**
     * Saves the current task list by overwriting the data in the text file.
     *
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @throws IOException If error occurs during input or output operations.
     */
    public static void saveData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(taskList.saveTaskList());
        fw.close();
    }
}
