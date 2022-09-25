import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {
    public static final String TODO = "todo";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final int TASK = 0;
    public static final int NUMBER = 1;
    public static final int DETAILS = 1;
    public static final String INVALID_COMMAND = "Sorry, please input a proper command!\n";
    public static final String DEADLINE_FORMAT_ERROR = "Invalid deadline format! Please remember to add '/by' before your deadline!";
    public static final String DEADLINE_DESCRIPTION_ERROR = "Please input a description before your deadline!";
    public static final String EVENT_DESCRIPTION_ERROR = "Please input a description before your event timing!";
    public static final String EVENT_FORMAT_ERROR = "Invalid event format! Please remember to add '/at' before your event timing!";
    public static final String FILE_TASK_CONVERSION_ERROR = "Invalid task format in data file! Please check data file!";
    public static final String DEADLINE_DATETIME_ERROR = "Please enter the date and time correctly!\n" +
            "Include a space ' ' to separate the date and time for your deadline!";
    public static final String EVENT_DATETIME_ERROR = "Please enter the date and time correctly!\n" +
            "Include a space ' ' to separate the date and time for your event timing!";
    public static final String WRONG_DATETIME_FORMAT = "Please input your date in YYYY-MM-DD format and time in HH:mm format!\n"
            +
            "Make sure that HH is between 0-23 and mm is between 00-60!";

    /**
     * Takes in a task stored in the data file and convert it to be stored
     * in the Duke bot's list if tasks
     * 
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

    private String parseDateAndTime(String[] dateTimeDetails) {
        String time, date;
        LocalTime parseTime;
        LocalDate parseDate;
        parseDate = LocalDate.parse(dateTimeDetails[0]);
        parseTime = LocalTime.parse(dateTimeDetails[1]);
        date = parseDate.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy"));
        time = parseTime.format(DateTimeFormatter.ofPattern("HH:mm a"));
        return "(by: " + date + " " + time + ")";
    }

    /**
     * Takes in the user input to store a certain type of task, and returns the task
     * in
     * the correct format to be stored in the Duke bot's list of tasks
     * 
     * @param taskType get the type of task user has specified
     * @param details  get the corresponding task description and time (if any)
     *                 based on
     *                 the task type
     * @return the specified task in the proper format to be stored
     * @throws Error if the user inputs the task details in the wrong format
     */
    private Task task(String taskType, String details) throws Error {
        String[] separateDetails, dateTimeDetails;
        String description, dateAndTime;
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
                description = separateDetails[0].trim();
                dateTimeDetails = separateDetails[1].trim().split(" ", 2);
                if (dateTimeDetails.length != 2) {
                    throw new Error(DEADLINE_DATETIME_ERROR);
                }
                try {
                    dateAndTime = parseDateAndTime(dateTimeDetails);
                    return new Deadline(description, dateAndTime, false);
                } catch (DateTimeParseException error) {
                    throw new Error(WRONG_DATETIME_FORMAT);
                }

            case EVENT:
                separateDetails = details.split("/at", 2);
                if (separateDetails.length != 2) {
                    throw new Error(EVENT_FORMAT_ERROR);
                } else if (separateDetails[0].equals("")) {
                    throw new Error(EVENT_DESCRIPTION_ERROR);
                }
                description = separateDetails[0].trim();
                dateTimeDetails = separateDetails[1].trim().split(" ", 2);
                if (dateTimeDetails.length != 2) {
                    throw new Error(EVENT_DATETIME_ERROR);
                }
                try {
                    dateAndTime = parseDateAndTime(dateTimeDetails);
                    return new Event(description, dateAndTime, false);
                } catch (DateTimeParseException error) {
                    throw new Error(WRONG_DATETIME_FORMAT);
                }

            default:
                throw new Error(INVALID_COMMAND);
        }
    }

    /**
     * Checks through the current task list to find if there are any
     * tasks that have the same description and/or timings
     * 
     * @param checkTask is the task the user has just created and needs
     *                  to be checked for similiarities
     * @param tasks     list of tasks to find duplicate
     * @return true if duplicate is found, else return false
     */
    private boolean checkDuplicate(Task checkTask, List<Task> tasks) {
        for (Task task : tasks) {
            if (checkTask.getDescription().equals(task.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * The function modifies the list of tasks and file contents based on the user's
     * actions
     * 
     * @param index    determines which task on the list to be modified
     * @param command  determines the action to be done on the selected task
     * @param taskList is the list of tasks to choose the task from
     * @param storage  is the class that handles loading of tasks from the file
     *                 and saving tasks in the file
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
     * Takes in a 1 word user input and handles cases where the user has sent a
     * command
     * in the wrong format
     * 
     * @param input    gets the user command
     * @param taskList is the lists of tasks to be accessed based on the user
     *                 command
     */
    public void singleCommand(String input, TaskList taskList, Ui ui) {
        switch (input) {
            case LIST:
                taskList.showList();
                break;
            case UNMARK:
                ui.unmarkErrorMessage();
                break;
            case MARK:
                ui.markErrorMessage();
                break;
            case DEADLINE:
                ui.deleteErrorMessage();
                break;
            case FIND:
                ui.missingKeywordMessage();
                break;
            default:
                ui.invalidCommandMessage();
                break;
        }

    }

    /**
     * Takes in a multi-word input from the user and handles cases where
     * the user has sent a command in the wrong format
     * 
     * @param get      the multi-word command as an array of single commands
     * @param taskList is the lists of tasks to be accessed based on the user
     *                 command
     * @param ui       is the class that deals with displaying messages to the user
     * @param storage  is the class that handles loading of tasks from the file
     *                 and saving tasks in the file
     */
    public void multipleCommands(String[] get, TaskList taskList, Ui ui, Storage storage) {
        switch (get[TASK].toLowerCase()) {
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
                } catch (IndexOutOfBoundsException error) {
                    ui.outOfBoundsMessage();
                } catch (NumberFormatException error) {
                    ui.notIntegerMessage();
                }
                break;
            case DEADLINE:
            case EVENT:
            case TODO:
                try {
                    Task task = task(get[TASK], get[DETAILS]);
                    List<Task> tasks = taskList.getTaskList();
                    if (checkDuplicate(task, tasks)) {
                        ui.duplicateDetected();
                        break;
                    }
                    taskList.addTask(task);
                    storage.appendToFile(task.fileFormat());
                } catch (Error error) {
                    System.out.println(error.getMessage());
                }
                break;
            case FIND:
                System.out.println("find details:" + "'" + get[DETAILS] + "'");
                taskList.showMatchedTasks(get[DETAILS]);
                break;
            default:
                ui.invalidCommandMessage();
        }
    }
}