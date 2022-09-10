package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CommandHandler {
    private static Task[] tasks = new Task[100];
    private static int taskCounter = 0;
    public static void drawLine() {    //print underscore symbol 50 times
        System.out.println("__________________________________________________");
    }
    public static void byeUser() {
        System.out.println("Bye. Hope to see you again!");
        drawLine();
    }
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; ++i) {
            char taskTypeCharacter = tasks[i].getType();
            System.out.println(i+1 + ".[" + taskTypeCharacter + "]" +
                    "[" + (tasks[i].isDone() ? "X] " : " ] ")
                    + tasks[i]);
        }
        drawLine();
    }
    public static int getTaskID(String command) {
        command = command.trim();
        String[] words = command.split(" ");
        int taskID = Integer.parseInt(words[1]);
        return taskID;
    }
    public static void unmarkTask(int taskID) throws DukeException {
        if (tasks[taskID - 1].isDone() == false) {
            throw new DukeException(":( OOPS!!! Unable to unmark as this task has not been done yet");
        }
        tasks[taskID-1].setDone(false);
        System.out.println("Okay, I've marked this task as not done yet:");
        char taskTypeCharacter = tasks[taskID-1].getType();
        System.out.println("[" + taskTypeCharacter + "]"+ "[ ] " + tasks[taskID-1]);
        drawLine();
    }

    public static void markTask(int taskID) throws DukeException{
        if (tasks[taskID - 1].isDone() == true) {
            throw new DukeException(":( OOPS!!! Unable to mark as this task has already been done");
        }
        tasks[taskID-1].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        char taskTypeCharacter = tasks[taskID-1].getType();
        System.out.println("[" + taskTypeCharacter + "]" + "[X] " + tasks[taskID-1]);
        drawLine();
    }
    public static void createNewTask(String command) {
        command = command.trim();
        char taskType = command.toUpperCase().charAt(0);    //first char is the type in uppercase
        try {
            command = removeTaskType(command);   //get taskName from command
        } catch (DukeException e) {
            e.printErrorMessage();
            drawLine();
            return;
        }
        String taskDateTime, taskName;
        switch(taskType) {
        case 'T':
            createNewToDo(new ToDo(command, false, taskType));
            break;
        case 'D':
            try {
                taskDateTime = getTaskDateTime(command);
                taskName = getTaskName(command);
                createNewDeadline(new Deadline(taskName, false, taskType, taskDateTime));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(":( OOPS!!! Deadline tasks should be of the form 'deadline <task_name> /by <task_deadline>'");
                drawLine();
            }
            break;
        case 'E':
            try {
                taskDateTime = getTaskDateTime(command);
                taskName = getTaskName(command);
                createNewEvent(new Event(taskName, false, taskType, taskDateTime));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(":( OOPS!!! Event tasks should be of the form 'event <task_name> /at <task_time>'");
                drawLine();
            }
            break;
        default:
            drawLine();
        }
    }
    public static String removeTaskType(String command) throws DukeException{
        int indexOfFirstBlankSpace = command.indexOf(" ");
        String firstWord;
        if (indexOfFirstBlankSpace == -1) {
            firstWord = command;
        } else {
            firstWord = command.substring(0, indexOfFirstBlankSpace);
        }
        if (indexOfFirstBlankSpace == -1 && (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event"))) {
            throw new DukeException(":( OOPS!!! The description of a " + command + " cannot be empty.");
        } else if (!firstWord.equals("todo") && !firstWord.equals("deadline") && !firstWord.equals("event")) {
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        command = command.substring(indexOfFirstBlankSpace + 1);
        return command;
    }
    private static String getTaskName(String command) {
        int indexOfBackslash = command.indexOf('/');
        return command.substring(0, indexOfBackslash-1);
    }
    private static String getTaskDateTime(String command) {
        int indexOfBackslash = command.indexOf('/');
        command = command.substring(indexOfBackslash + 1);
        int indexOfFirstBlankspace = command.indexOf(' ');
        return command.substring(indexOfFirstBlankspace + 1);
    }

    private static void createNewToDo(ToDo toDo) {
        tasks[taskCounter] = toDo;
        taskCounter++;
        printAddedTask(toDo);
    }
    private static void createNewDeadline(Deadline deadline) {
        tasks[taskCounter] = deadline;
        taskCounter++;
        printAddedTask(deadline);
    }
    private static void createNewEvent(Event event) {
        tasks[taskCounter] = event;
        taskCounter++;
        printAddedTask(event);
    }

    private static void printAddedTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + task.getType() + "]" + "[ ] " + task);
        System.out.println("Now you have " + taskCounter + " tasks in your list.");
        drawLine();
    }

    public static void executeUserCommands(DukeFile df) {
        loadTasks(df);
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = in.nextLine();
            drawLine();
            if (command.equals("bye")) {
                byeUser();
                break;
            }
            else if (command.equals("list")) {
                listTasks();
            }
            else if (command.contains("unmark")) {
                int taskID = getTaskID(command);
                try {
                    unmarkTask(taskID);
                } catch (DukeException e) {
                    e.printErrorMessage();
                    drawLine();
                } catch (NullPointerException e) {
                    System.out.println(":( OOPS!!! This task does not exist.");
                    drawLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(":( OOPS!!! This task does not exist.");
                    drawLine();
                }
            }
            else if (command.contains("mark")) {
                int taskID = getTaskID(command);
                try {
                    markTask(taskID);
                } catch (DukeException e) {
                    e.printErrorMessage();
                    drawLine();
                } catch (NullPointerException e) {
                    System.out.println(":( OOPS!!! This task does not exist.");
                    drawLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(":( OOPS!!! This task does not exist.");
                    drawLine();
                }
            }
            else {
                createNewTask(command);
            }
            try {
                df.saveFile(df.getFile(), getTaskList());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } while (command != "bye");
    }

    private static String getTaskList() {
        String taskList = "";
        for (int i = 0; i < taskCounter; ++i) {
            char taskType = tasks[i].getType();
            taskList += tasks[i].getType() + " | ";
            taskList += (tasks[i].isDone() ? "1" : "0") + " | ";
            switch(taskType) {
            case 'T':
                taskList += tasks[i].getName() + "\n";
                break;
            case 'D':
            case 'E':
                taskList += tasks[i].getName() + " | " + tasks[i].getDateTime() + "\n";
                break;
            default:
            }
        }
        return taskList;
    }
    private static void loadTasks(DukeFile df) {
        try {
            String taskList = df.loadFile(df.getFile());
            while(taskList.length() > 0 && taskList.charAt(taskList.length()-1) == '/') {
                String taskName, taskDateTime;
                int ind_pipe;
                int ind_backslash = taskList.indexOf("/");
                String task = taskList.substring(0, ind_backslash);
                taskList = taskList.substring(ind_backslash + 1);
                char taskType = task.charAt(0);
                boolean isDone = task.charAt(4) == '1';
                switch(taskType) {
                case 'T':
                    taskName = task.substring(8);
                    tasks[taskCounter] = new ToDo(taskName, isDone, taskType);
                    taskCounter++;
                    break;
                case 'D':
                    task = task.substring(8);
                    ind_pipe = task.indexOf("|");
                    taskName = task.substring(0, ind_pipe - 1);
                    taskDateTime = task.substring(ind_pipe + 2);
                    tasks[taskCounter] = new Deadline(taskName, isDone, taskType, taskDateTime);
                    taskCounter++;
                    break;
                case 'E':
                    task = task.substring(8);
                    ind_pipe = task.indexOf("|");
                    taskName = task.substring(0, ind_pipe - 1);
                    taskDateTime = task.substring(ind_pipe + 2);
                    tasks[taskCounter] = new Event(taskName, isDone, taskType, taskDateTime);
                    taskCounter++;
                    break;
                default:
                    System.out.println("File is incompatible, cannot be read");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

