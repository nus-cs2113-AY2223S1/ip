import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
        Scanner input = new Scanner(System.in);
        String val = input.nextLine();
        String[] list = new String[100];
        String[] status = new String[100];
        int length = 0;
        
        while(!val.equals("bye")){
            System.out.println("____________________________________________________________");
            if(val.equals("list")){
                for(int i = 0; i < length; i++) {
                    System.out.println("  " + (i + 1) + ".[" + status[i] + "] " + list[i]);
                }
            } else if (!val.contains("mark")) {
                list[length] = val;
                status[length] = " ";
                length++;
                System.out.println("added: " + val);
            } else if (val.indexOf("mark") == 0) {
                int id = val.indexOf(" ");
                String ind = val.substring(id + 1);
                int index = Integer.parseInt(ind) - 1;
                status[index] = "X";
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    [" + status[index] + "] " + list[index]);
            } else{
                int id = val.indexOf(" ");
                String ind = val.substring(id + 1);
                int index = Integer.parseInt(ind) - 1;
                status[index] = " ";
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("    [" + status[index] + "] " + list[index]);
            }
            System.out.println("____________________________________________________________");
            val = input.nextLine();

        }

        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
