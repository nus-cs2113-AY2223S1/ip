package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;

public class Duke {

    public static final String QUERY_COMMAND_MESSAGE = "What can I do for you?";
    public static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String MARK_TASK_UNDONE_MESSAGE = "Nice! I've marked this task as not done:";
    public static final String MARK_TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";

    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    public static final String EVENT_DESCRIPTION_SEPARATOR = " /at ";
    public static final String DEADLINE_DESCRIPTION_SEPARATOR = " /by ";
    public static final String DATA_DUKE_TXT = "data/duke.txt";
    public static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private Parser parser;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(DATA_DUKE_TXT).run();
    }

    private void run() {
        ui.showWelcomeMessage();
        while (true) {
            try {
                String input = ui.readCommand();
                String[] CommandTypeAndArguments = parser.splitCommandTypeAndArguments(input);
                String feedback = executeCommand(CommandTypeAndArguments);
                ui.showFeedbackToUser(feedback);
            }  catch (InvalidCommandException e) {
                ui.showFeedbackToUser(e.getMessage());
            } catch (EmptyDescriptionException e) {
                ui.showFeedbackToUser(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private String executeCommand(String[] commandTypeAndArguments) throws EmptyDescriptionException, InvalidCommandException, IOException {
       String commandType = commandTypeAndArguments[0];
       String commandArguments = getCommandArguments(commandTypeAndArguments);
        switch (commandType) {
        case "list":
            return executeListCommand();
        case "mark":
            return executeMark(true, commandArguments);
        case "unmark":
            return executeMark(false, commandArguments);
        case "todo":
            return executeCreateToDo(commandArguments);
        case "deadline":
            return executeCreateDeadline(commandArguments);
        case "event":
            return executeCreateEvent(commandArguments);
        case "delete":
            return executeDelete(commandArguments);
        case "bye":
            exitProgramme();
        default:
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String getCommandArguments(String[] commandTypeAndArguments) {
        if (commandTypeAndArguments.length == 1) {
            return "";
        }
        return commandTypeAndArguments[1];
    }

    private String executeDelete(String commandArguments) throws IOException {
        int taskIndex = Integer.parseInt(commandArguments) - 1;
        String feedback = DELETE_TASK_MESSAGE + System.lineSeparator() + "  " + taskList.getTasks().get(taskIndex).getTaskInfo();
        taskList.getTasks().remove(taskIndex);
        storage.overWriteDukeTxt(taskList.getTasks());
        return feedback + System.lineSeparator()+ getTasksCountFeedback();
    }

    private String executeCreateEvent(String commandArguments) throws EmptyDescriptionException, IOException {
        if (commandArguments.length() == 0) {
            throw new EmptyDescriptionException("The description of an event cannot be empty");
        }
        String[] separatedArguments = parser.getDescriptionAndTime(commandArguments, EVENT_DESCRIPTION_SEPARATOR);
        if (separatedArguments.length < 2) {
            throw new EmptyDescriptionException("pty");
        }
        return createEvent(separatedArguments);
    }

    private String createEvent(String[] separatedArguments) throws IOException {
        Event newEvent = new Event(separatedArguments[0], separatedArguments[1]);
        taskList.getTasks().add(newEvent);
        storage.appendToFile(newEvent.toString());
        return ADD_TASK_MESSAGE + System.lineSeparator() +  newEvent.getTaskInfo() + System.lineSeparator() + getTasksCountFeedback();
    }


    private String executeCreateDeadline(String commandArguments) throws EmptyDescriptionException, IOException {
        if (commandArguments.length() == 0) {
            throw new EmptyDescriptionException("The description  of a deadline cannot be empty");
        }
        String[] separatedArguments = parser.getDescriptionAndTime(commandArguments, DEADLINE_DESCRIPTION_SEPARATOR);
        if (separatedArguments.length < 2) {
            throw new EmptyDescriptionException("The description of a deadline cannot be empty");
        }
        return createDeadline(separatedArguments);
    }

    private String createDeadline(String[] separatedArguments) throws IOException {
        Deadline newDeadline = new Deadline(separatedArguments[0], separatedArguments[1]);
        taskList.getTasks().add(newDeadline);
        storage.appendToFile(newDeadline.toString());
        return ADD_TASK_MESSAGE + System.lineSeparator() +  newDeadline.getTaskInfo() + System.lineSeparator() + getTasksCountFeedback();
    }


    private String executeCreateToDo(String commandArgument) throws EmptyDescriptionException, IOException {
        if (commandArgument.length() == 0) {
            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
        }
        ToDo newToDo = new ToDo(commandArgument);
        taskList.getTasks().add(newToDo);
        storage.appendToFile(newToDo.toString());
        String feedback = "Got it. I've added this task";
        feedback += System.lineSeparator() + newToDo.getTaskInfo() + System.lineSeparator() + getTasksCountFeedback();
        return feedback;
    }

    private String executeMark(boolean isMarkAsDone, String commandArgument) throws IOException {
        int taskIndex = Integer.parseInt(commandArgument) - 1;
        Task targetTask = taskList.getTasks().get(taskIndex);
        String feedback= "";
        if (isMarkAsDone) {
            targetTask.setDone(true);
            feedback = MARK_TASK_DONE_MESSAGE;
        } else {
            targetTask.setDone(false);
            feedback = MARK_TASK_UNDONE_MESSAGE;
        }
        storage.overWriteDukeTxt(taskList.getTasks());
        feedback = feedback + System.lineSeparator() + "  " + targetTask.getTaskInfo();
        return feedback;
    }

    private void exitProgramme() {
        ui.showFeedbackToUser(EXIT_MESSAGE);
        System.exit(0);
    }


    private String executeListCommand() {
        ui.showTaskList(taskList.getTasks());
        return getTasksCountFeedback();
    }

    private String getTasksCountFeedback() {
        int taskCount = taskList.getTaskCount();
        String taskName = taskCount < 2 ? "task" : "tasks";
        return System.lineSeparator() + String.format("Now you have %d %s in the list.", taskCount, taskName);
    }

}
