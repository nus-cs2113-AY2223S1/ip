
public class Bloom {
    public static void drawLine() {    //print underscore symbol 50 times
        System.out.println("__________________________________________________");
    }
    public static void welcomeUser() {
        drawLine();
        System.out.println("Hello! I'm Bloom, your personal task manager.");
        System.out.println();
        System.out.println("Here are the available commands: ");
        System.out.println("todo <task name> -- add task (todo)");
        System.out.println("deadline <taskname> /by <date and/or time> -- add task (deadline)");
        System.out.println("event <taskname> /at <date and/or time period -- add task (event)");
        System.out.println("list -- to list current tasks");
        System.out.println("mark <task number> -- to mark the respective task as completed");
        System.out.println("unmark <task number> -- to unmark the respective task as not completed");
        System.out.println("bye -- to exit the program");
        System.out.println();
        System.out.println("What can I do for you?");
        drawLine();
    }
    public static void main(String[] args) {
        String logo =  " _____ __    _____ _____ _____\n"
                     + "| __  |  |  |     |     |     |\n"
                     + "| __ -|  |__|  |  |  |  | | | |\n"
                     + "|_____|_____|_____|_____|_|_|_|\n";
        System.out.println("Hello from\n" + logo);
        CommandHandler handler = new CommandHandler();
        welcomeUser();
        handler.executeUserCommands();
    }
}
