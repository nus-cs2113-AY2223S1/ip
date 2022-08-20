import java.util.Scanner;

public class Duke {

    //Zhou Zhou
    public static void line() {
        System.out.println("------------------------------");
    }

    public static void greet() {
        line();
        System.out.println("Hi! I'm Slave Kai, Zhou Zhou's 256IQ bot\nHow can I help you?");
        line();
    }

    public static void bye() {
        line();
        System.out.println("Please don't go :(");
        line();
    }

    static Task[] Tasks = new Task[100];

    public static void listAllTasks() {
        for (int taskNumber = 0; taskNumber < Task.getTasksCount(); taskNumber++) {
            Tasks[taskNumber].listTask();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            line();
            String[] words = input.split(" ");
            switch (words[0]) {
            case "list":
                listAllTasks();
                break;
            case "mark":
                if((Integer.parseInt(words[1]) > 0) && (Integer.parseInt(words[1]) <= Task.getTasksCount())) {
                    Tasks[Integer.parseInt(words[1]) - 1].markAsDone();
                }
                break;
            case "unmark":
                if((Integer.parseInt(words[1]) > 0) && (Integer.parseInt(words[1]) <= Task.getTasksCount())) {
                    Tasks[Integer.parseInt(words[1]) - 1].markAsNotDone();
                }
                break;
            default:
                Tasks[Task.getTasksCount()] = new Task(input);
                System.out.println("added: " + input);
                break;
            }
            line();
            input = in.nextLine();
        }
        line();
        bye();
    }
}
