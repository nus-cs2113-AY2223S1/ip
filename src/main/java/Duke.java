public class Duke {
    public static void drawLine() {    //print underscore symbol 50 times
        System.out.println("__________________________________________________");
    }

    public static void welcomeUser() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        welcomeUser();
        drawLine();
        byeUser();
    }
}
