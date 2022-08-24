import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        List<String> Storage = new ArrayList<>();
        String line = " ";
        Scanner in = new Scanner(System.in);
        int count = 0;


        while (count < 100 && !line.equals("bye")) {
            line = in.nextLine();

            if (line.equals("list")) {
                for (int i = 0; i < Storage.size() && Storage.get(i)!=null; i++) {
                    System.out.println(i+1 + ". " + Storage.get(i));
                }
                System.out.println();
            }
            else {
                Storage.add(line);
                System.out.println("added: " + line + "\n");
                count++;
            }
        }


        System.out.println("Bye good friend! Hope to see you again soon!\n" + logo);
    }
}
