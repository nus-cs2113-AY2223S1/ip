package task;

import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();
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
            System.out.println("Marked this task: \n " + tasks.get(target - 1));
        } else {
            System.out.println("Unmarked this task: \n" + tasks.get(target - 1));
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void addDeadLine(Deadline deadline) {
        tasks.add(deadline);
        taskCount++;
        printRoger(deadline);
    }

    public static void addTodo(Todo todo) {
        tasks.add(todo);
        taskCount++;
        printRoger(todo);
    }

    public static void addEvent(Event event) {
        tasks.add(event);
        taskCount++;
        printRoger(event);
    }

    public static void markTarget(int target) {
        tasks.get(target - 1).setDone(true);
        printMark(target, true);
    }

    public static void unmarkTarget(int target) {
        tasks.get(target - 1).setDone(false);
        printMark(target, false);
    }

    public static void printList() {
        System.out.println("Here are a list of your tasks: ");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + ". " + task);
            index++;
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void deleteTask(int index) {
        System.out.println(tasks.get(index - 1) + " has been removed");
        tasks.remove(index - 1);
        taskCount--;
        System.out.println("There are " + taskCount + "tasks left");
        System.out.println(LINE_DIVIDER);
    }
}
