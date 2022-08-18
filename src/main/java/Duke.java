public class Duke {

    public static void greetUser(){
        String greeting = "____________________________________________________________" + System.lineSeparator()
        + "Hello! My name is Axel. :-)" + System.lineSeparator()
        + "How may I help you today?" + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator()
        + "Goodbye. Hope to see you again soon!" + System.lineSeparator()
        + "____________________________________________________________";
        System.out.println(greeting);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println(logo);
        greetUser();
    }
}
