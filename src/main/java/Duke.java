public class Duke {
    public static void printLines(int n) {
        // to print those lines
        for (int i = 0; i < n; i ++) {
            System.out.print("_");
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        printLines(50);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLines(50);
        System.out.println("Bye. Hope to see you again soon!");
        printLines(50);
    }
}
