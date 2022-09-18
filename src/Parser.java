import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser extends Constants{
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
    public Task task(String taskType, String details) throws Error {
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
    public void modifyTask(int index, String command, TaskList taskList, Storage storage) {
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

    public void multipleCommands(String[] get, TaskList taskList, Ui ui, Storage storage) {
        switch (get[TASK]) { //if input more than 1
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