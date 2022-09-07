import java.util.Scanner;
public class Duke {
    public static int numberOfTasks = 0;
    public static Task[] myList = new Task[1000];

    public static void main(String[] args) throws MethodNotRecognisedException {

        String GREETING = "____________________________________________________________\n" +
                " __      __                       \n" +
                "/  \\    /  \\ ____   ____    ____  \n" +
                "\\   \\/\\/   // __ \\ /    \\  / ___\\ \n" +
                " \\        /\\  ___/|   |  \\/ /_/  >\n" +
                "  \\__/\\  /  \\___  >___|  /\\___  / \n" +
                "       \\/       \\/     \\//_____/  " +
                "\n Hello! I'm Weng!\n" +
                " What can I do for ya?\n" +
                "____________________________________________________________\n";
        System.out.println("\n" + GREETING);

        try {
            while (true) {
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                String words[] = input.split(" ");
                if (input.startsWith("bye")) {
                    sayBye();
                    break;
                } else if (input.startsWith("list")) {
                    printList();
                } else if (input.startsWith("mark")) {
                    markTask(words);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(words);
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else throw new MethodNotRecognisedException();
            }
        }catch(MethodNotRecognisedException e){
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        catch(NoTaskSpecifiedException e){
            System.out.println("OOPS!!! I'm sorry, no task specified :-(");
        }
    }

    private static void sayBye() {
        String EXIT_MESSAGE = "____________________________________________________________\n" +
                " Bye. Hope to cya again soon!\n" +
                "____________________________________________________________";
        System.out.println(EXIT_MESSAGE);
    }

    private static void printList() {
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.print(i + 1);
            System.out.print(".");
            System.out.println(myList[i].toString());
        }
    }

    private static void markTask(String[] words) {
        int index = Integer.parseInt(words[1]);
        myList[index - 1].markAsDone();
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                myList[index - 1].getStatusIcon() + " " + myList[index - 1].description +
                "\n____________________________________________________________");
    }

    private static void unmarkTask(String[] words) {
        int index = Integer.parseInt(words[1]);
        myList[index - 1].markAsNotDone();
        System.out.println("____________________________________________________________\n" +
                "     Ok, I've marked this task as not done yet:\n" +
                myList[index - 1].getStatusIcon() + " " + myList[index - 1].description +
                "    ____________________________________________________________");
    }

    private static void addDeadline(String input) {
        String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4);
        myList[numberOfTasks] = new Deadline(description, by);
        numberOfTasks++;
        printCurrentTaskMessage();
    }

    private static void addEvent(String input) {
        String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/at") - 1);
        String at = input.substring(input.indexOf("/at") + 4);
        myList[numberOfTasks] = new Event(description, at);
        numberOfTasks++;
        printCurrentTaskMessage();
    }

    private static void addTodo(String input) throws NoTaskSpecifiedException{
        String words[] = input.split(" ");
        if(words.length <  2){
            throw new NoTaskSpecifiedException();
        }
        String description = input.substring(input.indexOf(" ") + 1);
        myList[numberOfTasks] = new Todo(description);
        numberOfTasks++;
        printCurrentTaskMessage();
    }

    private static void printCurrentTaskMessage(){
        System.out.println("Got it. I've added this task:");
        System.out.println(myList[numberOfTasks-1].toString());
        System.out.println("Now you have "+numberOfTasks+" tasks in the list");
    }
}

