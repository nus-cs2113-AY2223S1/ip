package duke;

import duke.command.Menu;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    public static String[] splitInput(String userInput) {
        String[] split = userInput.split(" ", 2);
        if (split.length == 1) {
            return new String[]{split[0], ""};
        }
        return split;
    }

    public void executeInstruction(String instruction, String inputValue) throws DukeException {
        switch (instruction) {
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            tasks.addTask(instruction, inputValue, false);
            break;
        case "delete":
            tasks.deleteTask(inputValue);
            break;
        case "list":
            tasks.list();
            break;
        case "mark":
            tasks.mark(inputValue, false);
            break;
        case "unmark":
            tasks.unmark(inputValue);
            break;
        case "bye":
            ui.displayExitMessage();
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    public void saveFile(String instruction) throws IOException {
        switch (instruction) {
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            storage.rewriteDukeFile(tasks);
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            storage.appendDukeFile(tasks);
            break;
        default:
            // No action for other command or invalid command
            break;
        }
    }

    public void safeExecuteInstruction(String instruction, String inputValue) {
        try {
            executeInstruction(instruction, inputValue);
            saveFile(instruction);
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            //dukeMenu.displayErrorMessage();
        }
    }

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.initialize());
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        String userInput = "", instruction = "", inputValue = "";
        Scanner in = new Scanner(System.in);
        ui.displayGreetingMessage();
        while (!instruction.equals("bye")) {
            userInput = in.nextLine();
            String[] splits = splitInput(userInput);
            instruction = splits[0];
            inputValue = splits[1];
            safeExecuteInstruction(instruction, inputValue);
        }

    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
