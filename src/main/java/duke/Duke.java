package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static TaskList myTaskList = new TaskList();

    /**
     * Method to run the main loop of the program
     * Accepts user input and carries out relevant command until user says "bye"
     */
    public static void run() {
        Ui.welcomeUser();
        try {
            myTaskList = Storage.readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            Parser.handleInput(userInput, myTaskList);
            System.out.println();
            userInput = in.nextLine();
        }
        Ui.goodbyeUser();
    }

    public static void main(String[] args) {
        Duke.run();
    }
}

