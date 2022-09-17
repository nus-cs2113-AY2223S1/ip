package duke;

import java.util.Scanner;

public class Duke {
    private static final List dukeList = new List();
    public static final String markDone = "mark";
    public static final String delete = "delete";
    public static final String bye = "bye";
    public static final String list = "list";
    public static final String space = " ";
    public static final String unmarkDone = "unmark";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Message.printGreeting();
        echo();
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals(bye)) {
            try {
                translateInput(line);
            } catch (DukeException e) {
                Message.printError();
            }
            line = in.nextLine();
        }
        Message.printingExit();
    }

    public static int checkInteger(String[] wordsInput) throws DukeException {
        if (wordsInput.length != 2 || wordsInput[1].equals("")){
            throw new DukeException();
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(wordsInput[1]);
        } catch (NumberFormatException e){
            throw new DukeException();
        }
        if (taskNumber > dukeList.getListSize()){
            throw new DukeException();
        }
        return taskNumber;
    }

    private static String[] splitInput(String input) throws DukeException {
        String[] wordsInput;
        try {
            wordsInput = input.split(space);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
        return wordsInput;
    }

    public static void translateInput(String input) throws DukeException {
        if (input.equals(list)) {
            dukeList.printList();
            return;
        }
        String[] wordsInput = splitInput(input);
        if (wordsInput[0].equals(unmarkDone)) {
            dukeList.unmarkItemDone(checkInteger(wordsInput));
        } else if (wordsInput[0].equals(markDone)) {
            dukeList.markItemDone(checkInteger(wordsInput));
        } else if (wordsInput[0].equals(delete)) {
            dukeList.deleteTask(checkInteger(wordsInput));
        } else {
            dukeList.addTask(input);
        }
    }

}
