import java.util.Scanner;

public class Duke {
    public static Task[] list = new Task[100];
    public static int position = 0;

    public static void printDivider() {
        System.out.println("    ----------------------------------------------------");
    }

    public static void printGreeting() {
        printDivider();
        System.out.println("    Hello! I'm Albot, your new best friend! ᕙ(`▿´)ᕗ");
        System.out.println("    What can I do for you today, friend? ʕ•́ᴥ•̀ʔっ♡");
        System.out.println();
        printDivider();
    }

    public static void printExitMessage() {
        printDivider();
        System.out.println("    Aww, you're leaving already? I'm gonna miss you (╥﹏╥)");
        System.out.println("    But anyways bye! Hope to see you again soon, friend! (ɔ◔‿◔)ɔ ♥");
        System.out.println();
        printDivider();
    }

    public static void addToList(Task currentTask) {
        list[position] = currentTask;
        position++;

        printDivider();
        System.out.println("    added: " + currentTask.description);
        System.out.println();
        printDivider();
    }

    public static void printList() {
        printDivider();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < position; i++) {
            System.out.println("    " + String.valueOf(i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
        }
        System.out.println();
        printDivider();
    }

    public static void markAsDone(String index) {
        Task currentTask = list[Integer.parseInt(index) - 1];
        currentTask.markAsDone();

        printDivider();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [" + currentTask.getStatusIcon() + "] " + currentTask.description);
        System.out.println("");
        printDivider();
    }

    public static void markAsUndone(String index) {
        Task currentTask = list[Integer.parseInt(index) - 1];
        currentTask.markAsUndone();

        printDivider();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      [" + currentTask.getStatusIcon() + "] " + currentTask.description);
        System.out.println("");
        printDivider();
    }

    public static void main(String[] args) {
        printGreeting();

        String userInput = "";

        while (!userInput.equals("bye")) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            String words[] = userInput.split(" ");
            if (words[0].equals("mark")) {
                markAsDone(words[1]);
            } else if (words[0].equals("unmark")) {
                markAsUndone(words[1]);
            } else {
                Task task = new Task(userInput);
                if (userInput.equals("list")) {
                    printList();
                } else {
                    addToList(task);
                }
            }
        }

        printExitMessage();
    }
}
