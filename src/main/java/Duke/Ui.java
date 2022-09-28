package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class deals with interactions with the users, including reading of inputs and displaying various outputs.
 */
public class Ui {



    /**
     * Prints a line to separate outputs
     */
    public static void drawLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Greets the user with welcome message
     */
    public static void sayHi() {
        String tommyLogo =  " _____ ____  _______  _______  __ __  \n"
                +   "  | | |   | || || || || || ||  |_ _|  \n"
                +   "  | | |   | || || || || || ||   | |  \n"
                +   "  |_| |___| || || || || || ||   | |  \n";
        System.out.println(" Hello! I'm\n" + tommyLogo + "\n What can I do for you?\n");
        drawLine();
    }

    /**
     * Displays shutdown message to user
     */
    public static void sayBye() {
        System.out.println(" Bye. Hope to see you again soon!\n");
        drawLine();
    }

    /**
     * Reads user input from command line and returns it
     * @return User input
     */
    public static String readInputs() {
        Scanner command = new Scanner(System.in);
        String input = command.nextLine();
        return input;
    }

    /**
     * Displays specified errors
     */
    public static void showError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public static void showEmptyToDo() {
        drawLine();
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        drawLine();
    }
    public static void showIllegalCommand() {
        drawLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        drawLine();
    }

    public static void showFileWriteError() {
        drawLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I was unable to write to the file :-(");
        drawLine();
    }

}
