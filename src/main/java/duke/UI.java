package duke;

public class UI {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINEBREAK = "____________________________________________________________";

    static void printLine(){System.out.println(LINEBREAK); }

    static void printLogo(){System.out.println("Hello from\n" + LOGO);}
}
