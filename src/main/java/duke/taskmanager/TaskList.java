package duke.taskmanager;

import duke.UI;
import duke.exceptions.*;
import duke.taskmanager.tasks.Deadline;
import duke.taskmanager.tasks.Event;
import duke.taskmanager.tasks.Task;
import duke.taskmanager.tasks.Todo;

import java.util.ArrayList;

import static duke.taskmanager.TaskManager.toSave;

public class TaskList extends ArrayList<Task> {

    private final UI ui = new UI();
    public TaskList() {
        this.add(new Todo("Todo buffer for one based input", ' '));
    }

    public TaskList(ArrayList<String> storedTasks) throws DukeException {
        if (storedTasks.isEmpty()) {
            throw new DukeException();
        }
        this.add(new Todo("Todo buffer for one based input", ' '));
        for (String s : storedTasks) {
            tryCommand(s);
        }
    }
    public void tryCommand(String command) {
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

    public void doCommand(String command, String firstWord)
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
            this.add(new Todo(command, ' '));
            ui.printTask(this.get(this.size()-1));
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
            if (pos > this.size() || pos < 1) {
                throw new TaskOutOfBoundsException();
            }
            ui.printTaskAfterDelete(this.get(pos));
            this.remove(pos);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        }
    }

    private void addDeadlineOrEvent(String command, String firstWord) throws NoBackslashException {
        if (command.contains("/")){
            switch (firstWord) {
            case "deadline":
                this.add(new Deadline(command, '/'));
                break;
            case "event":
                this.add(new Event(command, '/'));
                break;
            }
            ui.printTask(this.get(this.size()-1));
        } else {
            throw new NoBackslashException();
        }
    }

    private void mark(String command, boolean done) throws TaskOutOfBoundsException {
        try {
            int startIdx = command.substring(0, command.indexOf(' ') + 1).length();
            int pos = Integer.parseInt(command.substring(startIdx));
            if (pos > this.size() || pos < 1) {
                throw new TaskOutOfBoundsException();
            }
            ui.printMark(this.get(pos), done);
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
