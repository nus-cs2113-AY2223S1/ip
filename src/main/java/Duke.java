public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        PrintHorizontalLine();
        PrintGreeting();
        PrintHorizontalLine();
        PrintExit();
        PrintHorizontalLine();
    }

    public static void PrintGreeting(){
        System.out.println("Hello I'm Duke");
        System.out.println("Hello what can I do for you?");
    }

    public static void PrintExit(){
        System.out.println("Bye. Hope to see you soon");
    }
    public static void PrintHorizontalLine(){
        System.out.println("-----------------------------------------------------------");
    }
}
