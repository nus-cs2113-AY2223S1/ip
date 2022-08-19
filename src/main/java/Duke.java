package main.java;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        introduction();
    }

    private static void introduction() {
        String hLine = "\n-----------------------------------";
        String introduction = "\nHello! I'm Duke\nWhat can I do for you?";
        String goodbye = "\nBye. Hope to see you again soon!";
        String result = hLine + introduction + hLine + goodbye + hLine;
        System.out.println(result);
    }
}
