public class Duke {

    private void reply (String message) {
        System.out.print(message);
        System.out.println("____________________________________________________________");
    }
    private void greet() {
        String greetMessage = "Hello! I'm Banana\n"
                + "What can I do for you?\n";
        reply(greetMessage);
    }

    private void bye() {
        String byeMessage = "Bye. Hope to see you again soon!\n";
        reply(byeMessage);
    }

    private void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        reply(logo);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.bye();
    }
}
