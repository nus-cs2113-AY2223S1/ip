package duke;

import duke.task.Parser;

import java.util.Scanner;

public class Ui extends Duke{

    /**
     * takes input line by line from user,
     * translate the input accordingly,
     * when user exits the application
     * saves updated list to file
     * @throws DukeException
     */
    public static void input() throws DukeException {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals(bye)) {
            try {
                Parser.translateInput(line);
            } catch (DukeException e) {
                Message.printError();
            }
            line = in.nextLine();
        }
        try {
            Storage.saveListToFile();
        } catch (DukeException e){
            Message.printSystemError();
        }
        Message.printingExit();
    }
}
