package duke.ui;


import duke.data.Messages;
import duke.command.CommandResult;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {

    public final Scanner input;
    public final PrintStream output;

    public TextUi(){
        this.input = new Scanner(System.in);
        this.output = System.out;
    }

    public static final String UI_PROMPT = ">>> ";
  

    public String getUserCommand() {
        output.print(UI_PROMPT);
        String userInput = input.nextLine();
        while(shouldIgnore(userInput)){
            userInput = input.nextLine();
        }
        return input.nextLine();
    }

    private boolean shouldIgnore(String userInput){
        return userInput.trim().isEmpty();
    }

    public void showToUser(String... messages) {
        for (String m : messages) {
            System.out.println(m);
        }
    }

    public void showToUserDivider(String... messages) {
        System.out.println(Messages.DIVIDER);
        showToUser(messages);
        System.out.println(Messages.DIVIDER);
    }


    public void showResultToUser(CommandResult result) {
        showToUserDivider(result.messageTop,result.target.toString(), result.messageBottom);
    }

    public void showWelcomeMessage() {
        this.showToUserDivider(Messages.WELCOME);
    }

    public void showExitMessage() {
        this.showToUserDivider(Messages.EXIT);
    }

    public void showUnknownMessage() {
        this.showToUserDivider(Messages.COMMAND_LISTS);
    }
}
