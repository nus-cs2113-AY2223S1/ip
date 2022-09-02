public class TaskManager {
    private static final int MAX_TASK = 100;
    private final Task[] tasks;
    private int taskCount;

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
            System.out.println("Maximum number of tasks reached");
        }
    }

    public void listTasks() {
        if (taskCount > 0) {
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s\n", i + 1, tasks[i]);
            }
        } else {
            System.out.println("There are no tasks added yet. Type 'help' if you need help.");
        }
    }

    public void markTaskAsDone(String input) {
        String[] inputWords = input.split(" ", 2);
        try {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            tasks[taskIndex].markAsDone();
            System.out.printf("Marked as done: %s\n", tasks[taskIndex]);
        } catch (Exception exception) {
            System.out.println("Invalid input. Task could not be marked as done.");
        }
    }

    public void markTaskAsUndone(String input) {
        String[] inputWords = input.split(" ", 2);
        try {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            tasks[taskIndex].unmarkDone();
            System.out.printf("Unmarked done: %s\n", tasks[taskIndex]);
        } catch (Exception exception) {
            System.out.println("Invalid input. Task could not be unmarked.");
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
