package duke.taskmanager;

import duke.UI;
import duke.exceptions.EmptyException;
import duke.exceptions.NoBackslashException;
import duke.exceptions.TaskOutOfBoundsException;
import duke.exceptions.WrongCommandException;
import duke.taskmanager.tasks.Deadline;
import duke.taskmanager.tasks.Event;
import duke.taskmanager.tasks.Task;
import duke.taskmanager.tasks.Todo;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager {
    public static StringBuilder toSave = new StringBuilder();

    public static ArrayList<Task> tasks = new ArrayList<>() {
        {
            add(new Todo("Todo buffer for one based input", ' '));
        }
    };
    public static int oneBasedIndex = 1;

    private Storage storage;
    private UI ui;

    public TaskManager() {
        ui = new UI();
        ui.printGreetingMessage();
        try {
            File f = new File("data/duke.txt"); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String command = s.nextLine();
                tryCommand(command);
            }
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundException();
        }

    }

    public void receiveCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        while (!command.equals("bye")) {
            boolean isList = command.equals("list");
            if (isList) {
                ui.printList(tasks);
            } else {
                tryCommand(command);
            }
            command = in.nextLine().trim();
        }
        try {
            saveTasks();
        } catch (IOException e) {
            System.out.println("I/O error...");
        }
        ui.printExitMessage();
    }

    private void saveTasks() throws IOException {
        File file = new File("data/duke.txt");
        FileWriter fw;
        file.getParentFile().mkdirs();
        file.createNewFile();
        fw = new FileWriter(file);
        fw.write(String.valueOf(toSave));
        fw.close();
    }

    private void tryCommand(String command) {
        String firstWord;
        try {
            firstWord = command.substring(0, command.indexOf(' '));
            doCommand(command, firstWord);
        } catch (StringIndexOutOfBoundsException e) {
            try {
                checkExceptions(command);
            }catch (EmptyException ee) {
                ui.printEmptyException(command);
            } catch (WrongCommandException ee) {
                ui.printWrongCommandException();
            }
        } catch (WrongCommandException e) {
            ui.printWrongCommandException();
        } catch (TaskOutOfBoundsException e) {
            ui.printTaskOutOfBoundsException();
        } catch (NoBackslashException e) {
            ui.printNoBackslashException();
        }
    }

    private void doCommand(String command, String firstWord)
            throws WrongCommandException, TaskOutOfBoundsException, NoBackslashException {
        switch (firstWord) {
        case "mark":
            mark(command, true);
            toSave.append(command).append(System.lineSeparator());
            break;
        case "unmark":
            mark(command, false);
            toSave.append(command).append(System.lineSeparator());
            break;
        case "todo":
            tasks.add(new Todo(command, ' '));
            ui.printTask(tasks.get(oneBasedIndex));
            toSave.append(command).append(System.lineSeparator());
            break;
        case "deadline":
            //Fallthrough
        case "event":
            addDeadlineOrEvent(command,firstWord);
            toSave.append(command).append(System.lineSeparator());
            break;
        case "delete":
            delete(command);
            toSave.append(command).append(System.lineSeparator());
            break;
        default:
            throw new WrongCommandException();
        }
    }

    private void delete(String command) throws TaskOutOfBoundsException {
        try {
            int startIdx = "delete ".length();
            int pos = Integer.parseInt(command.substring(startIdx));
            if (pos >= oneBasedIndex || pos < 1) {
                throw new TaskOutOfBoundsException();
            }
            ui.printTaskAfterDelete(tasks.get(pos));
            tasks.remove(pos);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        }
    }

    private void addDeadlineOrEvent(String command, String firstWord) throws NoBackslashException {
        if (command.contains("/")){
            switch (firstWord) {
            case "deadline":
                tasks.add(new Deadline(command, '/'));
                break;
            case "event":
                tasks.add(new Event(command, '/'));
                break;
            }
            ui.printTask(tasks.get(oneBasedIndex));
        } else {
            throw new NoBackslashException();
        }
    }

    private void mark(String command, boolean done) throws TaskOutOfBoundsException {
        try {
            int startIdx = command.substring(0, command.indexOf(' ') + 1).length();
            int pos = Integer.parseInt(command.substring(startIdx));
            if (pos >= oneBasedIndex || pos < 1) {
                throw new TaskOutOfBoundsException();
            }
            ui.printMark(tasks.get(pos), done);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        }
    }

    private void checkExceptions(String command) throws EmptyException, WrongCommandException {
        if (command.matches("mark|unmark|todo|deadline|event|delete")) {
            throw new EmptyException();
        } else {
            throw new WrongCommandException();
        }
    }
}