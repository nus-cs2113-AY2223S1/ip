package duke;


import duke.file.FileOperation;
import duke.taskings.Task;
import duke.taskings.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {


    /**
     * Returns the number of words within a string.
     *
     * @param input the string input from the user
     * @return the number of words in the string
     */

    public static int getNumOfWords(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        String[] words = input.split("\\s+");
        return words.length;
    }


    public static void main(String[] args) {

        // array storing the Task objects.
        ArrayList<Task> tasks = new ArrayList<Task>();

        //stores the number of words in the user's string input.
        int numOfWords;


        String inData;
        Scanner scan = new Scanner(System.in);
        Message.greet();
        FileOperation.initialiseFile(tasks);
        Message.displayLineDivider();

        while (true) {
            // continuously receive user input
            String command = "";
            inData = scan.nextLine();
            inData = inData.trim();
            numOfWords = getNumOfWords(inData);
            if (inData.equals("help")) {
                Message.getHelp();
            } else if (inData.equals("list")) {
                //print entire list if input is equal to "list"
                TaskManager.getList(tasks);
            } else if (inData.equals(("bye"))) {
                Message.showExit();
                break; //exit loop
            } else if (inData.isBlank() || inData.isEmpty()) {
                Message.showWrongCommand();
            } else if (inData.contains(" ")) {
                command = inData.split(" ")[0];
                String[] userInput = inData.split(" ");
                StringBuilder synthesizedArr = new StringBuilder();
                for (int i = 1; i < numOfWords; i += 1) {
                    synthesizedArr.append(userInput[i]).append(" ");
                }
                String taskDescription = synthesizedArr.toString();
                switch (command) {
                case "mark":
                    TaskManager.processCommand(tasks, numOfWords, "mark", userInput);
                    break;
                case "unmark":
                    TaskManager.processCommand(tasks, numOfWords, "unmark", userInput);
                    break;
                case "delete":
                    TaskManager.processCommand(tasks, numOfWords, "delete", userInput);
                    break;
                case "todo":
                    TaskManager.addTodo(tasks, taskDescription);
                    break;
                case "deadline":
                    TaskManager.addDeadline(tasks, taskDescription);
                    break;
                case "event":
                    TaskManager.addEvent(tasks, taskDescription);
                    break;
                default:
                    Message.showWrongCommand();

                }
            } else {
                Message.showWrongCommand();
            }
        }
    }
}
