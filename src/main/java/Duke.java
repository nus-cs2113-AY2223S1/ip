import java.util.Scanner;


public class Duke {

    public static int listSize = 0;
    public static Task[] taskList = new Task[100];

    public static void addTask(Task task) {
        taskList[listSize] = task;
        listSize++;
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
                task.print(), listSize);
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void list() {
        if (listSize > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < listSize; i++) {
                System.out.printf("%d. %s\n",
                        i + 1, taskList[i].print());
            }
        } else {
            System.out.println("No items in list! Type something to add to list.");
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

    public  static  void todo(String line) {
        String task = line.substring(line.indexOf(' ')+1);
        Todo todo = new Todo(task);
        addTask(todo);
    }

    public static void deadline(String line) {
        try {
            String task = line.substring(line.indexOf(' ')+1, line.indexOf("/by")-1);
            String date = line.substring(line.indexOf("/by")+4);
            Deadline deadline = new Deadline(task, date);
            addTask(deadline);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Usage: deadline <task> /by <date>");
        }
    }

    public static void event(String line) {
        try {
            String task = line.substring(line.indexOf(' ')+1, line.indexOf("/at")-1);
            String date = line.substring(line.indexOf("/at")+4);
            Event event = new Event(task, date);
            addTask(event);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Usage: event <task> /at <date>");
        }
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        // Greet user
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        // Wait for input
        while (true) {
            line = in.nextLine();
            System.out.println("____________________________________________________________");
            if (line.equals("bye")) {
                bye();
                break;
            } else if (line.equals("list")) {
                list();
            } else if (!line.equals("")) {
                String[] words = line.split("\\s+");
                switch (words[0]) {
                case "mark":
                    mark(true, words);
                    break;
                case "unmark":
                    mark(false, words);
                    break;
                case "todo":
                    todo(line);
                    break;
                case "deadline":
                    deadline(line);
                    break;
                case "event":
                    event(line);
                    break;
                }
            }
            System.out.println("____________________________________________________________");
        }
    }
}
