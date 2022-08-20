import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        echo();
    }

    public static void printLine(){
        System.out.print("\t");
        for(int i = 0; i < 60; i++){
            System.out.print("\u2500");
        }
        System.out.println();
    }

    public static void greet(){
        printLine();
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public static void echo(){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        input = scanner.nextLine();
        while(!input.equals("bye")){
            printLine();
            System.out.println("\t" + input);
            printLine();
            input = scanner.nextLine();
        }
        exit();
    }

    public static void exit(){
        printLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLine();
    }
}
