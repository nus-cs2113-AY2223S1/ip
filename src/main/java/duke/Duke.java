package duke;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

import duke.util.Storage;
import duke.util.InputParser;
import duke.util.TaskManager;
import duke.util.Ui;

import java.util.List;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskManager taskList;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskManager();
        InputParser.init();
    }

    public void closeUtil() {
        ui.close();
        InputParser.close();
        taskList.close();
    }

    public void start() {
        ui.greetUser();
        loadPastSession();
        InputParser.readInput();
        String userInput = InputParser.getCommand();

        while (!isExit(userInput)) {

            try {
                InputParser.parseUserInput(userInput);
                executeUserInput();
            } catch (DukeException e) {
                ui.displayMessage(e.getErrorMessage());
            }
            InputParser.readInput();
            userInput = InputParser.getCommand();
        }

        closeUtil();
    }

    public void executeUserInput() throws UnknownCommandException {
        String command = InputParser.getCommand();
        List<String> parameters = InputParser.getTaskParameters();

        try {
            switch (command) {
                case ("list"):
                    taskList.listAllTask();
                    break;
                case ("mark"):
                    taskList.setTask(Integer.parseInt(parameters.get(0)) - 1, true);
                    break;
                case ("unmark"):
                    taskList.setTask(Integer.parseInt(parameters.get(0)) - 1, false);
                    break;
                case ("marked"):
                    taskList.setTask(-1, true);
                    break;
                case ("delete"):
                    taskList.deleteTask(Integer.parseInt(parameters.get(0)) - 1);
                    break;
                case ("todo"):
                    taskList.addTodo(parameters.get(0));
                    break;
                case ("deadline"):
                    taskList.addDeadline(parameters.get(0), parameters.get(1));
                    break;
                case ("event"):
                    taskList.addEvent(parameters.get(0), parameters.get(1) );
                    break;
                default:
                    throw new UnknownCommandException("Error: Unknown Command");
            }
        } catch (DukeException e) {
            ui.displayMessage( e.getErrorMessage() );
        }
    }

    public void loadPastSession(){
        storage.loadData();

        List<String> pastData = storage.getData();

        for(String userInput: pastData) {
            try {
                InputParser.parseUserInput(userInput);
                executeUserInput();
            } catch (DukeException e) {
                ui.displayMessage( e.getErrorMessage() );
            }
        }

        taskList.setHasLoaded(true);
    }

    public boolean isExit(String userInput) {
        if (userInput.equals("bye")) {
            ui.endMessage();

            try {
                storage.writeData(TaskManager.serialize());
            } catch (DukeException e) {
                ui.displayMessage(e.getErrorMessage());
            }

            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

}
