package duke;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

import duke.util.Storage;
import duke.util.InputParser;
import duke.util.TaskManager;
import duke.util.Ui;

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

    public static final String MESSAGE_BYE = "BEEP BEEP >>>> SEE >>> YOU >>>> AGAIN >>> BEEP BEWWWWW >>>";

    public static void executeUserInput() throws UnknownCommandException {
        String command = InputParser.getCommand();
        String[] parameters = InputParser.getTaskParameters();

        try {
            switch (command) {
                case ("list"):
                    TaskManager.listAllTask();
                    break;
                case ("mark"):
                    TaskManager.setTask(Integer.parseInt(parameters[0]) - 1, true);
                    break;
                case ("unmark"):
                    TaskManager.setTask(Integer.parseInt(parameters[0]) - 1, false);
                    break;
                case ("delete"):
                    TaskManager.deleteTask(Integer.parseInt(parameters[0]) - 1);
                    break;
                case ("todo"):
                    TaskManager.addTodo(parameters[0]);
                    break;
                case ("deadline"):
                    TaskManager.addDeadline(parameters[0], parameters[1]);
                    break;
                case ("event"):
                    TaskManager.addEvent(parameters[0], parameters[1]);
                    break;
                default:
                    throw new UnknownCommandException("Error: Unknown Command");
            }
        } catch (DukeException e) {
            System.out.println(e.getErrorMessage());
        }
    }

    public static void loadPastSession(){
        Storage.loadData();

        List<String> pastData = Storage.getHistory();

        for(String userInput: pastData) {
            try {
                InputParser.parseUserInput(userInput);
                executeUserInput();
            } catch (DukeException e) {
                System.out.println(e.getErrorMessage());
            }
        }

        Storage.addSessionCommands(pastData);
        TaskManager.setHasLoaded(true);
    }

    public static void greetUser() {
        System.out.println(LOGO);
        System.out.println(MESSAGE_GREET);
    }

    public static boolean isExit(String userInput) {
        if (userInput.equals("bye")) {
            Ui.endMessage();
            Storage.writeData();
            return true;
        }
        return false;
    }

    public static void initUtil() {
        Ui.init();
        InputParser.init();
        Storage.init();
        TaskManager.init();
    }

    public static void closeUtil() {
        Ui.close();
        InputParser.close();
        Storage.close();
        TaskManager.close();
    }

    public static void main(String[] args) {
        initUtil();
        Ui.greetUser();
        loadPastSession();
        InputParser.readInput();
        String userInput = InputParser.getCommand();


        while (!isExit(userInput)) {

            Storage.addSessionCommand(userInput);

            try {
                InputParser.parseUserInput(userInput);
                executeUserInput();
            } catch (DukeException e) {
                System.out.println(e.getErrorMessage());
            }
            InputParser.readInput();
            userInput = InputParser.getCommand();
        }

        closeUtil();
    }


}
