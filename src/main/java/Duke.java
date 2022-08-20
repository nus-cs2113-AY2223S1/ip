import java.util.Scanner;

public class Duke {

    static String[] tasks = new String[100];
    static int currTask = -1;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        processInput();
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

    public static void processInput(){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        input = scanner.nextLine();
        while(!input.equals("bye")){
            printLine();
            if(input.equals("list")){
                if(currTask == -1)
                    System.out.println("\tNothing in list right now!");
                for(int i = 0; i <= currTask; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            }
            else{
                tasks[++currTask] = input;
                System.out.println("\tadded: " + input);
            }
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
