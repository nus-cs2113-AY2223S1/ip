import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String LINE_BREAK = "______________________________________________\n";
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";

    private static final String TASK_ATTRIBUTE_DELIMITER = " \\| ";
    private static final String DUKE_DATA_FILE_PATH = "data/duke.txt";
    private static final int TASK_START_INDEX = 1;
    private static final int RIGHT_SHIFT_INDEX = 1;

    public static void createDukeFile(File dukeFile) throws IOException {
        dukeFile.getParentFile().mkdir();
        dukeFile.createNewFile();
    }
    public static void retrieveFileData(File dukeFile) throws FileNotFoundException, InvalidTaskFormatException {
        Scanner scanner = new Scanner(dukeFile);
        while (scanner.hasNext()) {
            String taskData = scanner.nextLine();
            String[] taskAttributes = taskData.split(TASK_ATTRIBUTE_DELIMITER);
            String taskType = taskAttributes[0];
            String taskName = taskAttributes[2];
            boolean isTaskCompleted = Boolean.valueOf(taskAttributes[1]);
            switch (taskType) {
            case "T":
                ToDo toDo = new ToDo(taskName);
                toDo.setIsCompleted(isTaskCompleted);
                tasks.add(toDo);
                break;
            case "D":
                String dueDate = taskAttributes[3];
                Deadline deadline = new Deadline(taskName, dueDate);
                deadline.setIsCompleted(isTaskCompleted);
                tasks.add(deadline);
                break;
            case "E":
                String timeOfEvent = taskAttributes[3];
                Event event = new Event(taskName, timeOfEvent);
                event.setIsCompleted(isTaskCompleted);
                tasks.add(event);
                break;
            default:
                throw new InvalidTaskFormatException();
            }
        }
    }
    public static void accessDukeFile() {
        File dukeFile = new File(DUKE_DATA_FILE_PATH);
        if (dukeFile.exists()) {
            try {
                retrieveFileData(dukeFile);
            } catch (FileNotFoundException e) {
                System.out.println("Duke data file cannot be accessed");
            } catch (InvalidTaskFormatException e) {
                System.out.println("Invalid task found in Duke file. Task is omitted from the list.");
            }
        } else {
            try {
                createDukeFile(dukeFile);
            } catch (IOException e) {
                System.out.println("Duke file creation failed.");
                System.out.println(e.getMessage());
            }
        }
    }
    public static void writeTasksToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(DUKE_DATA_FILE_PATH);
        for (Task task : tasks) {
            if (task instanceof ToDo) {
                ToDo toDo = (ToDo) task;
                fileWriter.write(toDo.getFileInput());
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                fileWriter.write(deadline.getFileInput());
            } else if (task instanceof Event) {
                Event event = (Event) task;
                fileWriter.write(event.getFileInput());
            }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
    public static void saveTasksData() {
        try {
            writeTasksToFile();
        } catch (IOException e) {
            System.out.println("Unable to write task to file: " + e.getMessage());
        }
    }

    public static void printList() {
        System.out.println(LINE_BREAK);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println( Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINE_BREAK);
    }

    public static void printMarkedTask(Task markedTask) {
        System.out.println(LINE_BREAK + "Nice! I've marked this task as done: ");
        System.out.println("  " + markedTask.toString());
        System.out.println();
    }

    public static void printUnmarkedTask(Task unmarkedTask) {
        System.out.println(LINE_BREAK + "OK, I've marked this task as not done yet: ");
        System.out.println("  " + unmarkedTask.toString());
        System.out.println();
    }

    public static void printDeletedTask(Task deletedTask) {
        System.out.println(LINE_BREAK + "Noted. I've removed this task: ");
        System.out.println("  " + deletedTask.toString());
        String numberOfTasksAfterDeletion = Integer.toString(tasks.size()-1);
        System.out.println("Now you have " + numberOfTasksAfterDeletion + " tasks in the list \n" + LINE_BREAK);
    }

    public static void printEmptyDescription() {
        System.out.println(LINE_BREAK + "Oh No! The description of an event cannot be empty.\n" + LINE_BREAK);
    }
    public static void printAddedTask(Task task) {
        System.out.println(LINE_BREAK + "Got it. I've added this task: ");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + Integer.toString(tasks.size()) + " tasks in the list \n" + LINE_BREAK);
    }

    public static ToDo getToDo(String[] inputWords) throws NoDescriptionException {
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        String[] taskNameWords = Arrays.copyOfRange(inputWords, TASK_START_INDEX, inputWords.length);
        String taskName = String.join(" ", taskNameWords);
        ToDo toDo = new ToDo(taskName);
        return toDo;
    }

    public static void checkForTaskExceptions(String[] inputWords, String delimiter) throws NoDescriptionException, InvalidTaskFormatException {
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (!Arrays.asList(inputWords).contains(delimiter)) {
            throw new InvalidTaskFormatException();
        }
    }
    public static int getIndexOfDelimiter(String[] inputWords, String delimiter) {
        int index = Arrays.asList(inputWords).indexOf(delimiter);
        return index;
    }

    public static String getTaskName(String[] inputWords, int index) {
        String[] taskNameArray = Arrays.copyOfRange(inputWords, TASK_START_INDEX, index);
        String taskName = String.join(" ", taskNameArray);
        return taskName;
    }

    public static String getTaskDate(String[] inputWords, int index) {

        String[] taskDateArray = Arrays.copyOfRange(inputWords, index + RIGHT_SHIFT_INDEX, inputWords.length);
        String taskDate = String.join(" ", taskDateArray);
        return taskDate;
    }
    public static Deadline getDeadline(String[] inputWords) throws NoDescriptionException, InvalidTaskFormatException {
        int index = getIndexOfDelimiter(inputWords, DEADLINE_DELIMITER);
        String deadlineName = getTaskName(inputWords, index);
        String dueDate = getTaskDate(inputWords, index);
        Deadline deadline = new Deadline(deadlineName, dueDate);
        return deadline;
    }

    public static Event getEvent(String[] inputWords) throws NoDescriptionException, InvalidTaskFormatException {
        int index = getIndexOfDelimiter(inputWords, EVENT_DELIMITER);
        String eventName = getTaskName(inputWords, index);
        String timeOfEvent = getTaskDate(inputWords, index);
        Event event = new Event(eventName, timeOfEvent);
        return event;
    }

    public static void printInvalidDeadline() {
        System.out.println(LINE_BREAK + "Invalid Deadline. Please provide due date!\n" + LINE_BREAK);
    }

    public static void printInvalidEvent() {
        System.out.println(LINE_BREAK + "Invalid Event. Please provide event time!\n" + LINE_BREAK);
    }

    public static void markTask(String[] inputWords) {
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task markedTask = tasks.get(listIndex);
        markedTask.setIsCompleted(true);
        printMarkedTask(markedTask);
    }

    public static void unmarkTask(String[] inputWords){
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task unmarkedTask = tasks.get(listIndex);
        unmarkedTask.setIsCompleted(false);
        printUnmarkedTask(unmarkedTask);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        printAddedTask(task);
    }
    public static void addDeadline(String[] inputWords) {
         try {
             checkForTaskExceptions(inputWords, DEADLINE_DELIMITER);
             Deadline deadline = getDeadline(inputWords);
             addTask(deadline);
         } catch (NoDescriptionException e) {
             printEmptyDescription();
         } catch (InvalidTaskFormatException e) {
             printInvalidDeadline();
         }
    }

    public static void addEvent(String[] inputWords) {
        try {
            checkForTaskExceptions(inputWords, EVENT_DELIMITER);
            Event event = getEvent(inputWords);
            addTask(event);
        } catch (NoDescriptionException e) {
            printEmptyDescription();
        } catch (InvalidTaskFormatException e) {
            printInvalidEvent();
        }
    }

    public static void addToDo(String[] inputWords){
        try {
            ToDo toDo = getToDo(inputWords);
            tasks.add(toDo);
            printAddedTask(toDo);
        } catch (NoDescriptionException e) {
            System.out.println(LINE_BREAK + "Oh No! The description of a todo cannot be empty.\n" + LINE_BREAK);
        }
    }

    public static void printInvalidCommand() {
        System.out.println(LINE_BREAK + "Oh no! I do not understand the command! \n" + LINE_BREAK);
    }

    public static void deleteTask(String[] inputWords) {
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task deletedTask = tasks.get(listIndex);
        printDeletedTask(deletedTask);
        tasks.remove(listIndex);
    }
    public static void main(String[] args) {
        accessDukeFile();
        System.out.println(LINE_BREAK + " Hello! I'm Duke\n" +
                " What can I do for you?\n" + LINE_BREAK);
        String lineInput;
        Scanner in = new Scanner(System.in);
        lineInput = in.nextLine();

        while (!lineInput.equalsIgnoreCase("bye")) {
            String[] inputWords = lineInput.split("\\s+");
            String action = inputWords[0];

            if (action.equalsIgnoreCase("list")) {
                printList();
            } else if (action.equalsIgnoreCase("mark")) {
                markTask(inputWords);
            } else if (action.equalsIgnoreCase("unmark")) {
                unmarkTask(inputWords);
            } else if (action.equalsIgnoreCase("todo")) {
                addToDo(inputWords);
            } else if (action.equalsIgnoreCase("event")) {
                addEvent(inputWords);
            } else if (action.equalsIgnoreCase("deadline")){
                addDeadline(inputWords);
            } else if (action.equalsIgnoreCase("delete")) {
                deleteTask(inputWords);
            } else {
                printInvalidCommand();
            }
            saveTasksData();
            lineInput = in.nextLine();
        }
        System.out.println(LINE_BREAK + "Bye. Hope to see see you again soon!" + System.lineSeparator() + LINE_BREAK);
    }
}
