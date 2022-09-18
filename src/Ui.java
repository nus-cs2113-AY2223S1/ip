import java.util.List;
import java.util.Scanner;

public class Ui{
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
        "Hello! I'm Jackson, your personal chat-bot! :)\n" +
        "What service are you looking for?\n";
    public static final String FAREWELL = "Leaving already? :( Come back soon!\n";
    public static final String FILE_NOT_FOUND = "File not found! Please ensure file path is correct!";
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
    public void listMessage() {
        System.out.println("Do you mean 'list'?\n");
    }

    public void missingFile() {
        System.out.println(FILE_NOT_FOUND);
    }

    public void showList(List<Task> tasks) {
        int itemNumber = 1;
        System.out.println(LINE + "Here are your list of tasks:");
        for (Task task : tasks) {
            System.out.println(itemNumber + "." + task.getDescriptionAndStatus());
            itemNumber++;
        }
        System.out.println(LINE);
    }
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
            "Noted. Following task has been added: " + '\n' + task.getDescriptionAndStatus() + "\n" +
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
