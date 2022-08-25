import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        String a = in.nextLine();
        while(!a.equals("bye")){
            System.out.println(a);
            a = in.nextLine();
        }
        System.out.println();

        System.out.println("Bye");
    }
}
