import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[101];
        int taskIndex = 1;
        String inData = "0";
        Scanner scan = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            //Continuously receive user input
            inData = scan.nextLine();
            if (inData.equals("list")) {
                //Print entire list if input is equal to "list"
                for (int i = 1; i < taskIndex; i += 1) {
                    System.out.println(i + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                if (inData.equals("bye")) {
                    //If input equals to "bye", exit the while-loop
                    break;
                } else {
                    if (taskIndex < 101) {
                        //If the number of elements within task[] is within 100, continue to add items to task[].
                        tasks[taskIndex] = inData;
                        taskIndex += 1;
                        System.out.println("____________________________________________________________");
                        System.out.println("added: " + inData);
                        System.out.println("____________________________________________________________");
                    }
                }
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
