public class TaskManager {
    private static final int MAX_TASK = 100;
    private final Task[] tasks;
    private int taskCount;
    /* Messages */
    private static final String HELP_MESSAGE = "Type 'help' if you need help.";
    private static final String NO_TASKS_MESSAGE = "There are no tasks added yet.";
    private static final String MAXIMUM_TASKS_REACHED_ERROR_MESSAGE = "Maximum number of tasks reached";
    private static final String WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE = "Sorry, task number should lies between 1 to " +
            MAX_TASK + " tasks stored. Type \"list\" for viewing all the tasks.";
    private static final String WRONG_TASK_NUMBER_ERROR_MESSAGE = "Sorry, the selected task has not been created yet. Type \"list\" to see the task numbers.";
    private static final String MISSING_TASK_NUMBER_ERROR_MESSAGE = "Sorry, you have not provide the task number.";
    private static final String WRONG_TASK_NUMBER_FORMAT_ERROR_MESSAGE = "Invalid input. Please type an integer for the task number.";
    /* End Messages */

    public TaskManager() {
        tasks = new Task[MAX_TASK];
        taskCount = 0;
    }

    private void addTask(Task task) {
        if (taskCount < MAX_TASK) {
            tasks[taskCount++] = task;
            System.out.println("Task added: " + task);
        }
        else {
            System.out.println(MAXIMUM_TASKS_REACHED_ERROR_MESSAGE);
        }
    }

    public void listTasks() {
        if (taskCount > 0) {
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s\n", i + 1, tasks[i]);
            }
        } else {
            System.out.println(NO_TASKS_MESSAGE + " " + HELP_MESSAGE);
        }
    }

    private int getTaskIndex(String input) {
        try {
            String[] inputWords = input.split(" ", 2);
            return Integer.parseInt(inputWords[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(MISSING_TASK_NUMBER_ERROR_MESSAGE);
            return -1;
        } catch (NumberFormatException e) {
            System.out.println(WRONG_TASK_NUMBER_FORMAT_ERROR_MESSAGE);
            return -1;
        }
    }

    public void markTaskAsDone(String input) {
        int taskIndex = getTaskIndex(input);
        if (taskIndex == -1) {
            return;
        }

        try {
            tasks[taskIndex].markAsDone();
            System.out.printf("Marked as done: %s\n", tasks[taskIndex]);
        } catch (NullPointerException e) {
            System.out.println(WRONG_TASK_NUMBER_ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    public void markTaskAsUndone(String input) {
        int taskIndex = getTaskIndex(input);
        if (taskIndex == -1) {
            return;
        }

        try {
            tasks[taskIndex].unmarkDone();
            System.out.printf("Unmarked done: %s\n", tasks[taskIndex]);
        } catch (NullPointerException e) {
            System.out.println(WRONG_TASK_NUMBER_ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    public void addTodoTask(String input) {
        try {
            String[] parameters = Todo.extractParameters(input);
            String description = parameters[0].trim();
            addTask(new Todo(description));
        } catch (Exception exception) {
            System.out.println("Invalid input, todo task could not be added");
        }
    }

    public void addDeadlineTask(String input) {
        try {
            String[] parameters = Deadline.extractParameters(input);
            String description = parameters[0].trim();
            String deadlineDate = parameters[1].trim();
            addTask(new Deadline(description, deadlineDate));
        } catch (Exception exception) {
            System.out.println("Invalid input, deadline task could not be added");
        }
    }

    public void addEventTask(String input) {
        try {
            String[] parameters = Event.extractParameters(input);
            String description = parameters[0].trim();
            String datetime = parameters[1].trim();
            addTask(new Event(description, datetime));
        } catch (Exception exception) {
            System.out.println("Invalid input, event task could not be added");
        }
    }
}
