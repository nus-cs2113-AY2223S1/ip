import duke.exception.AbsentArgsFlagException;
import duke.exception.DukeException;
import duke.exception.IllegalArgsNumException;
import duke.exception.IllegalArgsTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.nio.Buffer;
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        showLogo();
        showWelcomeMsg();
        readUserCmd();
        showGoodbyeMsg();
    }



    private static final String END_CMD = "bye";

    private static void readUserCmd() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals(END_CMD)) {
            String[] args = input.split(" ", 2);
            String arg1 = args[0];
            String arg2 = "";
            if (args.length > 1) {
                arg2 = args[1];
            }
            switch (arg1) {
            case "list":
                listTask();
                break;
            case "mark":
                markTask(Integer.parseInt(arg2),
                        true);
                break;
            case "unmark":
                markTask(Integer.parseInt(arg2),
                        false);
                break;
            case "todo":
                try {
                    addTodo(arg2);
                } catch (DukeException e) {
                    handleAddTodoException();
                }
                break;
            case "deadline":
                try {
                    addDeadline(arg2);
                } catch (DukeException e) {
                    handleAddDeadException(e);
                }
                break;
            case "event":
                try {
                    addEvent(arg2);
                } catch (DukeException e) {
                    handleAddEventException(e);
                }
                break;
            case "delete":
                deleteTask(Integer.valueOf(arg2));
                break;
            default:
                try {
                    addTask(input);
                } catch (IllegalArgsTypeException e) {
                    handleAddTaskException();
                }
            }
            input = sc.nextLine();
        }
    }


    private static void handleAddTaskException() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        showSeparator();

    }
    private static void handleAddTodoException() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
        showSeparator();
    }

    private static void handleAddDeadException(DukeException e) {
        if (e instanceof IllegalArgsNumException) {
            System.out.println("OOPS!!! The description of a DDL cannot be empty.");
        }
        else if (e instanceof AbsentArgsFlagException) {
            System.out.println("OOPS!!! The description of a DDL should contain /by.");
        }
        showSeparator();
    }

    private static void handleAddEventException(DukeException e) {
        if (e instanceof IllegalArgsNumException) {
            System.out.println("OOPS!!! The description of a event cannot be empty.");
        }
        else if (e instanceof AbsentArgsFlagException) {
            System.out.println("OOPS!!! The description of a event should contain /at.");
        }
        showSeparator();
    }
}
