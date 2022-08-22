import java.util.Scanner;

public class Duke {

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static int numOfWords(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[101];
        int taskIndex = 1;
        String inData;
        Scanner scan = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            //Continuously receive user input
            inData = scan.nextLine();
            if (inData.equals("list")) {
                //Print entire list if input is equal to "list"
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < taskIndex; i += 1) {
                    System.out.println(i + ". " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
                System.out.println("____________________________________________________________");

            } else if (numOfWords(inData) == 2) {
                String[] arr = inData.split(" ");
                if (arr[0].equals("unmark")) {
                    //check if arr[1] is a number
                    if (isNumeric(arr[1])) {
                        int unmarkedIndex = Integer.parseInt(arr[1]);
                        if ((unmarkedIndex > 0) && (unmarkedIndex < taskIndex)) {
                            tasks[unmarkedIndex].setDone(false);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[" + tasks[unmarkedIndex].getStatusIcon() + "] " + tasks[unmarkedIndex].getDescription());
                        } else {
                            System.out.println("Invalid unmark command");
                        }
                    } else {
                        if (taskIndex < 101) {
                            //If the number of elements within task[] is within 100, continue to add items to task[].
                            tasks[taskIndex] = new Task(inData);
                            taskIndex += 1;
                            System.out.println("____________________________________________________________");
                            System.out.println("added: " + tasks[taskIndex - 1].description);
                            System.out.println("____________________________________________________________");
                        }
                    }
                } else if (arr[0].equals("mark")) {
                    //check if arr[1] is a number
                    if (isNumeric(arr[1])) {
                        int markedIndex = Integer.parseInt(arr[1]);
                        if ((markedIndex > 0) && (markedIndex < taskIndex)) {
                            tasks[markedIndex].setDone(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[" + tasks[markedIndex].getStatusIcon() + "] " + tasks[markedIndex].getDescription());
                        } else {
                            System.out.println("Invalid mark command");
                        }
                    } else {
                        if (taskIndex < 101) {
                            tasks[taskIndex] = new Task(inData);
                            taskIndex += 1;
                            System.out.println("____________________________________________________________");
                            System.out.println("added: " + tasks[taskIndex - 1].description);
                            System.out.println("____________________________________________________________");
                        }
                    }
                } else {
                    if (taskIndex < 101) {
                        tasks[taskIndex] = new Task(inData);
                        taskIndex += 1;
                        System.out.println("____________________________________________________________");
                        System.out.println("added: " + tasks[taskIndex - 1].description);
                        System.out.println("____________________________________________________________");
                    }
                }
            } else {
                if (inData.equals("bye")) {
                    //If input equals to "bye", exit the while-loop
                    break;
                } else {
                    if (taskIndex < 101) {
                        //If the number of elements within task[] is within 100, continue to add items to task[].
                        tasks[taskIndex] = new Task(inData);
                        taskIndex += 1;
                        System.out.println("____________________________________________________________");
                        System.out.println("added: " + tasks[taskIndex - 1].description);
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

