package duke;

import java.util.Scanner;

public class Duke {
    static final int MAX_TASKS = 100;
    static final String EXCEPTION_1 = "Empty Description";
    static final String EXCEPTION_2 = "Other Command";
    static final String EXCEPTION_3 = "Mark/Unmark Out of Bounds";

    public static Task[] tasks = new Task[MAX_TASKS];
    public static int position = 0;

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
            //Mark/Unmark index out of bounds
            System.out.println("\t☹ OOPS!!! Please provide a valid task index...");
        }

        System.out.println();
        printDivider();
    }

    public static void performAction(String[] words, String firstWord, String command) throws DukeException{
        if (firstWord.equals("mark")) {
            //Mark task as done
            markAsDone(words[1]);
        } else if (firstWord.equals("unmark")) {
            //Mark task as undone
            markAsUndone(words[1]);
        } else if (firstWord.equals("list")) {
            //Print the list
            printList();
        } else if (firstWord.equals("todo") | firstWord.equals("deadline") | firstWord.equals("event")) {
            //Add to-do, deadline, or event to list
            try {
                Task currentTask = createTask(command, words[0], words);
                addTask(currentTask);
            } catch (DukeException e) {
                printExceptionMessage(EXCEPTION_1);
            }
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
        tasks[position] = currentTask;
        position++;

        printDivider();
        System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
        System.out.print("\t  ");
        System.out.println(currentTask);
        System.out.println("\tNow you have " + Integer.toString(position) + " task(s) in the list! 凸(￣ヘ￣)");
        System.out.println("");
        printDivider();
    }

    public static void printList() {
        printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < position; i++) {
            System.out.print("\t" + Integer.toString(i + 1) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println();
        printDivider();
    }

    public static void markAsDone(String index) {
        try {
            Task currentTask = tasks[Integer.parseInt(index) - 1];
            currentTask.markAsDone();

            printDivider();
            System.out.println("\tNice! (〃＾▽＾〃) I've marked this task as done:");
            System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
            System.out.println("\tWell done completing your task, friend! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
            System.out.println("");
            printDivider();
        } catch (NullPointerException e) {
            printExceptionMessage(EXCEPTION_3);
        }
    }

    public static void markAsUndone(String index) {
        try {
            Task currentTask = tasks[Integer.parseInt(index) - 1];
            currentTask.markAsUndone();

            printDivider();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
            System.out.println("\tYou better stop procrastinating, friend.. (╥﹏╥)");
            System.out.println("");
            printDivider();
        } catch (NullPointerException e) {
            printExceptionMessage(EXCEPTION_3);
        }
    }

    public static void main(String[] args) {
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
