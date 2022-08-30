import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // level 1
        Scanner sc = new Scanner(System.in);
        String echo = sc.next();
        while (!Objects.equals(echo, "bye")){
            System.out.println(echo);
            echo = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");

        // level 2 & 3
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int count = 0;
        int marker;
        String[] storage = new String[100];
        String[] marked = new String[100];
        for(int m=0; m<100; m++){ //set all unmarked
            marked[m] = " ";
        }
        while (true){
            Scanner sc2 = new Scanner(System.in);
            String add = sc2.nextLine();
            if(Objects.equals(add, "list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i<count; i++){
                    System.out.println((i+1)+".["+ marked[i] +"] " +storage[i]);
                }
                continue;
            } else if (Objects.equals(add, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (add.contains("mark") || add.contains("unmark")) {
                 if (add.contains("unmark")) {
                     String[] parts = add.split(" ");
                     marker = Integer.parseInt(parts[1]);
                     marked[marker - 1] = " ";
                     System.out.println("OK, I've marked this task as not done yet:");
                     System.out.println("[ ] " + storage[marker - 1]);
                 } else if (add.contains("mark")) {
                    String[] parts = add.split(" ");
                    marker = Integer.parseInt(parts[1]);
                    marked[marker-1] = "X";
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] "+ storage[marker-1]);
                }
                continue;
            } else {
                storage[count] = add;
                System.out.println("added: " + add);
            }
            count++;
        }

    }
}
