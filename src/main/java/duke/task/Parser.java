package duke.task;

import duke.Duke;
import duke.DukeException;

public class Parser extends Duke {

    /**
     * checks that the number input by user is valid
     * @param wordsInput
     * @return
     * @throws DukeException
     */
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

    /**
     * splits input to spaces and checks that it is valid
     * @param input
     * @return input string divided by space
     * @throws DukeException
     */
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
        switch (wordsInput[0]) {
            case unmarkDone:
                dukeList.unmarkItemDone(checkInteger(wordsInput));
                break;
            case markDone:
                dukeList.markItemAsDoneAndPrintDone(checkInteger(wordsInput));
                break;
            case find:
                String[] divideByFirstSpace = input.split(space, 2);
                if (divideByFirstSpace.length < 2 || divideByFirstSpace[1].equals(emptyString)){
                    throw new DukeException();
                }
                dukeList.findTasks(divideByFirstSpace[1]);
                break;
            case delete:
                dukeList.deleteTask(checkInteger(wordsInput));
                break;
            default:
                dukeList.addTask(input);
                break;
        }
    }
}
