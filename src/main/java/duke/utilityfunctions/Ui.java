package duke.utilityfunctions;
public class Ui {
    public void generateTaskStatus(String taskIcon, String statusIcon, String description) {
        System.out.println("\t[" + taskIcon + "]" + "[" + statusIcon + "] " + description);
    }
    public void drawLine() {
        System.out.println("------------------------------------");
    }
    public void addedMsg() {
        System.out.println("Got it. I've added this task:");
    }

    public void taskCountReminder(int noOfTasks) {
        System.out.printf("Now you have %d tasks in the list.\n",noOfTasks);
    }
    public void notifyListHeader() {
        System.out.println("Here are the tasks in your list:");
    }

    public void notifyUnmarkedHeader() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public void notifyMarkedHeader() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void notifyRemovedHeader() {
        System.out.println("Noted. I have removed this task:");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    public void sayHello() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
