package task;

public class TaskList {

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static final String LINE_DIVIDER = "-------------------------------------";
    private static final String ROGER_MESSAGE = "Got it. I have added this task:";

    private static void printRoger(Task task) {
        System.out.println(ROGER_MESSAGE);
        System.out.println(task);
        System.out.println("You now have " + taskCount + " tasks.");
        System.out.println(LINE_DIVIDER);
    }

    private static void printMark(int target, boolean mark) {
        if (mark) {
            System.out.println("Marked this task: \n " + tasks[target - 1]);
        } else {
            System.out.println("Unmarked this task: \n" + tasks[target - 1]);
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void addDeadLine(Deadline deadline) {
        tasks[taskCount] = deadline;
        taskCount++;
        printRoger(deadline);
    }

    public static void addTodo(Todo todo) {
        tasks[taskCount] = todo;
        taskCount++;
        printRoger(todo);
    }

    public static void addEvent(Event event) {
        tasks[taskCount] = event;
        taskCount++;
        printRoger(event);
    }

    public static void markTarget(int target) {
        tasks[target - 1].setDone(true);
        printMark(target, true);
    }

    public static void unmarkTarget(int target) {
        tasks[target - 1].setDone(false);
        printMark(target, false);
    }

    public static void printList() {
        System.out.println("Here are a list of your tasks: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println(LINE_DIVIDER);
    }
}
