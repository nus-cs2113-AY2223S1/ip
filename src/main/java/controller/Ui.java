package controller;

import tasks.Task;
import java.util.Scanner;
public class Ui {

    public void greetUser() {
        String line = "------------------------\n";
        System.out.println(line + "Hello! I'm Juke!\nWhat can I do for you?\n" + line);
    }

    public void printExitGreeting() {
        System.out.println("\nSee you again!");
    }

    public void printStatus(Task latest, int total, boolean isAdd){
        String verb = "added";
        if (!isAdd) {
            verb = "removed";
        }

        String line = "\t------------------------\n";
        System.out.println(line + "\tGot it. I've " + verb + " this task:\n\t" + latest);
        System.out.println("\n\tNow you have " + total + " task(s) in the list.\n" + line);
    }

    public void showLoadingError() {
        System.out.println("UI Loading error.");
    }

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
