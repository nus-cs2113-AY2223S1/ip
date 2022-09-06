import java.util.Scanner;
public class Duke {
    public static final String MARKDONE = "mark ";
    public static final String MARKUNDONE = "unmark ";
    public static final String TODO = "todo ";
    public static final String DEADLINE = "deadline ";
    public static final String EVENT = "event ";
    public static final String LIST = "list";
    public static final String BYE = "bye";
    public static final String AT = "/at ";
    public static final String BY = "/by ";
    public static int itemCount = 0;

    private static void printDashLine() {
        System.out.println("__________________________________________________ \n");
    }

    private static void markDone (Task[] inputs, String input) throws DukeException{
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARKDONE) + MARKDONE.length()));
        if (taskNumber < itemCount) {
            System.out.println("Okiii... This task has been marked as done");
            inputs[taskNumber].markAsDone();
            System.out.println(inputs[taskNumber].description);
        } else {
            throw new DukeException();
        }
        printDashLine();
    }

    private static void markUnDone (Task[] inputs, String input) throws DukeException{
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARKUNDONE) + MARKUNDONE.length()));
        if (taskNumber < itemCount) {
            System.out.println("Okiii... This task has been marked as not done yet");
            inputs[taskNumber].markAsUndone();
            System.out.println(inputs[taskNumber].description);
        } else {
            throw new DukeException();
        }

        printDashLine();
    }

    private static void listTasks (Task[] inputs){
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

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (input.equals(BYE) == false) {
            printDashLine();
            if (input.equals(LIST)) {
                listTasks(inputs);
            } else if (input.contains(MARKUNDONE)) {
                try {
                    markUnDone(inputs, input);
                }
                catch (DukeException e)
                {
                    System.out.println("The task number is out of bound and therefore cannot be marked undone!");
                }
            } else if (input.contains(MARKDONE)) {
                try {
                    markDone(inputs, input);
                }
                catch (DukeException e)
                {
                    System.out.println("The task number is out of bound and therefore cannot be marked done!");
                }
            } else if (input.contains(TODO)) {
                try {
                    addTodo(inputs, input);
                }
                catch (DukeException e)
                {
                    System.out.println("The todo input is not valid! Might be missing description!");
                }
            }
            else if (input.contains(DEADLINE)) {
                try {
                    addDeadline(inputs, input);
                }
                catch (DukeException e)
                {
                    System.out.println("The deadline input is not valid! Might be missing description, '/by' or deadline!");
                }
            }
            else if (input.contains(EVENT)) {
                try{
                    addEvent(inputs, input);
                }
                catch (DukeException e){
                    System.out.println("The event input is not valid! Might be missing description, '/at' or time !");
                }
            }
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon! \n");

    }
    private static void addEvent(Task[] inputs, String input) throws DukeException{
        String task = input.substring(EVENT.length(), input.indexOf(AT));
        String time = input.substring(input.indexOf(AT) + AT.length());

        if (task.equals("") || (time.equals(""))){
            throw new DukeException();
        }
        else{
            inputs[itemCount] = new Event(task, time);
            itemCount++;
            printDashLine();
            System.out.println("Got it. I have added this task:");
            System.out.println("Now you have " + Integer.toString(itemCount) + " tasks left");
            printDashLine();
        }

    }

    private static void addDeadline(Task[] inputs, String input) throws DukeException {
        String task = input.substring(DEADLINE.length(), input.indexOf(BY));
        String deadline = input.substring(input.indexOf(BY) + BY.length());

        if (task.equals("") || deadline.equals("")){
            throw new DukeException();
        }

        else{
            inputs[itemCount] = new Deadline(task, deadline);
            itemCount++;
            printDashLine();
            System.out.println("Got it. I have added this task:");
            System.out.println("Now you have " + Integer.toString(itemCount) + " tasks left");
            printDashLine();
        }

    }

    private static void addTodo(Task[] inputs, String input) throws DukeException{
        String task = input.substring(TODO.length());
        if (task.equals("")){
            throw new DukeException();
        }
        else {
            inputs[itemCount] = new Todo(task);
            itemCount++;
            printDashLine();
            System.out.println("Got it. I have added this task:");
            System.out.println("Now you have " + Integer.toString(itemCount) + " tasks left");
            printDashLine();
        }

    }
}



