package duke;

public class Printables {

    public static String initialGreeting = "Greetings, I am Azmuth\n" +
            "What can I do for you?\n" +
            "Enter command 'help' for a list of useful commands!\n" +
            "____________________";

    public static String goodbyeMessage = "Bye and see you again soon!";

    public static String helpManual = "____________________\n" +
            "Here if a list of commands that I recognise!\n" +
            "'todo <taskName>': Add a new todo task\n" +
            "'deadline <taskName>/<date>': Add a new todo task\n" +
            "'event <taskName>/<date>': Add a new todo task\n" +
            "'list': Lists out tasks in index order\n" +
            "'mark <taskNumber>': Mark a certain task as done\n" +
            "'unmark <taskNumber>': Unmark a certain task that was done\n" +
            "'bye': Quit the system\n" + "____________________";

    public static String invalidBasicCommandMessage = "Commands 'list', 'help' and 'bye' can only be used alone" +
            "as a single word";
}
