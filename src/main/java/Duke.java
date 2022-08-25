import java.util.Scanner;

public class Duke {

    /**
     * Prints out a list of the tasks saved from the user inputs
     *
     * @param assignments variable that is created using the class Task
     * @param indexTask index the Task array given by the variable assignment
     */
    public static void printList(Task[] assignments, int indexTask){
        int numberOrder = 1;
        for(int i = 0; i < indexTask; i++) {
            System.out.println("\t " + numberOrder + ".["
                    + assignments[i].getStatusIcon() + "] "
                    + assignments[i].description);
            numberOrder++;
        }
    }

    public static void main(String[] args) {
        String logo = "___________.__                ___.     \n"
                + "\\_   _____/|  | _____    _____|  |__  \n"
                + " |    __)  |  | \\__  \\  /  ___/  |  \\ \n"
                + " |     \\   |  |__/ __ \\_\\___ \\|   Y  \\\n"
                + " \\___  /   |____(____  /____  >___|  /\n"
                + "     \\/              \\/     \\/     \\/ \n";
        System.out.println("Hello from\n" + logo);
        String lineDivider = "____________________________________________________________\n";
        System.out.println("\t" + lineDivider
                + "\t Hello! I'm Flash\n"
                + "\t What can I do for you?\n"
                + "\t" + lineDivider);

        String userInput;
        Scanner in = new Scanner(System.in);
        Task[] assignments = new Task[100];
        int indexTask = 0;

        do {
            //Enable user to enter text
            userInput = in.nextLine();
            System.out.print("\t" + lineDivider);
            String[] splitUserInput = userInput.split(" ");

            if(!splitUserInput[0].equals("bye")) {
                if(!splitUserInput[0].equals("list")
                        && !splitUserInput[0].equals("mark")
                        && !splitUserInput[0].equals("unmark")) {
                    assignments[indexTask] = new Task(userInput);
                    System.out.println("\t added: " + assignments[indexTask].description);
                    indexTask++;
                } else if (splitUserInput[0].equals("list")) {
                    printList(assignments, indexTask);
                } else if (splitUserInput[0].equals("mark")) {
                    int markIndex = Integer.parseInt(splitUserInput[1]) - 1;
                    //To handle a case where user tries to mark a task that has not been specified
                    try {
                        assignments[markIndex].markAsDone();
                    } catch(NullPointerException e) {
                        System.out.println("\t You are trying to mark a task that has not been specified!");
                        System.out.println("\t" + lineDivider);
                        continue;
                    }

                    System.out.println("\t Awesome! I've marked this task as done:");
                    System.out.println("\t\t [" + assignments[markIndex].getStatusIcon()
                            + "] " + assignments[markIndex].description);
                } else {
                    //If the splitUserInput[0] is equals to "unmark"
                    int unmarkIndex = Integer.parseInt(splitUserInput[1]) - 1;
                    //To handle a case where user tries to unmark a task that has not been specified
                    try {
                        assignments[unmarkIndex].unmarkAsDone();
                    } catch(NullPointerException e) {
                        System.out.println("\t You are trying to unmark a task that has not been specified!");
                        System.out.println("\t" + lineDivider);
                        continue;
                    }
                    System.out.println("\t Awesome! I've marked this task as not done yet:");
                    System.out.println("\t\t [" + assignments[unmarkIndex].getStatusIcon()
                            + "] " + assignments[unmarkIndex].description);
                }
            } else {
                System.out.println("\t Bye. Hope to see you again soon!");
            }

            System.out.println("\t" + lineDivider);
        }
        while(!userInput.equals("bye"));
    }
}
