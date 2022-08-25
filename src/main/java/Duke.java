public class Duke {
    public void greeting(){
        String greet = "Hello! I'm Duke";
        String question = "What can I do for you?";
        String bye = "Bye. Hope to see you again soon!";
        String line = "---------------------------------------------";
        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line + "\n" + bye + "\n" + line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        new Duke().greeting();
    }
}
