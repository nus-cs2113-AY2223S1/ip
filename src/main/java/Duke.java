import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------");
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            if(input.equals("bye")){
                System.out.println("------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------------");
            } else {
                System.out.println("------------------------------------");
                System.out.println(input);
                System.out.println("------------------------------------");
            }
        } while (!input.equals("bye"));


    }
}
