public class Message {
    public static void printingGreeting() {
        printingHorizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("Hello what can I do for you?");
        printingHorizontalLine();
    }

    public static void printingExit() {
        printingHorizontalLine();
        System.out.println("Bye. Hope to see you soon");
        printingHorizontalLine();
    }

    public static void printingHorizontalLine() {
        System.out.println("-----------------------------------------------------------");
    }

    public static void printingEcho(String line) {
        printingHorizontalLine();
        System.out.println(line);
        printingHorizontalLine();
    }

}
