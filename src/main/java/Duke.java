import java.util.Scanner;

//test new branch

public class Duke {
    static final String GREETING = "Hello! I'm Timmy\n What can I do for you?\n \n";
    static final String GOODBYE = "Bye. Hope to see you again soon\n";

    static TaskManager myManager = new TaskManager();

    public static void handleInput(String input) {
        String command;
        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
        } else {
            command = input;
        }
        try {
            myManager.handleInput(input, command);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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

