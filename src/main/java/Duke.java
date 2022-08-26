import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
        Scanner input = new Scanner(System.in);
        String val = input.next();
        String[] list = new String[100];
        int length = 0;
        while(!val.equals("bye")){
            if(val.equals("list")){
                System.out.println("____________________________________________________________");
                for(int i = 0; i < length; i++){
                    System.out.println("  " + i + 1 + ". " + list[i]);
                }
                System.out.println("____________________________________________________________");
                val = input.next();
            }else {
                list[length] = val;
                length++;
                System.out.println("____________________________________________________________\n" +
                        "added: " + val +
                        "\n____________________________________________________________");
                val = input.next();

            }
        }

        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
