package main.duke;

import main.duke.exception.DukeException;
import main.duke.task.Deadline;
import main.duke.task.Event;
import main.duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    /* Variables for Duke's use */
    private static Task[] taskList;
    private static int listIndex;
    private static Scanner scanner;

    /* Constants */
    private static final String INDENT = "\n    ";
    private static final String H_LINE = INDENT +
            "------------------------------------------------";
    private static final int TASK_LIMIT = 100;


    public static void main(String[] args) {

        //Initialize necessary variables
        init();

        //Print introduction to Duke
        introduction();

        //Load in tasks if a data file exists, and show them to the user
        loadTasksOrError();

        //Re-iterate what the user types, store in list, and unmark / mark
        while (!respondToUser()) {
        }

        //Once the user exists, save the current lists of tasks in a data file
        saveTasksOrError();

        //Salute them goodbye
        goodBye();

    }

    /* Initialize variables for Duke's use */
    private static void init() {
        scanner = new Scanner(System.in);
        taskList = new Task[TASK_LIMIT];
        listIndex = 0;
    }

    /* Print introduction to Duke */
    private static void introduction() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String introText = "\n    Hello! I'm Duke\n    What can I do for you?";
        String introduction = logo + H_LINE + introText + H_LINE;
        System.out.println(introduction);
    }

    /* Determine whether the user wants to create a task, exit, list, or mark */
    private static boolean respondToUser() {
        String input = scanner.nextLine();
        String inputType = input.toLowerCase();
        boolean shouldExit = false;

        switch (inputType) {
        case "bye":
            shouldExit = true;
            break;
        case "list":
            System.out.print(H_LINE);
            printList();
            System.out.println(H_LINE + "\n");
            break;
        default:
            //create patterns for checking both mark and unmark
            Pattern markPattern = Pattern.compile("^mark[ ]*[0-9]+[ ]*", Pattern.CASE_INSENSITIVE);
            boolean matchesMark = markPattern.matcher(input).find();
            Pattern unmarkPattern = Pattern.compile("^unmark[ ]*[0-9]+[ ]*", Pattern.CASE_INSENSITIVE);
            boolean matchesUnmark = unmarkPattern.matcher(input).find();

            //if the item is to be marked or unmarked, follow the correct steps to extract the index
            if (matchesMark || matchesUnmark) {
                markOrUnmark(matchesMark, input);

            //Otherwise, create a new task
            } else {
                try {
                    if (listIndex > TASK_LIMIT) {
                        throw new DukeException("Adding this task exceeds the task limit of " + TASK_LIMIT);
                    }
                    Task task = createTask(input);
                    System.out.println(H_LINE + INDENT
                            + "Success!" + INDENT + "Added: " + (listIndex + 1) + ". " + task + H_LINE + "\n");
                    taskList[listIndex] = task;
                    listIndex++;

                } catch (DukeException dukeException) {
                    System.out.println(H_LINE + INDENT
                            + dukeException.getMessage() + INDENT + "Task not added: "
                            + input + INDENT + "Please try again!" + H_LINE + "\n");
                }
            }
        }
        return shouldExit;
    }

    private static Task createTask(String input) throws DukeException {
        Task task;
        try {
            if (input.startsWith("deadline")) {
                task = createDeadline(input);
            } else if (input.startsWith("event")) {
                task = createEvent(input);
            } else if (input.startsWith("todo")){
                task = createTodo(input);
            } else {
                throw new DukeException("Uhoh! You haven't told me if this is a deadline, event, or todo.");
            }
        } catch (DukeException dukeException) {
            throw dukeException;
        } catch (Exception e) {
            throw new DukeException("Unknown Error: " + e.getMessage());
        }
        return task;
    }

    /* Create a new Deadline with the correct due date */
    private static Task createDeadline(String input) throws DukeException {
        int startIndex = findNextLetter("deadline", input);
        int dateIndex = input.indexOf("/by");
        if (dateIndex == -1) {
            throw new DukeException("Unable to create a Deadline! Please follow the format: deadline [task] /by [date]");
        }
        int endIndex = dateIndex + findNextLetter("/by", input.substring(dateIndex));
        return new Deadline(input.substring(startIndex, dateIndex), input.substring(endIndex));
    }

    /* Create a new Event with the correct date */
    private static Task createEvent(String input) throws DukeException {
        int startIndex = findNextLetter("event", input);
        int dateIndex = input.indexOf("/at");
        if (dateIndex == -1) {
            throw new DukeException("Unable to create an Event! Please follow the format: event [name] /at [date]");
        }
        int endIndex = dateIndex + findNextLetter("/at", input.substring(dateIndex));
        return new Event(input.substring(startIndex, dateIndex), input.substring(endIndex));
    }
    /* Create a new Todo */
    private static Task createTodo(String input) throws DukeException {
        int startIndex = findNextLetter("todo", input);
        if (startIndex >= input.length()) {
            throw new DukeException("You only wrote todo! Please follow the correct format: todo [task]");
        }
        return new Task(input.substring(startIndex));
    }

    private static int findNextLetter(String word, String input) throws DukeException {
        if (word.length() == input.length()) {
            throw new DukeException("You only wrote " + word + "! Please follow the correct format.");
        }
        int index = word.length();
        while (input.charAt(index) == ' ') {
            index++;
            if (index >= input.length()) {
                throw new DukeException("You only wrote spaces after " + word + "! Please follow the correct format.");
            }
        }
        return index;
    }

    /* Either mark or unmark a task */
    private static void markOrUnmark(boolean toMark, String input) {
        String markString = toMark ? "Mark" : "Unmark";
        System.out.print(H_LINE + INDENT + markString + "ing...");
        int markIndex = toMark ? 4 : 6;
        String number = input.substring(markIndex).replaceAll(" ", "");
        int index = Integer.valueOf(number) - 1;
        if (index >= listIndex) {
            System.out.print(INDENT + "Trying to " + markString + " an item outside of list length? Failed.");
        } else if (index < 0) {
            System.out.print(INDENT + "Trying to " + markString + " an item that is too small? Failed.");
        } else {
            if (toMark) {
                taskList[index].mark();
            } else {
                taskList[index].unmark();
            }
            System.out.print(INDENT + "Success!");
            printList();
        }
        System.out.println(H_LINE + "\n");
    }

    /* Print a list of the current tasks */
    private static void printList() {
        if (listIndex == 0) {
            System.out.print(INDENT + "Nothing to see here! Type to add to your list.");
            System.out.print(INDENT + "Here are the correct formats: "
                    + INDENT + "- todo [task]"
                    + INDENT + "- deadline [task] /by [date]"
                    + INDENT + "- event [name] /at [date]");
            return;
        }
        System.out.print(INDENT + "Here's your current task list:");
        for (int i = 0; i < listIndex; i++) {
            Task task = taskList[i];
            System.out.print(INDENT + (i + 1) + "." + task);
        }
    }

    /* Print a goodbye message from the Duke */
    private static void goodBye() {
        String goodByeText = "    Bye. Hope to see you again soon!" + H_LINE;
        System.out.print(goodByeText);
    }

    private static void loadTasksOrError() {
        try {
            loadTasks();
            System.out.print(INDENT + "Loaded tasks from data file successfully!");
            printList();
            System.out.println(H_LINE + "\n");
        } catch (DukeException dukeException) {
            System.out.println(INDENT
                    + dukeException.getMessage() + INDENT + "No tasks loaded." + H_LINE + "\n");
        }

    }

    private static void loadTasks() throws DukeException {
        if (foundTaskFile()) {
            System.out.print(H_LINE + INDENT + "Found a data file! Loading it in...");
            try {
                File taskFile = new File("./data/tasks.txt");
                Scanner scanner = new Scanner(taskFile);
                if (!scanner.hasNext()) {
                    throw new DukeException("Found an empty file!");
                }
                String firstText = scanner.nextLine();
                if (!firstText.equals("start")) {
                    throw new DukeException("File is not formatted correctly (could not find start).");
                }
                String taskString = scanner.nextLine();
                while (!taskString.equals("finish")) {
                    convertStringToTask(taskString);
                    if (listIndex >= TASK_LIMIT) {
                        throw new DukeException("File is not formatted correctly (could not find end). Or, tried to add too many tasks.");
                    }
                    taskString = scanner.nextLine();
                }
            } catch (FileNotFoundException fileException) {
                throw new DukeException("Unable to load the data text file.");
            }
        } else {
            System.out.print(H_LINE);
            throw new DukeException("No previous data found!");
        }
    }

    private static void convertStringToTask(String taskString) throws DukeException {
        Pattern taskPattern = Pattern.compile("^\\[[TDE]\\]\\[[X\\s]\\]\\s+.+$");
        boolean matchesTask = taskPattern.matcher(taskString).find();
        if (!matchesTask) {
            throw new DukeException("File is not formatted correctly (could not properly read tasks) for task: " + taskString);
        }
        Task task;
        String taskType = taskString.substring(1, 2);
        String mark = taskString.substring(4, 5);
        int descriptionIndex = "[T][ ] ".length();

        switch (taskType) {
        case "T":
            task = createTodo("todo " + taskString.substring(descriptionIndex));
            break;
        case "D":
            task = createDeadline("deadline " + taskString.substring(descriptionIndex));
            break;
        case "E":
            task = createEvent("event " + taskString.substring(descriptionIndex));
            break;
        default:
            throw new DukeException("An incorrectly formatted task was passed in to the data: " + taskString);
        }

        switch (mark) {
        case "X":
            task.mark();
            break;
        case " ":
            task.unmark();
            break;
        default:
            throw new DukeException("An incorrectly marked task was passed in to the data: " + taskString);
        }

        taskList[listIndex] = task;
        listIndex++;
    }

    private static boolean foundTaskFile() {
        File dataFolder = new File("./data");
        if (!dataFolder.exists()) {
            return false;
        }
        File taskFile = new File("./data/tasks.txt");
        if (!taskFile.exists()) {
            return false;
        }
        return true;
    }

    private static void saveTasksOrError() {
        try {
            saveTasks();
        } catch (DukeException dukeException) {
            System.out.println(H_LINE + INDENT
                    + dukeException.getMessage() + INDENT + "Unable to save tasks." + H_LINE + "\n");
        }

        System.out.println(H_LINE + INDENT + "Saved tasks to data file successfully!");
    }

    private static void saveTasks() throws DukeException {
        try {
            File taskFile = ensureExists();
            FileWriter fw = new FileWriter(taskFile);
            fw.write("start" + System.lineSeparator());
            for (int i = 0; i < listIndex; i++) {
                fw.write(taskList[i].dataString() + System.lineSeparator());
            }
            fw.write("finish");
            fw.close();
        } catch (DukeException dukeException) {
            throw dukeException;
        } catch (IOException e) {
            throw new DukeException("Unknown error with saving tasks: " + e.getMessage());
        }
    }

    private static File ensureExists() throws DukeException {

        File dataFolder = new File("./data");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        File taskFile = new File("./data/tasks.txt");
        if (!taskFile.exists()) {
            try {
                taskFile.createNewFile();
            } catch (Exception e) {
                throw new DukeException("Unable to create a data text file.");
            }
        }
        return taskFile;
    }
}
