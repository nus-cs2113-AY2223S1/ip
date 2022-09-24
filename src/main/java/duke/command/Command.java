package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Command {
    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printNewTask(Task task, int tasksSize) {
        printHorizontalLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + tasksSize + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        printHorizontalLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        printHorizontalLine();
    }

    public static void printMark(Task task) {
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        printHorizontalLine();
    }

    public static void printUnmark(Task task) {
        printHorizontalLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        printHorizontalLine();
    }



    public static void readFile(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int numberOfLine = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parsedLine = line.split(" \\| ");
            numberOfLine += 1;
            switch (parsedLine[0]) {
            case "T":
                tryAddTodo(tasks, "todo " + parsedLine[2]);
                break;
            case "D":
                String deadline = "deadline " + parsedLine[2] + " /by " + parsedLine[3];
                tryAddDeadline(tasks, deadline);
                break;
            case "E":
                String event = "event " + parsedLine[2] + " /at " + parsedLine[3];
                tryAddEvent(tasks, event);
                break;
            }
            switch (parsedLine[1]) {
            case "1":
                String mark = "mark " + numberOfLine;
                tryMarkTask(tasks, mark);
                break;
            case "0":
                String unmark = "unmark " + numberOfLine;
                tryUnmarkTask(tasks, unmark);
                break;
            }
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            String line = "";
            switch (tasks.get(i).getClass().getSimpleName()) {
            case "Todo":
                line = line + "T | ";
                break;
            case "Deadline":
                line = line + "D | ";
                break;
            case "Event":
                line = line + "E | ";
                break;
            }
            switch (tasks.get(i).getStatusIcon()) {
            case "X":
                line = line + "1 | ";
                break;
            case " ":
                line = line + "0 | ";
                break;
            }
            line = line + tasks.get(i).getDescription();
            switch (tasks.get(i).getClass().getSimpleName()) {
            case "Deadline":
                Deadline d = (Deadline) tasks.get(i);
                line = line + " | " + d.getBy();
                break;
            case "Event":
                Event e = (Event) tasks.get(i);
                line = line + " | " + e.getAt();
                break;
            }
            line = line + System.lineSeparator();
            fw.write(line);
        }
        fw.close();

    }

    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printHorizontalLine();
    }

    public static void printByeMessage() {
        printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private boolean exit = false;

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    public boolean isExit() {
        return(exit);
    }
}
