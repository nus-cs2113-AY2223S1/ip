package duke;

import duke.exceptions.EmptyTasklineException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void greet() {
        System.out.println("  ____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("  ____________________________________________________________");
    }

    public void bye() {
        System.out.println("  ____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("  ____________________________________________________________");
    }

    public void detectEmptyTasklineException(String taskLine) throws EmptyTasklineException {
        if (taskLine.equals(" ")) {
            throw new EmptyTasklineException();
        }
    }

    public String addTask(String line, String commandWord) {
        boolean hasException = findEmptyTasklineException(line);
        if (hasException) {
            return "exception";
        }

        separatingCommands(line, commandWord);
        System.out.println("  ____________________________________________________________");
        System.out.println("\tAdded:");
        System.out.println("\t  " + tasks[taskCount].getTaskType() + tasks[taskCount].getStatus()
                + tasks[taskCount].getDescription() + tasks[taskCount].getAddedInfo());
        taskCount++;
        System.out.println("\tNow you have " + taskCount + " tasks in the list");
        System.out.println("  ____________________________________________________________");
        return "done adding";
    }

    private void separatingCommands(String line, String commandWord) {
        if (commandWord.equals("todo")) {
            createTodo(line);
        } else if (commandWord.equals("deadline")) {
            createDeadline(line);
        } else if (commandWord.equals("event")) {
            createEvent(line);
        }
    }

    private void createTodo(String line) {
        tasks[taskCount] = new Todo(line);
    }

    private void createEvent(String line) {
        int index = line.indexOf("/at ");
        String at = line.substring(index + 4);
        line = line.substring(0, index - 1);
        tasks[taskCount] = new Event(line, at);
    }

    private void createDeadline(String line) {
        int index = line.indexOf("/by ");
        String by = line.substring(index + 4);
        line = line.substring(0, index - 1);
        tasks[taskCount] = new Deadline(line, by);
    }

    public void listTasks() {
        System.out.println("  ____________________________________________________________");
        System.out.println("\tThese are the tasks in your list:");
        for (int i = 0; i < taskCount; i += 1) {
            int index = i + 1;
            System.out.println("\t" + index + "." + tasks[i].getTaskType() + tasks[i].getStatus()
                    + " " + tasks[i].getDescription() + tasks[i].getAddedInfo());
        }
        System.out.println("  ____________________________________________________________");
    }

    public String markOrUnmark(String line, String commandWord) {
        boolean hasException = findEmptyTasklineException(line);
        if (hasException) {
            return "exception";
        }

        if (commandWord.equals("mark")) {
            markTask(line);
        } else {
            unmarkTask(line);
        }
        return "done marking or unmarking";
    }

    private boolean findEmptyTasklineException(String line) {
        try {
            detectEmptyTasklineException(line);
        } catch (EmptyTasklineException e) {
            System.out.println("  ____________________________________________________________");
            System.out.println("\tThere is no content assigned!");
            System.out.println("  ____________________________________________________________");
            return true;
        }
        return false;
    }

    public void markTask(String line) {
        int index = Integer.parseInt(line) - 1;
        tasks[index].setCompletion(true);
        System.out.println("  ____________________________________________________________");
        System.out.println("\tWell done! I have marked this task as completed:");
        System.out.println("\t  " + tasks[index].getTaskType() + tasks[index].getStatus() + " "
                + tasks[index].getDescription() + tasks[index].getAddedInfo());
        System.out.println("  ____________________________________________________________");
    }

    public void unmarkTask(String line) {
        int index = Integer.parseInt(line) - 1;
        tasks[index].setCompletion(false);
        System.out.println("  ____________________________________________________________");
        System.out.println("\tNoted. I have marked this task as incomplete:");
        System.out.println("\t  " + tasks[index].getTaskType() + tasks[index].getStatus() + " "
                + tasks[index].getDescription() + tasks[index].getAddedInfo());
        System.out.println("  ____________________________________________________________");
    }

    public void addExistingTasksInFile(String[] parts) {
        String taskType = parts[0];
        String taskState = parts[1];
        String taskDescription = parts[2];
        String taskAdditional = parts[3];
        if (taskType.equals("T")) {
            tasks[taskCount] = new Todo(taskDescription);
        } else if (taskType.equals("D")) {
            tasks[taskCount] = new Deadline(taskDescription, taskAdditional);
        } else {
            tasks[taskCount] = new Event(taskDescription, taskAdditional);
        }
        boolean done = true;
        if (taskState.equals(" ")) {
            done = false;
        }
        tasks[taskCount].setCompletion(done);
        taskCount++;
    }
}
