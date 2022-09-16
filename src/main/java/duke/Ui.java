package duke;

import java.util.Scanner;

public class Ui {
    private static final String INDENT = "    ";
    private static final String HORIZONTAL_RULE = "____________________________________________________________\n";
    private static final String DUKE_LOGO = String.join("\n", " ____        _        ", "|  _ \\ _   _| | _____ ",
            "| | | | | | | |/ / _ \\", "| |_| | |_| |   <  __/", "|____/ \\__,_|_|\\_\\___|", "");
    private static final String[] GREETING = { "Hello! I'm Duke", "What can I do for you?" };
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String[] lines) {
        System.out.println(INDENT + HORIZONTAL_RULE);
        for (String line : lines) {
            System.out.println(INDENT + " " + line);
        }
        System.out.println(INDENT + HORIZONTAL_RULE);
    }

    public void displayMessage(String line) {
        displayMessage(line.split("\n"));
    }

    public void initialize() {
        System.out.println(DUKE_LOGO);
        displayMessage(GREETING);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void cleanUp() {
        displayMessage(EXIT_MESSAGE);
        scanner.close();
    }
}
