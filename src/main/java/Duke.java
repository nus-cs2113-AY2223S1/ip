public class Duke {

    //Zhou Zhou
    public static void line() {
        System.out.println("------------------------------");
    }

    public static void greet() {
        line();
        System.out.println("Hi! I'm Slave Kai, Zhou Zhou's 256IQ bot\nHow can I help you?");
        line();
    }

    public static void bye() {
        System.out.println("Have a great day!");
        line();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();


        bye();
    }
}
