package duke;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Duke {
    public static final String MARK_DONE = "mark ";
    public static final String MARK_UNDONE = "unmark ";
    public static final String TODO = "todo ";
    public static final String DEADLINE = "deadline ";
    public static final String EVENT = "event ";
    public static final String LIST = "list";
    public static final String BYE = "bye";
    public static final String AT = "/at ";
    public static final String BY = "/by ";
    public static final String DELETE = "delete ";
    public static ArrayList <Task> taskList = new ArrayList<>();
    public static final java.nio.file.Path DATA_STORAGE_PATH = java.nio.file.Paths.get(System.getProperty("user.dir"), "src", "main", "java", "duke", "data.txt");

    private static void printDashLine() {
        System.out.println("__________________________________________________ \n");
    }

    private static void markDone (ArrayList <Task> taskList, String input) throws DukeException{
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARK_DONE) + MARK_DONE.length()));
        if (taskNumber < taskList.size()) {
            System.out.println("Okiii... This task has been marked as done");
            taskList.get(taskNumber).markAsDone();
            System.out.println((taskList.get(taskNumber)).description);
        } else {
            throw new DukeException();
        }
        printDashLine();
    }

    private static void markUnDone (ArrayList <Task> taskList, String input) throws DukeException{
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARK_UNDONE) + MARK_UNDONE.length()));
        if (taskNumber < taskList.size()) {
            System.out.println("Okiii... This task has been marked as not done yet");
            taskList.get(taskNumber).markAsUndone();
            System.out.println((taskList.get(taskNumber)).description);
        } else {
            throw new DukeException();
        }

        printDashLine();
    }

    private static void listTasks (ArrayList <Task> taskList){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(Integer.toString(i + 1) + " " + (taskList.get(i)).toString());
        }
        printDashLine();
    }

    public static void main(String[] args) throws IOException {
        String logo = "  ____                 _     \n" +
                " |  _ \\ ___  __ _  ___| |__  \n" +
                " | |_) / _ \\/ _` |/ __| '_ \\ \n" +
                " |  __/  __/ (_| | (__| | | |\n" +
                " |_|   \\___|\\__,_|\\___|_| |_|\n" +
                "                             ";
        System.out.println(logo);

        String intro = "__________________________________________________ \n"
                + "  Hello! I'm Peach  \n"
                + "  What can I do for you? \n"
                + "__________________________________________________ \n";

        System.out.println(intro);

        if (!(java.nio.file.Files.exists(DATA_STORAGE_PATH))){
            File file = new File (DATA_STORAGE_PATH.toString());
            file.getParentFile().mkdirs();
            System.out.println("Data Storage File successfully created \n");
        }

        try {
            Storage.loadTasks();
        } catch (FileNotFoundException e){
            System.out.println ("File is not found!");
        }

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (input.equals(BYE) == false) {
            printDashLine();
            if (input.equals(LIST)) {
                listTasks(taskList);
            } else if (input.contains(MARK_UNDONE)) {
                try {
                    markUnDone(taskList, input);
                }
                catch (DukeException e)
                {
                    System.out.println("The task number is out of bound and therefore cannot be marked undone!");
                }
            } else if (input.contains(MARK_DONE)) {
                try {
                    markDone(taskList, input);
                } catch (DukeException e) {
                    System.out.println("The task number is out of bound and therefore cannot be marked done!");
                }
            }else if (input.contains(DELETE)) {
                    try {
                        deleteTask(taskList, input);
                    }
                    catch (DukeException e)
                    {
                        System.out.println("The task number is out of bound and therefore cannot be deleted!");
                    }
            } else if (input.contains(TODO)) {
                try {
                    addTodo(taskList, input);
                }
                catch (DukeException e)
                {
                    System.out.println("The todo input is not valid! Might be missing description!");
                }
            }
            else if (input.contains(DEADLINE)) {
                try {
                    addDeadline(taskList, input);
                }
                catch (DukeException e)
                {
                    System.out.println("The deadline input is not valid! Might be missing description, '/by' or deadline!");
                }
            }
            else if (input.contains(EVENT)) {
                try{
                    addEvent(taskList, input);
                }
                catch (DukeException e){
                    System.out.println("The event input is not valid! Might be missing description, '/at' or time !");
                }
            }
            else {
                System.out.println("Oops... cannot recognize the input command !");
            }
            input = in.nextLine();
        }
        Storage.saveTasks();
        System.out.println("Bye. Hope to see you again soon! \n");

    }
    private static void addEvent(ArrayList <Task> taskList, String input) throws DukeException{
        String task = input.substring(EVENT.length(), input.indexOf(AT));
        String time = input.substring(input.indexOf(AT) + AT.length());

        if (task.equals("") || (time.equals(""))){
            throw new DukeException();
        }
        else{
            taskList.add(new Event(task, time));
            System.out.println("Got it. I have added this task:");
            System.out.println("Now you have " + taskList.size() + " tasks left");
            printDashLine();
        }


    }

    private static void addDeadline(ArrayList <Task> taskList, String input) throws DukeException {
        String task = input.substring(DEADLINE.length(), input.indexOf(BY));
        String deadline = input.substring(input.indexOf(BY) + BY.length());

        if (task.equals("") || deadline.equals("")){
            throw new DukeException();
        }

        else{
            taskList.add(new Deadline(task, deadline));
            System.out.println("Got it. I have added this task:");
            System.out.println("Now you have " + taskList.size() + " tasks left");
            printDashLine();

        }

    }

    private static void addTodo(ArrayList <Task> taskList, String input) throws DukeException{
        String task = input.substring(TODO.length());
        if (task.equals("")){
            throw new DukeException();
        }
        else {
            taskList.add(new Todo(task));
            System.out.println("Got it. I have added this task:");
            System.out.println("Now you have " + taskList.size() + " tasks left");
            printDashLine();
        }

    }

    private static void deleteTask (ArrayList <Task> taskList, String input) throws DukeException{
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(DELETE) + DELETE.length()));
        if (taskNumber < taskList.size()) {
            System.out.println("Okiii... This task has been deleted: ");
            System.out.println((taskList.get(taskNumber)).description);
            taskList.remove(taskNumber);
            System.out.println("Now you have " + taskList.size() + " tasks left");
        } else {
            throw new DukeException();
        }
        printDashLine();
    }

}



