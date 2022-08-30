import java.util.Scanner;
public class Duke {
    public static final String MARKDONE = "mark";
    public static final String MARKUNDONE = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String BYE = "bye";

    private static void printDashLine() {
        System.out.println("__________________________________________________ \n");
    }

    private static void markDone (Task[] inputs,int itemCount, String input){
        System.out.println("Okiii... This task has been marked as done");
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARKDONE) + 5));
        if (taskNumber < itemCount) {
            inputs[taskNumber].markAsDone();
            System.out.println(inputs[taskNumber].description);
        } else {
            System.out.println("Sorryyy!!! The index is out of bound!");
        }
        printDashLine();
    }

    private static void markUnDone (Task[] inputs,int itemCount, String input){
        System.out.println("Okiii... This task has been marked as not done yet");
        int taskNumber = Integer.parseInt(input.substring(input.indexOf("unmark ") + 7));
        if (taskNumber < itemCount) {
            inputs[taskNumber].markAsUndone();
            System.out.println(inputs[taskNumber].description);
        } else {
            System.out.println("Sorryyy!!! The index is out of bound!");
        }

        printDashLine();
    }

    private static void listTasks (Task[] inputs,int itemCount){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemCount; i++) {
            System.out.println(Integer.toString(i + 1) + " " + inputs[i].toString());
        }
        printDashLine();
    }

    public static void main(String[] args) {
        String logo = "  ____                 _     \n" +
                " |  _ \\ ___  __ _  ___| |__  \n" +
                " | |_) / _ \\/ _` |/ __| '_ \\ \n" +
                " |  __/  __/ (_| | (__| | | |\n" +
                " |_|   \\___|\\__,_|\\___|_| |_|\n" +
                "                             ";
        System.out.println(logo);

        String intro = "__________________________________________________ \n"
                + "  Hello! I'm Peach  \n"
                + "  What can I do for you? \n"
                + "__________________________________________________ \n";

        System.out.println(intro);
        Task[] inputs = new Task[100];
        int itemCount = 0;

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();


        while (input.equals(BYE) == false) {
            printDashLine();
            if (input.equals(LIST)) {
                listTasks(inputs, itemCount);
            } else if (input.contains(MARKUNDONE)) {
                markUnDone(inputs, itemCount, input);
            } else if (input.contains(MARKDONE)) {
                markDone(inputs, itemCount, input);
            } else if (input.contains(TODO)) {
                addTodo(inputs, itemCount, input);
            }
            else if (input.contains(DEADLINE)) {
                addDeadline(inputs, itemCount, input);
            }
            else if (input.contains(EVENT)) {
                addEvent(inputs, itemCount, input);
            }

            in = new Scanner(System.in);
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon! \n");

    }
    private static void addEvent(Task[] inputs, int itemCount, String input) {
        String task = input.substring(6, input.indexOf("/at "));
        String time = input.substring(input.indexOf("/at ") + 4);
        ;
        inputs[itemCount] = new Event(task, time);
        itemCount++;
        printDashLine();
        System.out.println("Got it. I have added this task:");
    }

    private static void addDeadline(Task[] inputs, int itemCount, String input) {
        String task = input.substring(9, input.indexOf("/by "));
        String deadline = input.substring(input.indexOf("/by ") + 4);
        inputs[itemCount] = new Deadline(task, deadline);
        itemCount++;
        printDashLine();
        System.out.println("Got it. I have added this task:");
    }

    private static void addTodo(Task[] inputs, int itemCount, String input) {
        String task = input.substring(5);
        inputs[itemCount] = new Todo(task);
        itemCount++;
        printDashLine();
        System.out.println("Got it. I have added this task:");
    }
}



