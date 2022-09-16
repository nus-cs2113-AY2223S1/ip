package Duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static Scanner sc;
    public static DataManager dataManager = new DataManager();

    public static TaskManager taskManager = new TaskManager();
    public static InputParser parser = new InputParser();

    //need to refactor to remove redundant inputs
    public static ArrayList<String> userSessionInput = new ArrayList<String>();

    public static void executeUserInput() throws UnknownCommandException {
        String command = parser.getCommand();
        String[] parameters = parser.getTaskParameters();

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
                throw new UnknownCommandException();
        }

    }

    public static void loadPastSession(){
        dataManager.loadData();

        List<String> pastData = dataManager.getData();

        for(String userInput: pastData) {
            try {
                parser.parseUserInput(userInput);
                executeUserInput();
            } catch (UnknownCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! The description cannot be empty.");
            }
        }

        for(var pastCommand: pastData) {
            userSessionInput.add(pastCommand);
        }

        taskManager.setHasLoaded(true);
    }

    public static void greetUser() {

        final String LOGO = "\n"
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

        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static boolean isToExit(String userInput) {
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

        while (!isToExit(userInput)) {

            userSessionInput.add(userInput);

            try {
                parser.parseUserInput(userInput);
                executeUserInput();
            } catch (UnknownCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! The description cannot be empty.");
            }
            userInput = sc.nextLine();
        }

        sc.close();
    }
}
