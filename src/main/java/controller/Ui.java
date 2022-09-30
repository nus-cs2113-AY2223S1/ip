package controller;

import tasks.Task;
import java.util.Scanner;
public class Ui {

    /**
     * Prints the set greeting
     */
    public void greetUser() {
        String line = "------------------------\n";
        System.out.println(line + "Hello! I'm Juke!\nWhat can I do for you?\n" + line);
    }

    /**
     * Prints the exit message
     */
    public void printExitGreeting() {
        System.out.println("\nSee you again!");
    }

    /**
     * Prints whether a task was added or removed
     * @param latest Task that was added or removed
     * @param total count of the total number of tasks in list
     * @param isAdd whether task is being added or removed
     */
    public void printStatus(Task latest, int total, boolean isAdd){
        String verb = "added";
        if (!isAdd) {
            verb = "removed";
        }

        String line = "\t------------------------\n";
        System.out.println(line + "\tGot it. I've " + verb + " this task:\n\t" + latest);
        System.out.println("\n\tNow you have " + total + " task(s) in the list.\n" + line);
    }

    /**
     * Prints UI loading message
     */
    public void showLoadingError() {
        System.out.println("UI Loading error.");
    }

    /**
     * Prompts user for input until user enters termination keyword
     * 
     * @param parser Parser object which will process the user's line input
     */
    public void getContinuousInput(Parser parser) {
        Scanner userInputScanner = new Scanner(System.in);
        String userInput = "";
        final String EXIT_TRIGGER = "bye";

        while (!userInput.equals(EXIT_TRIGGER)) {
            // Read user input, splits it into 2 strings- the command and the remaining (if exists)
            userInput = userInputScanner.nextLine();
            String[] words = userInput.split(" ", 2);
            parser.processUserInput(words, userInput);
        }

        userInputScanner.close();
    }
    
}
