import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int listSize = 0;
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
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (line.equals("list")) {
                if (listSize > 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listSize; i++) {
                        System.out.printf("%d.[%s] %s\n",
                                i + 1, taskList[i].getStatusIcon(), taskList[i].getDescription());
                    }
                } else {
                    System.out.println("No items in list! Type something to add to list.");
                }
            } else if (!line.equals("")) {
                String[] words = line.split("\\s+");
                if (words[0].equals("mark")) {
                    if (words.length == 2) {
                        try {
                            int d = Integer.parseInt(words[1]);
                            if (d > 0 && d <= listSize) { // Valid
                                if (taskList[d-1].getStatus()) {
                                    System.out.printf("This task is already done:\n" + "[%s] %s\n",
                                            taskList[d-1].getStatusIcon(), taskList[d-1].getDescription());
                                } else {
                                    taskList[d - 1].setDone(true);
                                    System.out.printf("Nice! I've marked this task as done:\n" + "[%s] %s\n",
                                            taskList[d - 1].getStatusIcon(), taskList[d - 1].getDescription());
                                }
                            } else {
                                if (listSize == 0) {
                                    System.out.println("Error: list is empty");
                                } else {
                                    System.out.printf("Error: enter an integer between 1 - %d\n", listSize);
                                }
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Usage: mark <integer>");
                        }
                    } else {
                        System.out.println("Usage: mark <integer>");
                    }
                } else if (words[0].equals("unmark")) {
                    if (words.length == 2) {
                        try {
                            int d = Integer.parseInt(words[1]);
                            if (d > 0 && d <= listSize) { // Valid
                                if (!taskList[d-1].getStatus()) {
                                    System.out.printf("This task is already not done:\n" + "[%s] %s\n",
                                            taskList[d-1].getStatusIcon(), taskList[d-1].getDescription());
                                } else {
                                    taskList[d - 1].setDone(false);
                                    System.out.printf("OK, I've marked this task as not done yet:\n" + "[%s] %s\n",
                                            taskList[d - 1].getStatusIcon(), taskList[d - 1].getDescription());
                                }
                            } else {
                                if (listSize == 0) {
                                    System.out.println("Error: list is empty");
                                } else {
                                    System.out.printf("Error: enter an integer between 1 - %d\n", listSize);
                                }
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Usage: unmark <integer>");
                        }
                    } else {
                        System.out.println("Usage: unmark <integer>");
                    }
                } else {
                    taskList[listSize] = new Task(line);
                    listSize++;
                    System.out.println("Added: " + line);
                }
            }
            System.out.println("____________________________________________________________");
        }
    }
}
