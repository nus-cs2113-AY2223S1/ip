package duke;
import java.util.Scanner;


public class Duke {

    public static final int MAX_SIZE = 100;

    public static int listSize = 0;
    public static Task[] taskList = new Task[MAX_SIZE];

    public static void addTask(Task task) throws ArrayOutOfBoundException {
        if (listSize < MAX_SIZE) {
            taskList[listSize] = task;
            listSize++;
            System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
                    task.print(), listSize);
        } else {
            throw new ArrayOutOfBoundException();
        }
    }

    public  static  void todo(String line, String[] words) {
        if (words.length >= 2) {
            String task = line.substring(line.indexOf(' ') + 1);
            Todo todo = new Todo(task);
            try {
                addTask(todo);
            } catch (ArrayOutOfBoundException e) {
                System.out.println("Error! Too many items in list!");
            }
        } else {
            System.out.println("Usage: todo <task>");
        }
    }

    public static void deadline(String line) {
        try {
            String task = line.substring(line.indexOf(' ')+1, line.indexOf("/by")-1);
            String date = line.substring(line.indexOf("/by")+4);
            Deadline deadline = new Deadline(task, date);
            try {
                addTask(deadline);
            } catch (ArrayOutOfBoundException e) {
                System.out.println("Error! Too many items in list!");
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Usage: deadline <task> /by <date>");
        }
    }

    public static void event(String line) {
        try {
            String task = line.substring(line.indexOf(' ')+1, line.indexOf("/at")-1);
            String date = line.substring(line.indexOf("/at")+4);
            Event event = new Event(task, date);
            try {
                addTask(event);
            } catch (ArrayOutOfBoundException e) {
                System.out.println("Error! Too many items in list!");
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Usage: event <task> /at <date>");
        }
    }

    public static void list() throws ArrayEmptyException {
        if (listSize > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < listSize; i++) {
                System.out.printf("%d. %s\n", i + 1, taskList[i].print());
            }
        } else {
            throw new ArrayEmptyException();
        }
    }

    public static void mark(boolean isMark, String[] words) {
        if (words.length == 2) {
            try {
                int d = Integer.parseInt(words[1]);
                if (d > 0 && d <= listSize) { // Valid
                    if (taskList[d-1].getStatus()) { // Task is done
                        if (isMark) {
                            System.out.printf("This task is already done:\n" + "%s\n",
                                    taskList[d - 1].print());
                        } else {
                            taskList[d - 1].setDone(false);
                            System.out.printf("OK, I've marked this task as not done yet:\n" + "%s\n",
                                    taskList[d - 1].print());
                        }
                    } else { // Task is not done
                        if (isMark) {
                            taskList[d - 1].setDone(true);
                            System.out.printf("Nice! I've marked this task as done:\n" + "%s\n",
                                    taskList[d - 1].print());
                        } else {
                            System.out.printf("This task is already not done:\n" + "%s\n",
                                    taskList[d-1].print());
                        }
                    }
                } else { // Invalid index
                    if (listSize == 0) {
                        System.out.println("Error: list is empty");
                    } else {
                        System.out.printf("Error: enter an integer between 1 - %d\n", listSize);
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Usage: mark <integer>");
            }
        } else {
            System.out.println("Usage: mark <integer>");
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        // Greet user
        printLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLine();

        // Wait for input
        while (true) {
            line = in.nextLine();
            printLine();
            if (line.equals("bye")) {
                bye();
                break;
            } else if (!line.equals("")) {
                String[] words = line.split("\\s+");
                switch (words[0]) {
                case "todo":
                    todo(line, words);
                    break;
                case "deadline":
                    deadline(line);
                    break;
                case "event":
                    event(line);
                    break;
                case "list":
                    try {
                        list();
                    } catch (ArrayEmptyException e) {
                        System.out.println("No items in list! Type something to add to list.");
                    }
                    break;
                case "mark":
                    mark(true, words);
                    break;
                case "unmark":
                    mark(false, words);
                    break;
                }
            }
            printLine();
        }
    }
}
