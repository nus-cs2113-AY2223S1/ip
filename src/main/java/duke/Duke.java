package duke;

import duke.chatbot.Chatbot;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void runCommand(Chatbot chatbot, String command, String description) {
        switch(command) {
        case "bye":
            chatbot.bye();
            break;
        case "list":
            chatbot.listTasks();
            break;
        case "mark":
            chatbot.markTask(description, true);
            break;
        case "unmark":
            chatbot.markTask(description, false);
            break;
        case "todo":
        case "deadline":
        case "event":
            chatbot.addTask(command, description);
            break;
        case "delete":
            chatbot.deleteTask(description);
            break;
        default:
            chatbot.alertInvalidCommand();
            break;
        }
    }

    public static void main(String[] args) throws IOException {
        Chatbot chatbot = new Chatbot();
        chatbot.greet();
        Scanner in = new Scanner(System.in);

        //@@author chydarren-reused
        // Reused from https://stackoverflow.com/a/38985883
        // with minor modifications
        File directory = new File("data");
        if(!directory.exists()) {
            directory.mkdir();
        }

        File file = new File("data/data.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        //@@author

        String input, command, description;
        do {
            input = in.nextLine();
            if (input.contains(" ")) {
                command = input.split(" ")[0];
                description = input.substring(input.indexOf(' ') + 1);
            } else {
                command = input;
                description = "";
            }
            runCommand(chatbot, command, description);
        } while (!input.equals("bye"));
    }
}