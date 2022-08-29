public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showSeparator();
        System.out.println(logo);
        showSeparator();
        showWelcomeMsg();
        showSeparator();
        showGoodbyeMsg();
        showSeparator();
    }

    private static void showSeparator() {
        System.out.println("===================================================");
    }

    private static void showWelcomeMsg() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void showGoodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }
 }
