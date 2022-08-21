import java.awt.*;
import java.util.Scanner;

public class Duke {
    private boolean isActive = true;
    private Task[] tasks = new Task[100];
    private int taskCount = 0;
    private void greet() {
        String greetMessage = "Hello! I'm Banana\n"
                + "How can I help you?";
        reply(greetMessage);
    }
    private void bye() {
        String byeMessage = "Good bye. Hope to see you again soon!";
        reply(byeMessage);
    }
    private void reply (String message) {
        // Split this string to easily indent new line when replying
        String[] messageArray = message.split("\n");
        System.out.println("*************************************************************");
        for (String item: messageArray) {
            //
            System.out.print("      ");
            System.out.print(item + '\n');
        }
        System.out.println("*************************************************************");
    }
    private void confirmAdd(String task) {
        this.reply("I added one task to your list: " + task);
    }
    private void printList(Task[] tasks) {
        int i = 0;
        if (tasks[0] == null) {
            this.reply("There is nothing in the list right now");
        } else {
            System.out.println("*************************************************************");
            System.out.println("      " + "Here are " + String.valueOf(taskCount) + " tasks in your list:");
            for (; i < taskCount; i++) {
                System.out.println("        " + tasks[i].getNumber() + "." + tasks[i].taskInfo());
            }
            System.out.println("*************************************************************");
        }
    }

    private void markDone(int num) {
        if (tasks[num].isDone()) {
            reply("This task was marked done before:\n"
                    + " " + tasks[num].taskInfo());
        } else {
            tasks[num].setDone(true);
            reply("Congratulations! You have done this task:\n"
                    + " " + tasks[num].taskInfo());
        }
    }
    private void markUndone(int num) {
        if (!tasks[num].isDone()) {
            reply("This task was marked undone before:\n"
                    + " " + tasks[num].taskInfo());
        } else {
            tasks[num].setDone(false);
            reply("OK, I've marked this task as not done yet:\n"
                    + " " + tasks[num].taskInfo());
        }
    }
    private void parseMessage(String message) {
        //split the input to get the command
        String[] cmdArray = message.split(" ");
        switch(cmdArray[0]) {
        case "bye":
            this.bye();
            this.isActive = false;
            break;
        case "list":
            this.printList(tasks);
            break;
        case "mark":
            int numDone = Integer.parseInt(cmdArray[1]);
            if (numDone > taskCount) {
                reply("You only have " + String.valueOf(taskCount) + " in the list");
            } else {
                this.markDone(numDone - 1);
            }
            break;
        case "unmark":
            int numUndone = Integer.parseInt(cmdArray[1]);
            if (numUndone > taskCount) {
                reply("You only have " + String.valueOf(taskCount) + " in the list");
            } else {
                this.markUndone(numUndone - 1);
            }
            break;
        default:
            tasks[taskCount] = new Task(taskCount+1, message);
            taskCount++;
            this.confirmAdd(message);
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner commands = new Scanner(System.in);
        String command;
        while (duke.isActive) {
            command = commands.nextLine();
            //need to trim all the trailing and leading spaces of the input for better accuracy
            duke.parseMessage(command.trim());
        }
    }
}
