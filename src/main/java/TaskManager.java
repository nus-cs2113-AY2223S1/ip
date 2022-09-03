public abstract class TaskManager {
    private static final int MAX_NUM_OF_TASKS = 100;
    private static Task[] tasks = new Task[MAX_NUM_OF_TASKS];

    public static void addTask(String type, String input) {
        final String ADD_PHRASE_START = "added: ";
        // add one because newTask has not been created yet
        final String ADD_PHRASE_END =
                "There are " + Integer.toString(Task.getTaskCount() + 1) + " task(s) in your list.";

        Task newTask;
        try {
            newTask = Parser.parseTask(type, input);
        } catch (InvalidTodoDescriptionException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        } catch (InvalidDeadlineDescriptionException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        } catch (InvalidEventDescriptionException e) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }

        // change to zero-index
        tasks[Task.getTaskCount() - 1] = newTask;

        System.out.println(ADD_PHRASE_START + newTask);
        System.out.println(ADD_PHRASE_END);
    }

    public static void printList() {
        for (int i = 0; i < Task.getTaskCount(); i += 1) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks[i]);
        }
    }

    public static void markTask(String input) {
        int taskNum = Parser.parseTaskNumber(InputManager.MARK_PHRASE, input);

        tasks[taskNum].setDone();

        System.out.println("Completed! The following task is marked as done:");
        System.out.println(tasks[taskNum]);
    }

    public static void unmarkTask(String input) {
        int taskNum = Parser.parseTaskNumber(InputManager.UNMARK_PHRASE, input);

        tasks[taskNum].setUndone();

        System.out.println("Oh no! The following task is marked as undone:");
        System.out.println(tasks[taskNum]);
    }
}
