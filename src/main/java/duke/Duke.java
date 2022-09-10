package duke;

import java.io.FileNotFoundException;

public class Duke {
    public static void drawLine() {    //print underscore symbol 50 times
        System.out.println("__________________________________________________");
    }
    public static void welcomeUser() {
        drawLine();
        System.out.println("Hello! I'm Bloom, your personal task manager.");
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
        DukeFile df = new DukeFile();
//        try {
//            df.printFileContents(df.getFile());
//            drawLine();
//        } catch (FileNotFoundException e) {
//            System.out.println("File does not exist");
//        }
        handler.executeUserCommands(df);
    }
}
