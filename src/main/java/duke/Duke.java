package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void printIntroMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

//    private static void printFileContents(String filePath) throws FileNotFoundException {
//        File f = new File(filePath); // create a File for the given file path
//        Scanner s = new Scanner(f); // create a Scanner using the File as the source
//        while (s.hasNext()) {
//            System.out.println(s.nextLine());
//        }
//    }

//    private static void writeToFile(String filePath, String textToAdd) throws IOException {
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(textToAdd);
//        fw.close();
//    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        // printIntroMessage();

        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // attempts to find file and add to list. if not found, creates a new file in a new directory
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] task = s.nextLine().split(" \\| ");
                switch (task[0]) {
                case "T":
                    tasks.add(new Todo(task[2]));
                    // print out id of task
                    System.out.println(tasks.size());
                    if (task[1].equals("1")) {
                        tasks.get(tasks.size() - 1).setDone(tasks.get(tasks.size() - 1).isDone);
                    }
                    break;
                case "D":
                    try {
                        tasks.add(new Deadline(task[2], task[3]));
                        System.out.println(tasks.size());
                        if (task[1].equals("1")) {
                            tasks.get(tasks.size() - 1).setDone(tasks.get(tasks.size() - 1).isDone);
                        }
                    } catch (DukeException e) {
                        e.printStackTrace();
                    }
                    break;
                case "E":
                    try {
                        tasks.add(new Event(task[2], task[3]));
                        System.out.println(tasks.size());
                        if (task[1].equals("1")) {
                            tasks.get(tasks.size() - 1).setDone(tasks.get(tasks.size() - 1).isDone);
                        }
                    } catch (DukeException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            File dir = new File("data");
            dir.mkdir();
            File f = new File("data/duke.txt");
            try {
                f.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        printIntroMessage();

        // if list has tasks in it, print them out
        if (tasks.size() > 0) {
            System.out.println("Here are the current tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        } else {
            System.out.println("You have no tasks in your list. Try adding some!");
        }

        do {
            line = in.nextLine();
            String type = TaskManager.getTaskType(line);

            if (type.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                clearCurrentList();
                saveNewList(tasks);
            }
            switch (type) {
            case "list":
                TaskManager.printTaskList(tasks);
                break;
            case "delete":
                try {
                    TaskManager.deleteTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("\tPlease input the task number that you want to delete.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThere is no such item in your Task List.");
                }
                break;
            case "mark":
                try {
                    TaskManager.markTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("\tPlease input the task number that you want to mark.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThere is no such item in your Task List.");
                }
                break;
            case "unmark":
                try {
                    TaskManager.unmarkTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("\tPlease input the task number that you want to mark.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThere is no such item in your Task List.");
                }
                break;
            case "total":
                int total = tasks.size();
                if (total == 1) {
                    System.out.println("\tYou have 1 task");
                } else {
                    System.out.println("\tYou have " + total + " tasks!!!");
                }
                break;
            case "todo":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    Task t = new Todo(details);
                    tasks.add(t);
                    TaskManager.printSuccessfulAdd(tasks);
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty. Please tell me what you want to do");
                    System.out.println("\tExample: todo (return book)");
                }
                break;
            case "deadline":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    String[] breakBy = details.split("/by", 2);
                    String detail = breakBy[0];
                    String by = breakBy[1];
                    Task d = new Deadline(detail, by);
                    tasks.add(d);
                    TaskManager.printSuccessfulAdd(tasks);
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\tPlease tell me when is the deadline.");
                    System.out.println("\tExample: deadline (return book) /by (Sunday)");
                }
                break;
            case "event":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    String[] breakAt = details.split("/at", 2);
                    String detail = breakAt[0];
                    String at = breakAt[1];
                    Task e = new Event(detail, at);
                    tasks.add(e);
                    TaskManager.printSuccessfulAdd(tasks);
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\tPlease tell me when is the event.");
                    System.out.println("\tExample: event (borrow book) /at (library)");
                }
                break;
            default:
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } while (!line.equals("bye"));
    }

    private static void saveNewList(ArrayList<Task> tasks) {
        try {
            for (Task task : tasks) {
                // if task is Todo type, append to file T | 0/1 | task description
                String boolValue = getBoolValue(task);
                if (task instanceof Todo) {
                    appendToFile("T | " + boolValue + " | " + task.description + System.lineSeparator());
                }
                // if task is Deadline type, append to file D | 0/1 | task description | by: deadline
                if (task instanceof Deadline) {
                    appendToFile("D | " + boolValue + " | " + task.description + " | " + ((Deadline) task).by + System.lineSeparator());
                }
                // if task is Event type, append to file E | 0/1 | task description | at: event time
                if (task instanceof Event) {
                    appendToFile("E | " + boolValue + " | " + task.description + " | " + ((Event) task).at + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearCurrentList() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getBoolValue(Task task) {
        String boolValue;
        if (task.isDone()) {
            boolValue = "1";
        } else {
            boolValue = "0";
        }
        return boolValue;
    }
}



