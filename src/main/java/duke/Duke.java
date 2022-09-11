package duke;

import duke.chatbot.ChatBot;
import duke.exception.InvalidCommandException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void runCommand(ChatBot chatbot, String command, String description) throws InvalidCommandException {
        switch(command) {
        case "bye":
            chatbot.bye();
            break;
        case "list":
            chatbot.listTasks();
            break;
        case "mark":
        case "unmark":
            chatbot.markTask(command, description);
            break;
        case "todo":
        case "deadline":
        case "event":
            chatbot.addTask(command, description, false, true);
            break;
        case "delete":
            chatbot.deleteTask(description);
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    public static void main(String[] args) throws IOException {
        ChatBot chatbot = new ChatBot();
        chatbot.greet();
        Scanner in = new Scanner(System.in);

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
            try {
                runCommand(chatbot, command, description);
            } catch (InvalidCommandException e) {
                chatbot.alertInvalidCommand();
            }
        } while (!input.equals("bye"));
    }
}