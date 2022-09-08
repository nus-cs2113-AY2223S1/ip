/**
 * TO DO:
 * create tasks package
 * create command package
 * improve parser
 * duke structure will be init ui, parser, tasklist
 * to run duke - print greeting, loop while !exitCommand ->
 * ui polling for user input, parse user input, execute command, update tasklist
 * */

import java.util.Scanner;

public class Duke {

    /** Greeting art */
    public static final String greetingArt =
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣠⣤⣤⣄⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⠖⠊⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠲⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⡤⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⡜⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢢⠀⠀⠀⠀⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⣸⠁⠀⠀⠀⠀⠀⠀⠀⠱⡀⠀⠀⠀⠀⠀⠀⠀⡀⠈⠀⡀⠀⠀⠀⠈⡇⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⡏⠀⠀⠀⠀⠀⠀⠀⠀⡰⠁⠀⠀⠀⠀⠀⠀⠀⠘⡆⡜⠁⠀⠀⠀⠀⢧⡀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠸⡀⠀⠀⠀⠀⠀⣀⣤⡂⠀⠇⠱⠀⡀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢇⠀⠀⠀⠀⠀⠀⠀⠀⠈⢄⡀⢠⣟⢭⣥⣤⠽⡆⠀⡶⣊⣉⣲⣤⢀⡞⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠘⣆⠀⠀⠀⠀⠀⠀⡀⠀⠐⠂⠘⠄⣈⣙⡡⡴⠀⠀⠙⣄⠙⣛⠜⠘⣆⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠈⢦⡀⠀⠀⠀⢸⠁⠀⠀⠀⠀⠀⠀⠄⠊⠀⠀⠀⠀⡸⠛⠀⠀⠀⢸⠆⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⠦⢄⣘⣄⠀⠀⠀⠀⠀⠀⠀⡠⠀⠀⠀⠀⣇⡀⠀⠀⣠⠎⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠁⠈⡟⠒⠲⣄⠀⠀⡰⠇⠖⢄⠀⠀⡹⡇⢀⠎⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⡇⠀⠀⠹⠀⡞⠀⠀⢀⠤⣍⠭⡀⢱⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⢀⣀⣀⣠⠞⠀⠀⢠⡇⠀⠀⠀⠀⠁⠀⢴⠥⠤⠦⠦⡼⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⣀⣤⣴⣶⣿⣿⡟⠁⠀⠋⠀⠀⠀⢸⠁⠀⠀⠀⠀⠀⠀⠀⠑⣠⢤⠐⠁⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠬⠥⣄⠀⠀⠈⠲⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠙⠦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠈⢳⠀⠀⢀⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠒⠦⠤⢤⣄⣀⣠⠤⢿⣶⣶⣿⣿⣿⣶⣤⡀⠀⠀⠀⠀⠀\n" +
            "⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⠁⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⠀\n" +
            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣤⣤⣀⣀⣀⣀⣀⣀⣀⣤⣤⣤⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀";

    public static final String goodbyeArt =
            "        .--'''''''''--.\n" +
            "     .'      .---.      '.\n" +
            "    /    .-----------.    \\\n" +
            "   /        .-----.        \\\n" +
            "   |       .-.   .-.       |\n" +
            "   |      /   \\ /   \\      |\n" +
            "    \\    | .-. | .-. |    /\n" +
            "     '-._| | | | | | |_.-'\n" +
            "         | '-' | '-' |\n" +
            "          \\___/ \\___/\n" +
            "       _.-'  /   \\  `-._\n" +
            "     .' _.--|     |--._ '.\n" +
            "     ' _...-|     |-..._ '\n" +
            "            |     |\n" +
            "            '.___.'\n" +
            "              | |\n" +
            "             _| |_\n" +
            "            /\\( )/\\\n" +
            "           /  ` '  \\";

    /** Greeting and goodbye message constants */
    public static final String greetingMessage = "______________________________________\n" +
            "Hello! I'm Handsome!\n" +
            greetingArt +
            "How can I help you?\n" +
            "______________________________________\n";
    public static final String goodbyeMessage = "Bye. Hope to see you again soon!\n" + goodbyeArt;

    /** Commands */
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    /** Parser delimiters */

    public static final String GET_DESCRIPTION_DELIMITER = "/.+";
    public static final String GET_AT_DELIMITER = ".+/at ";
    public static final String GET_BY_DELIMITER = ".+/by ";
    public static final String SPACE_DELIMITER = "\\s";
    public static final String START_LINE_DELIMITER = "^";
    public static final String DIGITS_DELIMITER = "\\d+";
    public static final String DEADLINE_DELIMITER = ".+/by.+";
    public static final String EVENT_DELIMITER = ".+/at.+";


    /** Task variables */

    public static TaskList[] taskList= new TaskList[100];
    public static int taskCount = 0;

    /** Helper function to format message to be printed */
    public static final String DIVIDER = "______________________________________";
    private static void printFormattedMessage(String message) {
        System.out.println(DIVIDER + message + DIVIDER);
    }


    /** Prints greeting message */

    public static void printGreetingMessage(){
        System.out.println(greetingMessage);
    }

    /** Prints exit message */
    public static void printGoodbyeMessage(){
        System.out.println(goodbyeMessage);
    }

    /** Print that you have added a task */
    public static void printAddTask(){
        System.out.println(DIVIDER);
        System.out.print("Got it. I've added this task:\n  ");
    }
    /** Print number of tasks left */
    public static void printNumberOfTask(){
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {

        // Flow of chatbot greet -> read input and execute command while input != 'bye' -> goodbye

        // Print greeting message
        printGreetingMessage();

        // Initialise instance of TaskList
        TaskList list = new TaskList();

        System.out.println("Please enter your taskList command: (send 'bye' to exit) ");
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            String[] parsedInput = input.split("\\s+");

            if(parsedInput[0].equals("bye")) {
                break;
            } else if (parsedInput[0].equals("list")) {
                printFormattedMessage(list.getTaskList());
            } else if (parsedInput[0].equals("mark")){
                // mark item as complete
                list.setMarkAsCompleted(Integer.parseInt(parsedInput[1]), true);
                String markOutput = list.getTaskDescription(Integer.parseInt(parsedInput[1]));
                printFormattedMessage("Nice! I've marked this task as done:\n" + markOutput);
            } else if (parsedInput[0].equals("unmark")) {
                // mark item as incomplete
                list.setMarkAsCompleted(Integer.parseInt(parsedInput[1]), false);
                String unmarkOutput = list.getTaskDescription(Integer.parseInt(parsedInput[1]));
                printFormattedMessage("OK, I've marked this task as not done yet:\n" + unmarkOutput);
            } else {
                list.addNewTask(input);
                printFormattedMessage("added: " + input + "\n");
            }
        }

        printGoodbyeMessage();
    }

    // print formatted message

}

