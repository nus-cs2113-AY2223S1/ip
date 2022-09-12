package duke;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
                task.print(), taskList.size());
    }

    public  static  void todo(String line, String[] words) {
        if (words.length >= 2) {
            String task = line.substring(line.indexOf(' ') + 1);
            Todo todo = new Todo(task);
            addTask(todo);
        } else {
            System.out.println("Usage: todo <task>");
        }
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

    public static void list() throws ArrayEmptyException {
        if (taskList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, taskList.get(i).print());
            }
        } else {
            throw new ArrayEmptyException();
        }
    }

    public static void mark(boolean isMark, String[] words) {
        if (words.length == 2) {
            try {
                int d = Integer.parseInt(words[1]);
                if (d > 0 && d <= taskList.size()) { // Valid
                    if (taskList.get(d-1).getStatus()) { // Task is done
                        if (isMark) {
                            System.out.printf("This task is already done:\n" + "%s\n",
                                    taskList.get(d - 1).print());
                        } else {
                            taskList.get(d - 1).setDone(false);
                            System.out.printf("OK, I've marked this task as not done yet:\n" + "%s\n",
                                    taskList.get(d - 1).print());
                        }
                    } else { // Task is not done
                        if (isMark) {
                            taskList.get(d - 1).setDone(true);
                            System.out.printf("Nice! I've marked this task as done:\n" + "%s\n",
                                    taskList.get(d - 1).print());
                        } else {
                            System.out.printf("This task is already not done:\n" + "%s\n",
                                    taskList.get(d - 1).print());
                        }
                    }
                } else { // Invalid index
                    if (taskList.size() == 0) {
                        System.out.println("Error: list is empty");
                    } else {
                        System.out.printf("Error: enter an integer between 1 - %d\n", taskList.size());
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Usage: mark <integer>");
            }
        } else {
            System.out.println("Usage: mark <integer>");
        }
    }

    public static void delete(String[] words) {
        if (words.length == 2) {
            if (taskList.size() > 0) {
                try {
                    int d = Integer.parseInt(words[1]);
                    Task tempTask = taskList.get(d - 1);
                    taskList.remove(d - 1);
                    System.out.printf("Noted. I've removed this task:\n" + "%s\n" +
                            "Now you have %d tasks in the list.\n", tempTask.print(), taskList.size());
                } catch (IndexOutOfBoundsException ex) {
                    System.out.printf("Error: enter an integer between 1 - %d\n", taskList.size());
                } catch (NumberFormatException ex) {
                    System.out.println("Usage: delete <integer>");
                }
            } else {
                System.out.println("Error: list is empty!");
            }
        } else {
            System.out.println("Usage: delete <integer>");
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
                if (words.length == 0) {
                    System.out.println("Enter a valid command.");
                    printLine();
                    continue;
                }
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
                case "delete":
                    delete(words);
                    break;
                default:
                    System.out.println("Oops! I don't understand that command. Try again.");
                    break;
                }
            } else {
                System.out.println("Oops! I don't understand that command. Try again.");
            }
            printLine();
        }
    }
}
