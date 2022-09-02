import java.util.Scanner;

public class Duke {
    public static Task[] tasks = new Task[100];
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

    public static void addTask(Task currentTask) {
        tasks[position] = currentTask;
        position++;

        printDivider();
        System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
        System.out.print("\t  ");
        System.out.println(currentTask);
        System.out.println("\tNow you have " + Integer.toString(position) + " tasks in the list! 凸(￣ヘ￣)");
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
        Task currentTask = tasks[Integer.parseInt(index) - 1];
        currentTask.markAsDone();

        printDivider();
        System.out.println("\tNice! (〃＾▽＾〃) I've marked this task as done:");
        System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
        System.out.println("\tWell done completing your task, friend! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
        System.out.println("");
        printDivider();
    }

    public static void markAsUndone(String index) {
        Task currentTask = tasks[Integer.parseInt(index) - 1];
        currentTask.markAsUndone();

        printDivider();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
        System.out.println("\tYou better stop procrastinating, friend.. (╥﹏╥)");
        System.out.println("");
        printDivider();
    }

    public static void main(String[] args) {
        printGreeting();

        String userInput = "";

        while (!userInput.equals("bye")) {
            //Input the task from the user
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            String words[] = userInput.split(" ");
            //Mark a certain task as done
            if (words[0].equals("mark")) {
                markAsDone(words[1]);
                //Unmark a certain task as not done
            } else if (words[0].equals("unmark")) {
                markAsUndone(words[1]);
            } else {
                //Print the full list of tasks
                if (userInput.equals("list")) {
                    printList();
                } else {
                    switch(words[0]) {
                    case "todo" :
                        Task todo = new Todo(userInput.substring(5));
                        addTask(todo);
                        break;
                    case "deadline" :
                        String deadlineWords[] = userInput.substring(8).split("/");
                        Task deadline = new Deadline(deadlineWords[0].trim(), deadlineWords[1].substring(3));
                        addTask(deadline);
                        break;
                    case "event" :
                        String eventWords[] = userInput.substring(6).split("/");
                        Task event = new Event(eventWords[0].trim(), eventWords[1].substring(3));
                        addTask(event);
                        break;
                    }
                }
            }
        }

        printExitMessage();
    }
}
