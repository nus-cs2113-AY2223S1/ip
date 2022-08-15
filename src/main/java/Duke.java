public class Duke {
    public static void greet(){
        String entryBanner = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(entryBanner);
    }

    public static void quit(){
        String exitBanner = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(exitBanner);
    }

    public static void main(String[] args) {
        greet();
        quit();
    }
}
