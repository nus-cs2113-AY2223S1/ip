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


        //level 4
        // list with 100 rows, 3 columns, 0 - TaskType, 1 - isDone, 2 - TaskName
        String[][] TaskList = new String[100][3];
        int ListCounter = 0;
        for(int row=0; row<100; row++){
            for(int col=0; col<3; col++){
                TaskList[row][col] = " ";
            }
        }
        while(true) {
            Scanner ScannerObject4 = new Scanner(System.in);
            String input = ScannerObject4.nextLine();
            if(Objects.equals(input, "bye")){
                System.out.println("Bye! See you :)");
                break;
            } else if (Objects.equals(input, "list")) {
                //show list
                System.out.println("Here are the tasks in your list:");
                for(int task=0; task<ListCounter; task++){
                    System.out.println((task+1) + ".[" + TaskList[task][0]+"][" + TaskList[task][1]+"] " + TaskList[task][2]);
                }
                continue;
            }
            String command = input.substring(0, input.indexOf(' '));
            String content = input.substring(input.indexOf(' ') + 1);
            if(Objects.equals(command, "todo")){
                //add task to list
                TaskList[ListCounter][0] = "T";
                TaskList[ListCounter][2] = content;
                System.out.println("Got it. I've added this task:");
                System.out.println("[" + TaskList[ListCounter][0]+"][" + TaskList[ListCounter][1]+"] " + TaskList[ListCounter][2]);
                System.out.println("Now you have "+ (ListCounter+1) +" tasks in your list.");
            } else if (Objects.equals(command, "deadline")) {
                // add deadline to list, split at /
                TaskList[ListCounter][0] = "D";
                String task = content.substring(0, content.indexOf('/'));
                String timing = content.substring(content.indexOf('/') + 1);
                TaskList[ListCounter][2] = task + "(" + timing + ")";
                System.out.println("Got it. I've added this task:");
                System.out.println("[" + TaskList[ListCounter][0]+"][" + TaskList[ListCounter][1]+"] " + TaskList[ListCounter][2]);
                System.out.println("Now you have "+ (ListCounter+1) +" tasks in your list.");
            } else if (Objects.equals(command, "event")) {
                // add to event to list
                TaskList[ListCounter][0] = "E";
                String task = content.substring(0, content.indexOf('/'));
                String timing = content.substring(content.indexOf('/') + 1);
                TaskList[ListCounter][2] = task + "(" + timing + ")";
                System.out.println("Got it. I've added this task:");
                System.out.println("[" + TaskList[ListCounter][0]+"][" + TaskList[ListCounter][1]+"] " + TaskList[ListCounter][2]);
                System.out.println("Now you have "+ (ListCounter+1) +" tasks in your list.");
            } else if (Objects.equals(command, "mark")) {
                //mark task
                int TaskNumber = Integer.parseInt(content);
                TaskList[TaskNumber-1][1] = "X";
                System.out.println("Alright. Marked task " + TaskNumber);
                continue;
            } else if (Objects.equals(command, "unmark")) {
                //unmark task
                int TaskNumber = Integer.parseInt(content);
                TaskList[TaskNumber-1][1] = " ";
                System.out.println("Alright. Unmarked task " + TaskNumber);
                continue;
            } else {
                System.out.println("Invalid command. Please try again!");
                continue;
            }
            ListCounter++;
        }

    }
}
