package duke;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

import duke.util.Storage;
import duke.util.InputParser;
import duke.util.TaskManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static final String LOGO = "\n"
            + "     _________________________________________\n"
            + "   /  _____________________________________   \\ \n"
            + "   | |                                     |  | \n"
            + "   | |  C:\\> Initiating programme. .  .    |  | \n"
            + "   | |                                     |  | \n"
            + "   | |  C:\\> Creating Duke...              |  | \n"
            + "   | |                                     |  | \n"
            + "   | |                                     |  | \n"
            + "   | |                                     |  | \n"
            + "   | |_____________________________________|  | \n"
            + "    \\_____________________________________/ \n"
            + "       \\________________________________/ \n"
            + "        _________________________________ \n"
            + "   _-'.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--- `-_ \n"
            + "_-'.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--..-.-.`-_ \n";

    public static final String MESSAGE_GREET = "Hello! I'm Duke\n What can I do for you?";

    public static Scanner sc;
    public static Storage dataManager = new Storage();

    public static TaskManager taskManager = new TaskManager();
    public static InputParser parser = new InputParser();

    //need to refactor to remove redundant inputs
    public static ArrayList<String> userSessionInput = new ArrayList<>();

    public static void executeUserInput() throws UnknownCommandException {
        String command = parser.getCommand();
        String[] parameters = parser.getTaskParameters();

        try {
            switch (command) {
                case ("list"):
                    taskManager.listAllTask();
                    break;
                case ("mark"):
                    taskManager.setTask(Integer.parseInt(parameters[0]) - 1, true);
                    break;
                case ("unmark"):
                    taskManager.setTask(Integer.parseInt(parameters[0]) - 1, false);
                    break;
                case ("delete"):
                    taskManager.deleteTask(Integer.parseInt(parameters[0]) - 1);
                    break;
                case ("todo"):
                    taskManager.addTodo(parameters[0]);
                    break;
                case ("deadline"):
                    taskManager.addDeadline(parameters[0], parameters[1]);
                    break;
                case ("event"):
                    taskManager.addEvent(parameters[0], parameters[1]);
                    break;
                default:
                    throw new UnknownCommandException("Error: Unknown Command");
            }
        } catch (DukeException e) {
            System.out.println(e.getErrorMessage());
        }
    }

    public static void loadPastSession(){
        dataManager.loadData();

        List<String> pastData = dataManager.getData();

        for(String userInput: pastData) {
            try {
                parser.parseUserInput(userInput);
                executeUserInput();
            } catch (DukeException e) {
                System.out.println(e.getErrorMessage());
            }
        }

        userSessionInput.addAll(pastData);

        taskManager.setHasLoaded(true);
    }

    public static void greetUser() {
        System.out.println(LOGO);
        System.out.println(MESSAGE_GREET);
    }

    public static boolean isExit(String userInput) {
        if (userInput.equals("bye")) {
            System.out.println("BEEP BEEP >>>> SEE >>> YOU >>>> AGAIN >>> BEEP BEWWWWW >>>");
            dataManager.writeData(userSessionInput);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        greetUser();
        loadPastSession();

        sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!isExit(userInput)) {

            userSessionInput.add(userInput);

            try {
                parser.parseUserInput(userInput);
                executeUserInput();
            } catch (DukeException e) {
                System.out.println(e.getErrorMessage());
            }
            userInput = sc.nextLine();
        }

        sc.close();
    }
}
