package duke;

import duke.taskings.Task;
import duke.taskings.TaskList;
import duke.messages.Ui;

import java.util.ArrayList;

public class Parser {
    static final boolean EXIT = false;
    static final boolean CONTINUE = true;

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

    /**
     * parses the user input and processes it with the tasks array list.
     *
     * @param inData the user input .
     * @param tasks the array which would be operated on.
     *
     * @return EXIT if input equals "bye", else return CONTINUE
     */

    public static boolean processInput(String inData, ArrayList<Task> tasks) {

        if (inData.equals("help")) {
            Ui.getHelp();
        } else if (inData.equals("list")) {
            //print entire list if input is equal to "list"
            TaskList.getList(tasks);
        } else if (inData.equals(("bye"))) {
            Ui.showExit();
            return EXIT; //exit loop
        } else if (inData.isBlank() || inData.isEmpty()) {
            Ui.showWrongCommand();
        } else if (inData.contains(" ")) {
            //further parse the user input for user taskings
            int numOfWords = getNumOfWords(inData);
            String command = inData.split(" ")[0];
            String[] userInput = inData.split(" ");
            StringBuilder synthesizedArr = new StringBuilder();
            for (int i = 1; i < numOfWords; i += 1) {
                synthesizedArr.append(userInput[i]).append(" ");
            }
            String taskDescription = synthesizedArr.toString();


            switch (command) {
            case "mark":
                TaskList.processCommand(tasks, numOfWords, "mark", userInput);
                break;
            case "unmark":
                TaskList.processCommand(tasks, numOfWords, "unmark", userInput);
                break;
            case "delete":
                TaskList.processCommand(tasks, numOfWords, "delete", userInput);
                break;
            case "todo":
                TaskList.addTodo(tasks, taskDescription);
                break;
            case "deadline":
                TaskList.addDeadline(tasks, taskDescription);
                break;
            case "event":
                TaskList.addEvent(tasks, taskDescription);
                break;
            case "find":
                TaskList.findKeyword(tasks,taskDescription, numOfWords);
                break;
            default:
                Ui.showWrongCommand();

            }
        } else {
            Ui.showWrongCommand();
        }
        return CONTINUE;
    }
}
