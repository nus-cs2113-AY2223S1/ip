package duke.task;

import java.util.ArrayList;
import duke.command.Ui;
import duke.command.Parser;
import duke.exception.MissingDeadlineDescriptionException;
import duke.exception.MissingEventDescriptionException;
import duke.exception.MissingKeywordException;
import duke.exception.MissingTaskNumberException;
import duke.exception.MissingTodoDescriptionException;
import duke.exception.NonIntegerTaskNumberException;
import duke.exception.OutOfBoundsTaskNumberException;

public abstract class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void addTask(String type, String input) {
        final String ADD_PHRASE_START = "added: ";
        // add one because newTask has not been created yet
        final String ADD_PHRASE_END =
                "There are " + Integer.toString(Task.getTaskCount() + 1) + " task(s) in your list.";

        Task newTask;
        try {
            newTask = Parser.parseTask(type, input);
        } catch (MissingTodoDescriptionException e) {
            System.out.println("Missing Todo Description");
            return;
        } catch (MissingDeadlineDescriptionException e) {
            System.out.println("Missing Deadline Description");
            return;
        } catch (MissingEventDescriptionException e) {
            System.out.println("Missing Event Description");
            return;
        }

        // change to zero-index
        tasks.add(newTask);

        System.out.println(ADD_PHRASE_START + newTask);
        System.out.println(ADD_PHRASE_END);
    }

    public static void deleteTask(String input) {
        int taskNum;

        try {
            taskNum = Parser.parseTaskNumber(Ui.DELETE_PHRASE, input);
        } catch (MissingTaskNumberException e) {
            System.out.println("Missing task number");
            return;
        } catch (NonIntegerTaskNumberException e) {
            System.out.println("Non-integer task number");
            return;
        } catch (OutOfBoundsTaskNumberException e) {
            System.out.println("Out of bounds task number");
            return;
        }

        final String DELETE_PHRASE_START = "deleted: ";
        // minus one becuase task has not been deleted yet
        final String DELETE_PHRASE_END =
                "There are " + Integer.toString(Task.getTaskCount() - 1) + " task(s) in your list.";

        System.out.println(DELETE_PHRASE_START);
        System.out.println(tasks.get(taskNum));

        tasks.remove(taskNum);
        Task.deleteTask();

        System.out.println(DELETE_PHRASE_END);
    }

    public static void printList() {
        for (int i = 0; i < Task.getTaskCount(); i += 1) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i));
        }
    }

    public static void markTask(String input) {
        int taskNum;

        try {
            taskNum = Parser.parseTaskNumber(Ui.MARK_PHRASE, input);
        } catch (MissingTaskNumberException e) {
            System.out.println("Missing task number");
            return;
        } catch (NonIntegerTaskNumberException e) {
            System.out.println("Non-integer task number");
            return;
        } catch (OutOfBoundsTaskNumberException e) {
            System.out.println("Out of bounds task number");
            return;
        }

        tasks.get(taskNum).setDone();

        System.out.println("Completed! The following task is marked as done:");
        System.out.println(tasks.get(taskNum));
    }

    public static void unmarkTask(String input) {
        int taskNum;

        try {
            taskNum = Parser.parseTaskNumber(Ui.UNMARK_PHRASE, input);
        } catch (MissingTaskNumberException e) {
            System.out.println("Missing task number");
            return;
        } catch (NonIntegerTaskNumberException e) {
            System.out.println("Non-integer task number");
            return;
        } catch (OutOfBoundsTaskNumberException e) {
            System.out.println("Out of bounds task number");
            return;
        }

        tasks.get(taskNum).setUndone();

        System.out.println("Oh no! The following task is marked as undone:");
        System.out.println(tasks.get(taskNum));
    }

    public static void searchTask(String input) {
        String keyword;

        try {
            keyword = Parser.parseKeyword(input);
        } catch (MissingKeywordException e) {
            System.out.println("Keyword missing");
            return;
        }

        int matchingTaskCounter = 1;

        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                System.out.println(matchingTaskCounter + ". " + task);

                matchingTaskCounter += 1;
            }
        }

        if (matchingTaskCounter == 1) {
            System.out.println("No matching tasks found");
        }
    }
}
