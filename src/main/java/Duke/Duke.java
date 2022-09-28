package Duke;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.UnknownCommandException;
import Duke.Tasks.TaskManager;
import Duke.Storage.Storage;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static TaskManager tasks = new TaskManager();
    public static final String PRINT_LINE = "____________________________________________________________\n";

    public static void main(String[] args) {
        Storage storage = null;
        WelcomeMessage();
        try {
            storage = new Storage();
            storage.loadData(tasks);
        } catch (IOException e) {
            System.out.println(
                    Duke.PRINT_LINE
                            + "Error loading file \n"
                            + Duke.PRINT_LINE
            );
        }
        readInput();
        GoodbyeMessage();

    }


    public static void readInput() {
        Scanner sc = new Scanner(System.in);
        boolean isFinished = false;
        int taskNum = 0;

        do {
            String command = sc.nextLine();
            String[] splitCommand = command.split(" ");

            switch (splitCommand[0]) {
            case "bye":
                try {
                    Storage.writeToFile();
                } catch (IOException e) {
                    System.out.println("Error inputting data to file");
                }
                isFinished = true;
                break;
            case "mark":
                tasks.markTask(command);
                break;
            case "unmark":
                tasks.unmarkTask(command);
                break;
            case "list":
                tasks.printList();
                break;
            case "todo":
                tasks.addToDo(command);
                break;
            case "event":
                tasks.addEvent(command);
                break;
            case "deadline":
                tasks.addDeadline(command);
                break;
            case "delete":
                tasks.deleteTask(command);
                break;
            case "find":
                tasks.findTask(command);
                break;
            default:
                UnknownCommandDetection();
                break;
            }

        } while (isFinished != true);


    }

    private static void UnknownCommandDetection() {
        try {
            throw new UnknownCommandException();
        } catch (UnknownCommandException e) {
            e.UnknownCommandMessage();
        }
    }

    public static void WelcomeMessage() {
        String logo =
                " ___  __    ___  ________   ________          ________  ________  ________     \n" +
                        "|\\  \\|\\  \\ |\\  \\|\\   ___  \\|\\   ____\\        |\\   __  \\|\\   __  \\|\\   __  \\    \n" +
                        "\\ \\  \\/  /|\\ \\  \\ \\  \\\\ \\  \\ \\  \\___|        \\ \\  \\|\\ /\\ \\  \\|\\  \\ \\  \\|\\ /_   \n" +
                        " \\ \\   ___  \\ \\  \\ \\  \\\\ \\  \\ \\  \\  ___       \\ \\   __  \\ \\  \\\\\\  \\ \\   __  \\  \n" +
                        "  \\ \\  \\\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\       \\ \\  \\|\\  \\ \\  \\\\\\  \\ \\  \\|\\  \\ \n" +
                        "   \\ \\__\\\\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\_______\\       \\ \\_______\\ \\_______\\ \\_______\\\n" +
                        "    \\|__| \\|__|\\|__|\\|__| \\|__|\\|_______|        \\|_______|\\|_______|\\|_______|\n";

        System.out.println(logo
                + PRINT_LINE
                + " Hello! I'm King Bob\n"
                + " What can I do for you?\n"
                + PRINT_LINE
        );
    }

    public static void GoodbyeMessage() {
        System.out.println(
                PRINT_LINE
                        + "Bye. Come back soon! :) \n"
                        + PRINT_LINE
        );
    }
}


