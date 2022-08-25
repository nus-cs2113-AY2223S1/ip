import java.util.Scanner;

public class Duke {

    /**
     * Prints out a list of save data from the user inputs
     *
     * @param assignment task that is created by OOP
     * @param indexTask index the Task array given by the variable assignment
     */
    public static void printList(Task[] assignment, int indexTask){
        int numberOrder = 1;
        for(int i = 0; i < indexTask; i++) {
            System.out.println("\t " + numberOrder + ".["
                    + assignment[i].getStatusIcon() + "] "
                    + assignment[i].description);
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
        Task[] assignment = new Task[100];
        int indexTask = 0;

        do{
            //Enable user to enter text
            userInput = in.nextLine();
            System.out.print("\t" + lineDivider);
            String[] splitUserInput = userInput.split(" ");

            if(!splitUserInput[0].equals("bye")) {
                if(!splitUserInput[0].equals("list")
                        && !splitUserInput[0].equals("mark")
                        && !splitUserInput[0].equals("unmark")) {
                    assignment[indexTask] = new Task(userInput);
                    System.out.println("\t added: " + assignment[indexTask].description);
                    indexTask++;
                } else if (splitUserInput[0].equals("list")) {
                    printList(assignment, indexTask);
                } else if (splitUserInput[0].equals("mark")) {
                    int markIndex = Integer.parseInt(splitUserInput[1]) - 1;
                    //To handle a case where user tries to mark a task that has not been specified
                    try {
                        assignment[markIndex].markAsDone();
                    }

                    catch(NullPointerException e) {
                        System.out.println("\t You are trying to mark a task that has not been specified!");
                        System.out.println("\t" + lineDivider);
                        continue;
                    }

                    System.out.println("\t Awesome! I've marked this task as done:");
                    System.out.println("\t\t [" + assignment[markIndex].getStatusIcon()
                            + "] " + assignment[markIndex].description);
                } else { //If the splitUserInput[0] is equals to "unmark"
                    int unmarkIndex = Integer.parseInt(splitUserInput[1]) - 1;
                    //To handle a case where user tries to unmark a task that has not been specified
                    try {
                        assignment[unmarkIndex].unmarkAsDone();
                    }
                    catch(NullPointerException e) {
                        System.out.println("\t You are trying to unmark a task that has not been specified!");
                        System.out.println("\t" + lineDivider);
                        continue;
                    }
                    System.out.println("\t Awesome! I've marked this task as not done yet:");
                    System.out.println("\t\t [" + assignment[unmarkIndex].getStatusIcon()
                            + "] " + assignment[unmarkIndex].description);
                }
            } else{
                System.out.println("\t Bye. Hope to see you again soon!");
            }

            System.out.println("\t" + lineDivider);
        }
        while(!userInput.equals("bye"));
    }
}
