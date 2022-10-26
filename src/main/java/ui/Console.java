package ui;

import data.Storage;
import exceptions.InvalidTaskNumberException;
import tasks.*;
import static ui.Messages.*;

import java.util.Scanner;

/**
 * Starts the parser and continuously takes in user input until Duke is terminated.
 */
public class Console {
    private static boolean isExit = false;

    /**
     * Terminates runDuke and the Duke application by flagging the boolean isExit.
     */
    public static void exitDuke() {
        byeMessage();
        isExit = true;
        System.exit(0);
    }

    TaskList taskList = new TaskList();

    /**
     * Reads command name from user input by parsing the input
     */
    public void runCommand(Parser parser) throws InvalidTaskNumberException {
        String command = parser.getCommand();
        switch (command) {
            case "bye":
                Console.exitDuke();
                break;
            case "help":
                helpMessage();
                break;
            case "list":
                taskList.printList();
                break;
            case "todo":
            case "deadline":
            case "event":
                taskList.addTask(parser.toTask());
                break;
            case "mark":
                taskList.markTask(parser.getTaskNumber());
                break;
            case "unmark":
                taskList.unmarkTask(parser.getTaskNumber());
                break;
            case "delete":
                taskList.deleteTask(parser.getTaskNumber());
                break;
            case "save":
                Storage.saveToFile(taskList);
                break;
        }
    }

    public void runDuke() {
        try (Scanner userInput = new Scanner(System.in)) {
            while (!isExit) {
                String readInput;
                while ((readInput = userInput.nextLine()) != null) {
                    Parser parser = new Parser(readInput);
                    try {
                        parser.parseCommand();
                        runCommand(parser);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}



