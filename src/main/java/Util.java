public class Util {
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
