package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    static final int MAX_TASKS = 100;
    static final String EXCEPTION_1 = "Empty Description";
    static final String EXCEPTION_2 = "Other Command";
    static final String EXCEPTION_3 = "Mark/Unmark Out of Bounds";
    static final String EXCEPTION_4 = "File Not Found";

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numberOfTasks = 0;

    public static void printDivider() {
        System.out.println("\t----------------------------------------------------");
    }

    public static void printGreeting() {
        printDivider();
        System.out.println("\tHello! I'm Albot, your new best friend! ᕙ(`▿´)ᕗ");
        System.out.println("\tWhat can I do for you today, friend? ʕ•́ᴥ•̀ʔっ♡");
        System.out.println();
        printDivider();
    }

    public static void printExitMessage() {
        printDivider();
        System.out.println("\tAww, you're leaving already? I'm gonna miss you (╥﹏╥)");
        System.out.println("\tBut anyways bye! Hope to see you again soon, friend! (ɔ◔‿◔)ɔ ♥");
        System.out.println();
        printDivider();
    }

    public static void printExceptionMessage(String exceptionType) {
        printDivider();

        switch (exceptionType) {
        case EXCEPTION_1 :
            //Empty description of a task
            System.out.println("\t☹ OOPS!!! The description of a task cannot be empty...");
            break;
        case EXCEPTION_2 :
            //Commands other than the ones recognized
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means...");
            break;
        case EXCEPTION_3 :
            //Mark/Unmark/Delete index out of bounds
            System.out.println("\t☹ OOPS!!! Please provide a valid task index...");
        case EXCEPTION_4:
            //File not found
            System.out.println("\t☹ OOPS!!! Unfortunately file is not found...");
        }

        System.out.println();
        printDivider();
    }

    public static void readFile() throws FileNotFoundException {
        File file = new File("files/duke.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            String[] commandWords = line.substring(2).split(" ");
            try {
                Task currentTask = createTask(line.substring(2), commandWords[0], commandWords);
                addTask(currentTask, true);
                if (words[0].equals("1")) {
                    markAsDone(String.valueOf(numberOfTasks), true);
                }
            } catch (DukeException e) {
                printExceptionMessage(EXCEPTION_2);
            }
        }
    }

    public static void appendToFile(String command) throws IOException {
        FileWriter fw = new FileWriter("files/duke.txt", true);
        fw.write("0 " + command + "\n");
        fw.close();
    }

    public static void rewriteFile() throws IOException {
        FileWriter fw = new FileWriter("files/duke.txt");
        String number;
        for (Task task : tasks) {
            if (task.isDone) {
                number = "1";
            } else {
                number = "0";
            }
            if (task instanceof Todo) {
                fw.write(number + " todo " + task.description + "\n");
            } else if (task instanceof Deadline) {
                fw.write(number + " deadline " + task.description + " /by " + ((Deadline) task).by + "\n");
            } else if (task instanceof Event) {
                fw.write(number + " event " + task.description + " /at " + ((Event) task).at + "\n");
            }
        }
        fw.close();
    }

    public static void performAction(String[] words, String firstWord, String command) throws DukeException{
        if (firstWord.equals("mark")) {
            //Mark task as done
            markAsDone(words[1], false);
        } else if (firstWord.equals("unmark")) {
            //Mark task as undone
            markAsUndone(words[1], false);
        } else if (firstWord.equals("list")) {
            //Print the list
            printList();
        } else if (firstWord.equals("todo") | firstWord.equals("deadline") | firstWord.equals("event")) {
            //Add a task to the list
            try {
                Task currentTask = createTask(command, words[0], words);
                addTask(currentTask, false);
                appendToFile(command);
            } catch (DukeException e) {
                printExceptionMessage(EXCEPTION_1);
            } catch (IOException e) {
                printExceptionMessage(EXCEPTION_4);
            }
        } else if (firstWord.equals("delete")) {
            //Remove a task from the list
            removeTask(Integer.parseInt(words[1]) - 1);
            try {
                rewriteFile();
            } catch (IOException e) {
                printExceptionMessage(EXCEPTION_4);
            }
        } else if (firstWord.equals("delete")) {
            //Remove a task from the list
            removeTask(Integer.parseInt(words[1]) - 1);
        } else {
            throw new DukeException();
        }
    }

    public static Task createTask(String command, String type, String[] words) throws DukeException{
        //Handle empty description exception
        if (words.length == 1) {
            throw new DukeException();
        }

        Task newTask = new Task("");

        switch (type) {
        case "todo" :
            newTask = new Todo(command.substring(5));
            break;
        case "deadline" :
            String deadlineWords[] = command.substring(8).split("/");
            newTask = new Deadline(deadlineWords[0].trim(), deadlineWords[1].substring(3));
            break;
        case "event" :
            String eventWords[] = command.substring(6).split("/");
            newTask = new Event(eventWords[0].trim(), eventWords[1].substring(3));
            break;
        }
        return newTask;
    }

    public static void addTask(Task currentTask) {
        tasks.add(currentTask);
        numberOfTasks++;

        printDivider();
        System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
        System.out.print("\t  ");
        System.out.println(currentTask);
        System.out.println("\tNow you have " + numberOfTasks + " task(s) in the list! 凸(￣ヘ￣)");
        System.out.println("");
        printDivider();
    }

    public static void addTask(Task currentTask, boolean isFile) {
        tasks.add(currentTask);
        numberOfTasks++;

        if (!isFile) {
            printDivider();
            System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
            System.out.print("\t  ");
            System.out.println(currentTask);
            System.out.println("\tNow you have " + Integer.toString(numberOfTasks) + " task(s) in the list! 凸(￣ヘ￣)");
            System.out.println("");
            printDivider();
        }
    }
    
    public static void removeTask(int index) {
        try {
            Task removedTask = tasks.get(index);
            tasks.remove(index);
            numberOfTasks--;

            printDivider();
            System.out.println("\tNoted! (๑˃ᴗ˂)ﻭ I've removed this task:");
            System.out.print("\t  ");
            System.out.println(removedTask);
            System.out.println("\tNow you have " + numberOfTasks + " task(s) in the list! 凸(￣ヘ￣)");
            System.out.println("");
            printDivider();
        } catch (IndexOutOfBoundsException e) {
            printExceptionMessage(EXCEPTION_3);
        }
    }

    public static void printList() {
        printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println();
        printDivider();
    }

    public static void markAsDone(String index, boolean isFile) {
        try {
            Task currentTask = tasks.get(Integer.parseInt(index) - 1);
            currentTask.markAsDone();
            rewriteFile();

            if (!isFile) {
                printDivider();
                System.out.println("\tNice! (〃＾▽＾〃) I've marked this task as done:");
                System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
                System.out.println("\tWell done completing your task, friend! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
                System.out.println("");
                printDivider();
            }
        } catch (NullPointerException e) {
            printExceptionMessage(EXCEPTION_3);
        } catch (IOException e) {
            printExceptionMessage(EXCEPTION_4);
        }
    }

    public static void markAsUndone(String index, boolean isFile) {
        try {
            Task currentTask = tasks.get(Integer.parseInt(index) - 1);
            currentTask.markAsUndone();
            rewriteFile();

            if (!isFile) {
                printDivider();
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
                System.out.println("\tYou better stop procrastinating, friend.. (╥﹏╥)");
                System.out.println("");
                printDivider();
            }
        } catch (NullPointerException e) {
            printExceptionMessage(EXCEPTION_3);
        } catch (IOException e) {
            printExceptionMessage(EXCEPTION_4);
        }
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (FileNotFoundException e) {
            printExceptionMessage(EXCEPTION_4);
        }

        printGreeting();

        String userInput = "";

        while (!userInput.equals("bye")) {
            //Input the task from the user
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            //Exit the loop immediately if the user input is "bye"
            if (userInput.equals("bye")) {
                break;
            }
            String[] words = userInput.split(" ");
            try {
                performAction(words, words[0], userInput);
            } catch (DukeException e) {
                printExceptionMessage(EXCEPTION_2);
            }
        }

        printExitMessage();
    }
}
