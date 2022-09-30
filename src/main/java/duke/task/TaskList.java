package duke.task;

import duke.Storage;
import duke.exception.EmptyNumberInputException;
import duke.exception.InvalidDeadlineInputException;
import duke.exception.InvalidEventInputException;
import duke.exception.InvalidTaskDescriptionException;
import duke.parser.Parser;

import java.util.ArrayList;

import static duke.Ui.*;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {

    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        Storage.loadInputFile(this.tasks);
    }


    public static void addTodo(TaskList tasks, String fullCommand) {
        try {
            String description;
            description = Parser.getDescription(fullCommand).trim();
            Task t = new Todo(description);
            tasks.addTask(t);
            printSuccessfulAdd(tasks);
        } catch (InvalidTaskDescriptionException e) {
            showInvalidTodoInputExceptionMessage();
        }
    }

    public static void addDeadline(TaskList tasks, String fullCommand) {
            try {
                String[] description = Parser.parseDeadlineDescription(fullCommand);
                String deadlineName = description[0].trim();
                String deadlineDetails = description[1].trim();
                Task t = new Deadline(deadlineName, deadlineDetails);
                tasks.addTask(t);
                printSuccessfulAdd(tasks);
            } catch (InvalidDeadlineInputException e) {
                showInvalidDeadlineInputExceptionMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                showInvalidDeadlineInputExceptionMessage();
            }

    }

    public static void addEvent(TaskList tasks, String fullCommand){
            try {
                String[] description = Parser.parseEventDescription(fullCommand);
                String eventName = description[0].trim();
                String eventDetails = description[1].trim();
                Task t = new Event(eventName, eventDetails);
                tasks.addTask(t);
                printSuccessfulAdd(tasks);
            } catch (InvalidEventInputException e) {
                showInvalidEventInputExceptionMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                showInvalidEventInputExceptionMessage();
            }
    }

    public static void findMatchingTasks(TaskList tasks, String fullCommand) {
        String keyword;
        try {
            keyword = Parser.getDescription(fullCommand).trim();
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task t : tasks.tasks) {
                if (t.description.contains(keyword)) {
                    matchingTasks.add(t);
                }
            }
            printMatchingTasks(matchingTasks);
        } catch (InvalidTaskDescriptionException e) {
            showInvalidFindDescriptionExceptionMessage();
        }
    }

    public static void deleteTask(TaskList tasks, String line) {
        try {
            int taskId = Parser.getTaskId(line);
            Task taskToBeDeleted = tasks.get(taskId);
            int taskSize = tasks.size() - 1;
            System.out.println("\t_____________________");
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + taskToBeDeleted);

            if (taskSize == 1 || taskSize == 0) {
                System.out.println("\tNow you have " + taskSize + " task in the list.");
            } else {
                System.out.println("\tNow you have " + taskSize + " tasks in the list.");
            }
            System.out.println("\t_____________________");
            tasks.removeTask(taskId);
        } catch (NumberFormatException e) {
            showInvalidDeleteInputExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            showInvalidDeleteInputExceptionMessage();
        } catch (EmptyNumberInputException e) {
            showInvalidDeleteInputExceptionMessage();
        }
    }

    public static void markTask(TaskList tasks, String line) {
        try {
            int taskId = Parser.getTaskId(line);
            printMark(tasks, taskId);
        } catch (NumberFormatException e) {
            showInvalidMarkTaskInputExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            showInvalidMarkTaskInputExceptionMessage();
        } catch (EmptyNumberInputException e) {
            showInvalidMarkTaskInputExceptionMessage();
        }
    }

    public static void unmarkTask(TaskList tasks, String line) {
        try {
            int taskId = Parser.getTaskId(line);
            printUnmark(tasks, taskId);
        } catch (NumberFormatException e) {
            showInvalidUnmarkTaskInputExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            showInvalidUnmarkTaskInputExceptionMessage();
        } catch (EmptyNumberInputException e) {
            showInvalidUnmarkTaskInputExceptionMessage();
        }
    }
    
    public static void totalTask(TaskList tasks, String fullCommand) {
        int taskSize = tasks.size();
        printTotalNumberOfItems(tasks, taskSize);
    }

    // get size of tasks
    public int size() {
        return tasks.size();
    }

    // get task from tasks
    public Task get(int index) {
        return tasks.get(index);
    }

    // add task to tasks
    public void addTask(Task task) {
        tasks.add(task);
    }

    // remove task from tasks
    public void removeTask(int index) {
        tasks.remove(index);
    }





}
