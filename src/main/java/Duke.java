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

    public static void addTodoToList(Todo currentTodo) {
        tasks[position] = currentTodo;
        position++;

        printDivider();
        System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
        System.out.print("\t  ");
        System.out.println(currentTodo);
        System.out.println("\tNow you have " + Integer.toString(position) + " tasks in the list! 凸(￣ヘ￣)");
        System.out.println("");
        printDivider();
    }

    public static void addDeadlineToList(Deadline currentDeadline) {
        tasks[position] = currentDeadline;
        position++;

        printDivider();
        System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
        System.out.print("\t  ");
        System.out.println(currentDeadline);
        System.out.println("\tNow you have " + Integer.toString(position) + " tasks in the list! 凸(￣ヘ￣)");
        System.out.println("");
        printDivider();
    }

    public static void addEventToList(Event currentEvent) {
        tasks[position] = currentEvent;
        position++;

        printDivider();
        System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
        System.out.print("\t  ");
        System.out.println(currentEvent);
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
        //Modify this
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
        //Modify this
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
            //Input
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            String words[] = userInput.split(" ");
            //Mark
            if (words[0].equals("mark")) {
                markAsDone(words[1]);
                //Unmark
            } else if (words[0].equals("unmark")) {
                markAsUndone(words[1]);
            } else {
                //Print List
                if (userInput.equals("list")) {
                    printList();
                } else {
                    //Todo
                    if (words[0].equals("todo")) {
                        Todo todo = new Todo(userInput.substring(5));
                        addTodoToList(todo);
                        //Deadline
                    } else if (words[0].equals("deadline")) {
                        String deadlineWords[] = userInput.substring(8).split("/");
                        Deadline deadline = new Deadline(deadlineWords[0].trim(), deadlineWords[1].substring(3));
                        addDeadlineToList(deadline);
                        //Event
                    } else if (words[0].equals("event")) {
                        String eventWords[] = userInput.substring(6).split("/");
                        Event event = new Event(eventWords[0].trim(), eventWords[1].substring(3));
                        addEventToList(event);
                    }
                }
            }
        }

        printExitMessage();
    }
}
