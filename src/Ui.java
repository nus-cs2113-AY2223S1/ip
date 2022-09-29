import java.util.List;
import java.util.Scanner;

public class Ui{
    public void unmarkErrorMessage() {
        System.out.println("Please input a task number to unmark");
    }
    public void markErrorMessage() {
        System.out.println("Please input a task number to mark");
    }
    public void deleteErrorMessage(){
        System.out.println("Please input a task number for me to delete the task :)");
    }
    public void missingKeywordMessage() {
        System.out.println("Please input a keyword to find a matching task!");
    }
    public static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public static final String BOT_LOGO =
            "Hello from\n" +
            "       _            _                    \n"
            + "      | |          | |                   \n"
            + "      | | __ _  ___| | _____  ___  _ __  \n"
            + "  _   | |/ _` |/ __| |/ / __|/ _ \\| '_ \\ \n"
            + " | |__| | (_| | (__|   <\\__ \\ (_) | | | |\n"
            + "  \\____/ \\__,_|\\___|_|\\_\\___/\\___/|_| |_|\n";
    public static final String INTRODUCTION =
        "Hello! I'm Jackson, your personal task manager! :)\n";
    public static final String FAREWELL = "Leaving already? :( Come back soon!\n";
    final Scanner readInput;
    public Ui() {
        readInput = new Scanner(System.in);
    }
    public String userCommand() {
        return readInput.nextLine();
    }
    public void greet() {
        System.out.println(
            BOT_LOGO +
            LINE +
            INTRODUCTION +
            LINE
        );
    }
    public void exit() {
        System.out.println(
            LINE +
            FAREWELL +
            LINE
        );
    }

    public void promptMessage() {
        System.out.println("~~~~~Please enter a command~~~~~");
    }
    public void listMessage() {
        System.out.println("Do you mean 'list'?");
    }
    public void loadListMessage() {
        System.out.println("Loading existing file data...\n");
    }
    public void createNewListMessage() {
        System.out.println("No existing file data...creating empty list\n");
    }
    public void missingFile() {
        System.out.println("File not found! Please ensure file path is correct!");
    }
    public void duplicateDetected() {
        System.out.println("This task is already in the list!");
    }
    public void outOfBoundsMessage() {
        System.out.println("Sorry, there is no such task. Please input a correct number");
    }
    public void notIntegerMessage() {
        System.out.println("Please input a number!");
    }
    public void invalidCommandMessage() {
        System.out.println("Sorry, please input a proper command!");
    }
    /**
     * display the entire list of tasks to the user
     * @param tasks list of all the tasks the user has added
     */
    public void showList(List<Task> tasks) {
        int itemNumber = 1;
        System.out.println(LINE + "Here are your list of tasks:");
        for (Task task : tasks) {
            System.out.println(itemNumber + "." + task.getDescriptionAndStatus());
            itemNumber++;
        }
        System.out.println(LINE);
    }

    /**
     * Display the number of tasks that include the specified keyword, and their corresponding
     * ranking in the list
     * @param tasks list of all the tasks the user has added
     * @param keyword word specified by the user to be searched for
     */
    public void showMatchedTasks(List<Task> tasks, String keyword) {
        System.out.println(LINE + "Here are the list of matching tasks:");
        int count = 0;
        int itemNumber = 1;
        for (Task task : tasks) {
            if (task.getDescriptionAndStatus().contains(keyword)) {
                System.out.println(itemNumber + "." + task.getDescriptionAndStatus());
                count++;
            }
            itemNumber++;
        }
        if (count == 0) {
            System.out.println("There are no matching tasks!");
        }
        System.out.println(LINE);
    }
    public void markTaskMessage(Task task) {
        System.out.println(
            LINE +
            "The following task been marked as completed:\n" +
            task.getDescriptionAndStatus() + "\n" +
            LINE
        );
    }
    public void unmarkTaskMessage(Task task) {
        System.out.println(
            LINE +
            "The following task been marked as not done yet:\n" +
            task.getDescriptionAndStatus() + "\n" +
            LINE
        );
    }
    public void addTaskMessage(Task task, List<Task> tasks) {
        System.out.println(
            LINE +
            "Noted. Following task has been added: " + '\n' +
            task.getDescriptionAndStatus() + "\n" +
            "Total tasks in list: " + tasks.size() + '\n' +
            LINE
        );
    }
    public void deleteTaskMessage(Task task, List<Task> tasks) {
        System.out.println(
            LINE +
            "OK! I will remove the following task:\n" +
            task.getDescriptionAndStatus() + "\n" +
            "Total tasks in list: " + tasks.size() + '\n' +
            LINE
        );
    }
}
