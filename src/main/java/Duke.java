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

    public static void echo(String input) {
        line();
        System.out.println("added: " + input);
        line();
    }

    static Task[] Tasks = new Task[100];

    public static void listAllItems() {
        line();
        for(int taskNumber = 0; taskNumber < Task.getTasksCount(); taskNumber++) {
            System.out.println((taskNumber + 1) + ". " + Tasks[taskNumber].getName());
        }
        line();
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
            if(input.equals("list")) {
                listAllItems();
            }
            else {
                Tasks[Task.getTasksCount()] = new Task(input);
                echo(input);
            }
            input = in.nextLine();
        }
        bye();
    }
}
