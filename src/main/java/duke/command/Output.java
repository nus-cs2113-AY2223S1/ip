package duke.command;

public class Output {

    public static void printLines() {
        String lines = "__________________________________________________";
        System.out.println(lines);
    }

    public static void printGreetings(String greeting) {
        if (greeting.equals("hello")) {
            // hello greeting
            printLines();
            System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
            printLines();
        }   else {
            // bye greeting
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
