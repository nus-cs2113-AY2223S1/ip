import java.util.Scanner;

//test new branch

public class Duke {
    static final String GREETING = "Hello! I'm Timmy\n What can I do for you?\n \n";
    static final String GOODBYE = "Bye. Hope to see you again soon\n";

    static TaskManager myManager = new TaskManager();

    public static void handleInput(String input) {
        if (input.equals("list")) {
            myManager.print();
        } else if (input.startsWith("mark")){
            myManager.markAsDone(input);
        } else if (input.startsWith("unmark")) {
            myManager.removeMark(input);
        } else if (input.startsWith("todo")){
            myManager.addTodo(input);
        } else if (input.startsWith("deadline")) {
            myManager.addDeadline(input);
        } else if (input.startsWith("event")) {
            myManager.addEvent(input);
        }
    }

    public static void main(String[] args) {
        System.out.println(GREETING);
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            handleInput(userInput);
            System.out.println();
            userInput = in.nextLine();
        }

        System.out.println(GOODBYE);
    }
}

