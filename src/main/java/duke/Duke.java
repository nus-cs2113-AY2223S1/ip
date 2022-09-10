package duke;

import duke.exception.TodoNoDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.text.ErrorText;
import duke.text.InfoText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String DASH = "    ____________________________________________________________";

    public static void printGreeting() {
        String logo = "    *******     *******   *******       **\n"
                + "    /**////**   **/////** /**////**     ****\n"
                + "    /**    /** **     //**/**   /**    **//**\n"
                + "    /**    /**/**      /**/*******    **  //**\n"
                + "    /**    /**/**      /**/**///**   **********\n"
                + "    /**    ** //**     ** /**  //** /**//////**\n"
                + "    /*******   //*******  /**   //**/**     /**\n"
                + "    ///////     ///////   //     // //      //\n";

        System.out.println(String.format("%s\n%s\n%s\n%s", DASH, logo, InfoText.INFO_WELCOME, DASH));
    }

    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            for (int i = 0; i < tasks.size(); i++) {
                String taskType = Character.toString(tasks.get(i).getTaskDetails().charAt(1));
                String taskStatus = tasks.get(i).getStatusIcon().equals("X") ? "1" : "0";
                String taskDescription = tasks.get(i).getDescription();
                String textToWrite = String.format("%s | %s | %s", taskType, taskStatus, taskDescription);
                if (taskType.equals("D")) {
                    textToWrite += String.format(" | %s", tasks.get(i).getDueBy());
                } else if (taskType.equals("E")) {
                    textToWrite += String.format(" | %s", tasks.get(i).getEventTime());
                }
                fw.write(textToWrite + System.lineSeparator());
            }
            fw.close();
        } catch(IOException e) {
            System.out.println(String.format("%s\n%s %s\n%s", DASH, ErrorText.ERROR_FILE_IO, e.getMessage(), DASH));
        }
    }

    public static void markDone(ArrayList<Task> tasks, String input, boolean isDone) throws IOException {
        System.out.println(DASH);
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            tasks.get(taskIndex).setStatus(isDone);
            if (isDone) {
                System.out.println(String.format("%s", InfoText.INFO_TASK_MARKED));
            } else {
                System.out.println(String.format("%s", InfoText.INFO_TASK_UNMARKED));
            }
            System.out.println(String.format("    * %s", tasks.get(taskIndex).getTaskDetails()));
            writeToFile(tasks);
        } catch (NumberFormatException e) {
            System.out.println(String.format("%s", ErrorText.ERROR_INVALID_STATUS_FORMAT));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format("%s", ErrorText.ERROR_INVALID_TASK_INDEX));
        }
        System.out.println(DASH);
    }

    public static void printNewTask(String taskDetails, int numberOfTasks) {
        System.out.println(DASH);
        System.out.println(String.format("%s", InfoText.INFO_TASK_ADDED));
        System.out.println(String.format("    * %s", taskDetails));
        System.out.println(String.format("%s", String.format(String.valueOf(InfoText.INFO_TASK_COUNT), numberOfTasks)));
        System.out.println(DASH);
    }

    public static void checkTodoFormat(String input) throws TodoNoDescriptionException {
        if (input.equals("todo") || input.isBlank()) {
            throw new TodoNoDescriptionException();
        }
    }

    public static void main(String[] args) throws TodoNoDescriptionException, IOException {
        printGreeting();
        boolean isLoop = true;
        Scanner in = new Scanner(System.in);
        String command, input;
        String[] inputSplits;
        ArrayList<Task> tasks = new ArrayList<>();

        //@@author chydarren-reused
        // Reused from https://stackoverflow.com/a/38985883
        // with minor modifications
        File directory = new File("data");
        if(!directory.exists()) {
            directory.mkdir();
        }

        File file = new File("data/data.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        //@@author

        while (isLoop) {
            input = in.nextLine();
            if (input.contains(" ")) {
                command = input.split(" ")[0];
                input = input.substring(input.indexOf(' ') + 1);
            } else {
                command = input;
            }

            switch (command) {
            case "bye":
                System.out.println(String.format("%s\n%s\n%s", DASH, InfoText.INFO_BYE, DASH));
                isLoop = false;
                break;
            case "list":
                System.out.println(DASH);
                System.out.println(String.format("%s", InfoText.INFO_LIST));
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + "." + tasks.get(i).getTaskDetails());
                }
                System.out.println(DASH);
                break;
            case "mark":
                markDone(tasks, input, true);
                break;
            case "unmark":
                markDone(tasks, input, false);
                break;
            case "todo":
                try {
                    checkTodoFormat(input);
                    Todo todo = new Todo(input);
                    tasks.add(todo);
                    printNewTask(todo.getTaskDetails(), tasks.size());
                    writeToFile(tasks);
                } catch (TodoNoDescriptionException e) {
                    System.out.println(String.format("%s\n%s\n%s", DASH, ErrorText.ERROR_INVALID_TODO_FORMAT, DASH));
                }
                break;
            case "deadline":
                try {
                    inputSplits = input.split(" /by ");
                    Deadline deadline = new Deadline(inputSplits[0], inputSplits[1]);
                    tasks.add(deadline);
                    printNewTask(deadline.getTaskDetails(), tasks.size());
                    writeToFile(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(String.format("%s\n%s\n%s", DASH, ErrorText.ERROR_INVALID_DEADLINE_FORMAT, DASH));
                }
                break;
            case "event":
                try {
                    inputSplits = input.split(" /at ");
                    Event event = new Event(inputSplits[0], inputSplits[1]);
                    tasks.add(event);
                    printNewTask(event.getTaskDetails(), tasks.size());
                    writeToFile(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(String.format("%s\n%s\n%s", DASH, ErrorText.ERROR_INVALID_EVENT_FORMAT, DASH));
                }
                break;
            case "delete":
                System.out.println(DASH);
                try {
                    int taskIndex = Integer.parseInt(input) - 1;
                    String taskDetails = tasks.get(taskIndex).getTaskDetails();
                    tasks.remove(taskIndex);
                    System.out.println(String.format("%s", InfoText.INFO_TASK_DELETED));
                    System.out.println(String.format("    * %s", taskDetails));
                    System.out.println(String.format("%s", String.format(String.valueOf(InfoText.INFO_TASK_COUNT), tasks.size())));
                } catch (NumberFormatException e) {
                    System.out.println(String.format("%s", ErrorText.ERROR_INVALID_DELETE_FORMAT));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(String.format("%s", ErrorText.ERROR_INVALID_TASK_INDEX));
                }
                System.out.println(DASH);
                break;
            default:
                System.out.println(String.format("%s\n%s\n%s", DASH, ErrorText.ERROR_INVALID_COMMAND, DASH));
                break;
            }
        }
    }
}