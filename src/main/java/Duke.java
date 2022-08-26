import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
        Scanner input = new Scanner(System.in);
        String val = input.next();
        while(!val.equals("bye")){
            System.out.println("____________________________________________________________\n" +
                    val +
                    "\n____________________________________________________________");
            val = input.next();
        }

        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
