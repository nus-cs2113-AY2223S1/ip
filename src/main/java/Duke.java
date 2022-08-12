public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetings();
        exit();
    }

    private static void exit(){
        System.out.println(" Bye! Hope to see you again soon :)");
    }

    private static void greetings(){
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?");
    }
}
