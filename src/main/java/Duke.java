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
        Scanner ScannerObject = new Scanner(System.in);
        String echo = ScannerObject.next();
        while (!Objects.equals(echo, "bye")){
            System.out.println(echo);
            echo = ScannerObject.next();
        }
        System.out.println("Bye. Hope to see you again soon!");

        // level 2 & 3
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int counter = 0;
        int split;
        String[] toDoList = new String[100];
        String[] markers = new String[100];
        for(int m=0; m<100; m++){ //set all unmarked
            markers[m] = " ";
        }
        while (true){
            Scanner ScannerObject2 = new Scanner(System.in);
            String add = ScannerObject2.nextLine();
            if(Objects.equals(add, "list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i<counter; i++){
                    System.out.println((i+1)+".["+ markers[i] +"] " +toDoList[i]);
                }
                continue;
            } else if (Objects.equals(add, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (add.contains("mark") || add.contains("unmark")) {
                 if (add.contains("unmark")) {
                     String[] parts = add.split(" ");
                     split = Integer.parseInt(parts[1]);
                     markers[split - 1] = " ";
                     System.out.println("OK, I've marked this task as not done yet:");
                     System.out.println("[ ] " + toDoList[split - 1]);
                 } else if (add.contains("mark")) {
                    String[] parts = add.split(" ");
                    split = Integer.parseInt(parts[1]);
                    markers[split-1] = "X";
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] "+ toDoList[split-1]);
                }
                continue;
            } else {
                toDoList[counter] = add;
                System.out.println("added: " + add);
            }
            counter++;
        }

    }
}
