import java.util.Scanner;

public class Duke {
    static Scanner in = new Scanner(System.in);
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void echo(String line){ //Echo user input
        System.out.print("Duke says: ");
        System.out.println(line);
    }

    public static String ask(){
        System.out.print("You say: ");
        String a;
        return a = in.nextLine();
    }
    public static void main(String[] args) {
        String line = "";
        System.out.println(logo);

        //Increment Level 0
        System.out.println("===================================================");
        System.out.println("Hello! I'm Duke made by Than Duc Huy");
        System.out.println("What can I do for you?");
        System.out.println("===================================================");

        while(true){
            line = ask();
            if (line.toLowerCase().contains("bye")){
                break;
            }
            echo(line);

        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("===================================================");
    }


}
