package duke;

import duke.command.Command;
import duke.command.CommandMenu;
import duke.task.TaskList;

import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ________      ________ _____\n" +
            "|  ____\\ \\    / /  ____|  __ \\\n" +
            "| |__   \\ \\  / /| |__  | |__) |\n" +
            "|  __|   \\ \\/ / |  __| |  _  /\n" +
            "| |____   \\  /  | |____| | \\ \\\n" +
            "|______|   \\/   |______|_|  \\_\\";
    private static final String HORIZONTAL_LINE = "==================================================================";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static TaskList taskList;
    private static CommandMenu commandMenu;

    public Duke() {
        taskList = new TaskList();
        commandMenu = new CommandMenu();
    }

    private static void greet() {
        System.out.println(LOGO);
        System.out.println(Message.GREETING_MESSAGE);
    }

    private static void exit() {
        System.out.println(Message.EXIT_MESSAGE);
    }

    private static String getUserInput() {
        System.out.println(HORIZONTAL_LINE);
        return SCANNER.nextLine().trim();
    }

    public void run() {
        greet();

        while (true) {
            String userCommand = getUserInput();
            Command command = Parser.parseCommand(userCommand);
            command.execute(commandMenu, taskList);
            if (command.isExit()) {
                break;
            }
        }

        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
