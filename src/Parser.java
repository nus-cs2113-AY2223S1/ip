import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser extends Constants{
    /**
     * Takes in a task stored in the data file and convert it to be stored
     * in the Duke bot's list if tasks
     * @param task get task written in file format
     * @return converted task based on its type
     * @throws Error if task in file is in the wrong format and cannot be converted
     */
    public Task taskFromFile(String task) throws Error {
        String[] reconstruct = task.split(" \\| ");
        switch (reconstruct[0]) {
            case "T":
                return new Todo(reconstruct[2], reconstruct[1].equals("X"));
            case "E":
                return new Event(reconstruct[2], reconstruct[3], reconstruct[1].equals("X"));
            case "D":
                return new Deadline(reconstruct[2], reconstruct[3], reconstruct[1].equals("X"));
            default:
                throw new Error(FILE_TASK_CONVERSION_ERROR);
        }
    }

    /**
     * Takes in the user input to store a certain type of task, and returns the task in
     * the correct format to be stored in the Duke bot's list of tasks
     * @param taskType get the type of task user has specified
     * @param details get the corresponding task description and time (if any) based on
     *                the task type
     * @return the specified task in the proper format to be stored
     * @throws Error if the user inputs the task details in the wrong format
     */
    private Task task(String taskType, String details) throws Error {
        String[] separateDetails;
        String description;
        String time;
        LocalDateTime parseTime;
        switch (taskType) {
            case TODO:
                return new Todo(details, false);

            case DEADLINE:
                separateDetails = details.split("/by", 2);
                if (separateDetails.length != 2) {
                    throw new Error(DEADLINE_FORMAT_ERROR);
                } else if (separateDetails[0].equals("")) {
                    throw new Error(DEADLINE_DESCRIPTION_ERROR);
                }
                try {
                    description = separateDetails[0];
                    parseTime = LocalDateTime.parse(separateDetails[1]);
                    time = parseTime.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm"));
                    time = "(by: " + time + ")";
                    return new Deadline(description, time, false);
                }
                catch (DateTimeParseException error) {
                    throw new Error(WRONG_TIME_FORMAT);
                }

            case EVENT:
                separateDetails = details.split("/at", 2);
                if (separateDetails.length != 2) {
                    throw new Error(EVENT_FORMAT_ERROR);
                } else if (separateDetails[0].equals("")) {
                    throw new Error(EVENT_DESCRIPTION_ERROR);
                }
                try {
                    description = separateDetails[0];
                    parseTime = LocalDateTime.parse(separateDetails[1]);
                    time = parseTime.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm"));
                    time = "(at: " + time + ")";
                    return new Deadline(description, time, false);
                }
                catch (DateTimeParseException error) {
                    throw new Error(WRONG_TIME_FORMAT);
                }

            default:
                throw new Error(INVALID_COMMAND);
        }
    }

    /**
     * The function modifies the list of tasks and file contents based on the user's actions
     * @param index determines which task on the list to be modified
     * @param command determines the action to be done on the selected task
     * @param taskList is the list of tasks to choose the task from
     * @param storage is the class that handles loading of tasks from the file
     *                and saving tasks in the file
     */
    private void modifyTask(int index, String command, TaskList taskList, Storage storage) {
        switch (command) {
            case MARK:
                taskList.mark(index);
                storage.writeToFile(taskList);
                break;
            case UNMARK:
                taskList.unmark(index);
                storage.writeToFile(taskList);
                break;
            case DELETE:
                taskList.delete(index);
                storage.writeToFile(taskList);
                break;
        }
    }

    /**
     * Takes in a 1 word user input and handles cases where the user has sent a command
     * in the wrong format
     * @param input gets the user command
     * @param taskList is the lists of tasks to be accessed based on the user command
     */
    public void singleCommand(String input, TaskList taskList) {
        try {
            switch (input) {
                case LIST:
                    taskList.showList();
                    break;
                case UNMARK:
                    throw new Error(UNMARK_ERROR);
                case MARK:
                    throw new Error(MARK_ERROR);
                case DEADLINE:
                    throw new Error(DELETE_ERROR);
                case FIND:
                    throw new Error(MISSING_KEYWORD);
                default:
                    throw new Error(INVALID_COMMAND);
            }
        } catch (Error error) {
            System.out.println(error.getMessage());
        }
    }

    /**
     * Takes in a multi-word input from the user and handles cases where
     * the user has sent a command in the wrong format
     * @param get the multi-word command as an array of single commands
     * @param taskList is the lists of tasks to be accessed based on the user command
     * @param ui is the class that deals with displaying messages to the user
     * @param storage is the class that handles loading of tasks from the file
     *                and saving tasks in the file
     */
    public void multipleCommands(String[] get, TaskList taskList, Ui ui, Storage storage) {
        switch (get[TASK]) {
            case LIST:
                ui.listMessage();
                taskList.showList();
                break;
            case UNMARK:
            case MARK:
            case DELETE:
                try {
                    int index = Integer.parseInt(get[NUMBER]);
                    modifyTask(index, get[TASK], taskList, storage);
                }
                catch (IndexOutOfBoundsException error) {
                    System.out.println(OUT_OF_BOUNDS);
                }
                catch (NumberFormatException error) {
                    System.out.println(NOT_INTEGER);
                }
                break;
            case DEADLINE:
            case EVENT:
            case TODO:
                try {
                    String details = get[DETAILS].replaceAll("\\s", "");
                    Task task = task(get[TASK], details);
                    taskList.addTask(task);
                    storage.appendToFile(task.fileFormat());
                }
                catch (Error error) {
                    System.out.println(error.getMessage());
                }
                break;
            case FIND:
                taskList.showMatchedTasks(get[DETAILS]);
                break;
            default:
                System.out.println(INVALID_COMMAND);
                break;
        }
    }
}