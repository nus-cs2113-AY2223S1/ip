package duke.utilityfunctions;

/**
 * This class handles all the UI-related behaviours for Duke
 */
public class Ui {
    /**
     * Generates and prints task status for a given task
     * @param taskIcon the icon representing type of task, whether it is a event, deadline or todo
     * @param statusIcon the icon representing status of task, whether it is done or not
     * @param description the description of the task
     */
    public void generateTaskStatus(String taskIcon, String statusIcon, String description) {
        System.out.println("\t[" + taskIcon + "]" + "[" + statusIcon + "] " + description);
    }
    public void drawLine() {
        System.out.println("------------------------------------");
    }
    public void addedMsg() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints a notification to remind user how many tasks they have in the list
     * @param noOfTasks the number of tasks in task array
     */
    public void taskCountReminder(int noOfTasks) {
        System.out.printf("Now you have %d tasks in the list.\n",noOfTasks);
    }
    public void notifyListHeader() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Notify user that task has been unmarked
     */
    public void notifyUnmarkedHeader() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Notify user that task has been marked as done
     */
    public void notifyMarkedHeader() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void notifyRemovedHeader() {
        System.out.println("Noted. I have removed this task:");
    }


    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Greets the user with a logo
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    /**
     * Say hello to the user
     */
    public void sayHello() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
