public class Menu {
    private Task[] tasks;
    private int taskCount;
    private static final String DIVIDER = "____________________________________________________________";
    private static final int MAX_TASK_SIZE = 100;
    private static final int INITIAL_TASK_COUNT = 0;
    private static final boolean IS_MARK_METHOD = true;
    private static final boolean IS_UNMARK_METHOD = false;

    public Menu() {
        this.tasks = new Task[MAX_TASK_SIZE];
        this.taskCount = INITIAL_TASK_COUNT;
    }

    public static void greet() {
        displayGreetingMessage();
    }

    public void list() {
        String listContent = "";
        for (int i = 0; i < taskCount; i++) {
            listContent += String.format("%d.%s", i + 1, tasks[i].getTaskFullDetails());
            listContent += System.lineSeparator();
        }
        displayListingMessage(listContent);
    }

    public void addTask(String taskType, String userInput) {
        String taskName;
        String[] split;
        switch (taskType) {
        case "todo":
            taskName = userInput;
            tasks[taskCount] = new Todo(taskName);
            break;
        case "deadline":
            split = splitTaskName(" /by ", userInput);
            // Task name: split[0], deadline: split[1]
            tasks[taskCount] = new Deadline(split[0], split[1]);
            break;
        case "event":
            split = splitTaskName(" /at ", userInput);
            // Task name: split[0], deadline: split[1]
            tasks[taskCount] = new Event(split[0], split[1]);
            break;
        default:
            displayErrorMessage();
        }
        taskCount++;
        displayTaskAdditionMessage(tasks[taskCount - 1].getTaskFullDetails(), taskCount);
    }

    public void mark(String inputValue) {
        if (isCorrectMarkUnmarkFormat(inputValue)) {
            int taskIndex = Integer.parseInt(inputValue);
            tasks[taskIndex - 1].setDone(true);
            String taskName = tasks[taskIndex - 1].getTaskName();
            displayMarkOrUnmarkMessage(taskName, IS_MARK_METHOD);
        } else {
            this.displayErrorMessage();
        }
    }

    public void unmark(String inputValue) {
        if (isCorrectMarkUnmarkFormat(inputValue)) {
            int taskIndex = Integer.parseInt(inputValue);
            this.tasks[taskIndex - 1].setDone(false);
            String taskName = this.tasks[taskIndex - 1].getTaskName();
            displayMarkOrUnmarkMessage(taskName, IS_UNMARK_METHOD);
        } else {
            this.displayErrorMessage();
        }
    }

    public void quit() {
        displayExitMessage();
    }

    private static void displayGreetingMessage() {
        String output = DIVIDER + System.lineSeparator()
                + "Hello! I'm Duke" + System.lineSeparator()
                + "What can I do for you?" + System.lineSeparator()
                + DIVIDER;
        System.out.println(output);
    }

    private static void displayListingMessage(String listContent) {
        String output = DIVIDER + System.lineSeparator()
                + "Here are the tasks in your list:" + System.lineSeparator()
                + listContent
                + DIVIDER;
        System.out.println(output);
    }

    private static void displayTaskAdditionMessage(String taskDetails, int count) {
        String output = DIVIDER + System.lineSeparator()
                + "Got it. I've added this task: " + System.lineSeparator()
                + "\t" + taskDetails + System.lineSeparator()
                + "Now you have " + count + " tasks in the list" + System.lineSeparator()
                + DIVIDER;
        System.out.println(output);
    }

    private static void displayMarkOrUnmarkMessage(String taskName, boolean isMarkMethod) {
        String output = DIVIDER + System.lineSeparator();
        if (isMarkMethod) {
            output += "Nice! I've marked this task as done:" + System.lineSeparator() + "\t[X] ";
        } else {
            output += "OK, I've marked this task as not done yet:" + System.lineSeparator() + "\t[ ] ";
        }
        output += taskName + System.lineSeparator() + DIVIDER;
        System.out.println(output);
    }

    private static void displayExitMessage() {
        String output = DIVIDER + System.lineSeparator()
                + "Bye. Hope to see you again soon!" + System.lineSeparator()
                + DIVIDER;
        System.out.println(output);
    }

    public void displayErrorMessage() {
        String output = DIVIDER + System.lineSeparator()
                + "Invalid input!" + System.lineSeparator()
                + DIVIDER;
        System.out.println(output);
    }

    public boolean isCorrectMarkUnmarkFormat(String inputValue) {
        try {
            int taskIndex = Integer.parseInt(inputValue);
            if (taskIndex > 0 && taskIndex <= this.taskCount) {
                return true;
            } else {
                // Invalid input format for mark and unmark feature
                return false;
            }
        } catch (NumberFormatException exception) {
            // Invalid input format for mark and unmark feature
            return false;
        }
    }

    public static String[] splitTaskName(String regex, String userInput) {
        String[] split = userInput.split(regex, 2);
        if (split.length == 1) {
            return new String[]{split[0], ""};
        }
        return split;
    }
}
