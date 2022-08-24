import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String linebreak = "____________________________________________________________";

    static void startSession() {
        System.out.println("Hello from\n" + logo);
        System.out.println(linebreak);
        System.out.println("Hello! I'm Duke Nukem");
        System.out.println("What can I do for you today? Let's rock!");
        System.out.println(linebreak);
    }

    static void endSession() {
        System.out.println(linebreak);
        System.out.println("Bye. Hope to see you again soon! Groovy!");
        System.out.println(linebreak);
    }

    static void storeList(ArrayList<String> List,String text) {
        System.out.println(linebreak);
        List.add(text);
        System.out.println("added: "+text);
        System.out.println(linebreak);
    }

    static void printList(ArrayList<String> List) {
        System.out.println(linebreak);
        int count =1;
        for (String text:List) {
            System.out.println(String.valueOf(count)+". "+text);
            count++;
        }
        System.out.println(linebreak);
    }

    public static void main(String[] args) {
        ArrayList<String> List = new ArrayList<String>();

        startSession();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(List);
            }
            else {
                storeList(List,line);
            }
            line = in.nextLine();
        }

        endSession();
    }
}
