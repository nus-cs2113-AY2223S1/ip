public class Util {
    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void printSplitLine(){
        System.out.println("    ____________________________________________________________");
    }
    
    public static void printTaskResponse(String response, int listLength){
        printSplitLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       "+response);
        System.out.println("     Now you have "+listLength+" tasks in the list.");
        printSplitLine();
    }
}
