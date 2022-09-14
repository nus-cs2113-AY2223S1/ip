package duke;

import duke.exception.DukeException;
import duke.taskings.Deadline;
import duke.taskings.Event;
import duke.taskings.Task;
import duke.taskings.Todo;
import duke.FileOperation;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static final String OUTOFBOUNDS = "IndexBeyondBoundError";
    static final String NONNUMERIC = "NonNumericError";


    /**
     * Returns the number of words within a string.
     *
     * @param input the string input from the user
     * @return the number of words in the string
     */

    public static int getNumOfWords(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        String[] words = input.split("\\s+");
        return words.length;
    }

    /**
     * Checks if the checked index is within the current taskIndex and if it is above 0.
     *
     * @param index     the checked index
     * @param arraySize the total number of entries in the tasks array.
     * @return true if the index is within the bounds. else return DukeException().
     */

    public static boolean checkIfWithinBounds(int index, int arraySize) throws DukeException {
        if ((index < 0) || (index >= arraySize)) {
            throw new DukeException();
        }
        return true;
    }


    public static void main(String[] args) {

        // array storing the Task objects.
        ArrayList<Task> tasks = new ArrayList<Task>();

        //stores the number of words in the user's string input.
        int numOfWords;


        String inData;
        Scanner scan = new Scanner(System.in);
        Message.greet();
        FileOperation.initialiseFile(tasks);
        Message.displayLineDivider();

        while (true) {
            // continuously receive user input
            inData = scan.nextLine();
            numOfWords = getNumOfWords(inData);

            if (inData.equals("list")) {
                //print entire list if input is equal to "list"
                int indexNum = 1;
                Message.getAcknowledgement("list");
                if (tasks.size() == 0) {
                    Message.printNumOfTasks(0);
                } else {
                    for (Task task : tasks) {
                        System.out.println(indexNum + ". " + task.printList());
                        indexNum += 1;
                    }
                }
                Message.displayLineDivider();
            } else if (numOfWords >= 2) {
                // check if the user input has 2 words before checking for commands "mark" , "unmark" or "delete"
                String[] parsedInput = inData.split(" ");

                if (numOfWords == 2 && parsedInput[0].equals("unmark")) {
                    // checks if user input has entered "unmark" as the first word.
                    try {
                        int unmarkedIndex = Integer.parseInt(parsedInput[1]) - 1;
                        if (checkIfWithinBounds(unmarkedIndex, tasks.size())) {
                            //checks if the user input command is within the current bound of the tasks array.
                            tasks.get(unmarkedIndex).setDone(false);

                            Message.getAcknowledgement("unmark");
                            Message.getEntryFullStatus(tasks, unmarkedIndex);
                            Message.displayLineDivider();
                            FileOperation.writeToFile(tasks);
                        }
                    } catch (NumberFormatException e) {
                        Message.showErrorMessage("unmark", NONNUMERIC);
                    } catch (DukeException e) {
                        Message.showErrorMessage("unmark", OUTOFBOUNDS);
                    }

                } else if (numOfWords == 2 && parsedInput[0].equals("mark")) {
                    // checks if user input has entered "mark" as the first word.
                    try {
                        int markedIndex = Integer.parseInt(parsedInput[1]) - 1;
                        if (checkIfWithinBounds(markedIndex, tasks.size())) {
                            // checks if the user input command is within the current bound of the tasks array.
                            tasks.get(markedIndex).setDone(true);
                            Message.getAcknowledgement("mark");
                            Message.getEntryFullStatus(tasks, markedIndex);
                            Message.displayLineDivider();
                            FileOperation.writeToFile(tasks);
                        }
                    } catch (NumberFormatException e) {
                        Message.showErrorMessage("mark", NONNUMERIC);
                    } catch (DukeException e) {
                        Message.showErrorMessage("mark", OUTOFBOUNDS);
                    }
                } else if (numOfWords == 2 && parsedInput[0].equals("delete")) {
                    try {
                        int deleteIndex = Integer.parseInt(parsedInput[1]) - 1;
                        if (checkIfWithinBounds(deleteIndex, tasks.size())) {
                            // checks if the user input command is within the current bound of the tasks array.

                            Message.getAcknowledgement("delete");
                            Message.getEntryFullStatus(tasks, deleteIndex);
                            tasks.remove(deleteIndex);
                            FileOperation.writeToFile(tasks); // to update the file with the updated tasks array
                            if (tasks.size() == 0) {
                                Message.printNumOfTasks(0);
                            } else {
                                Message.printNumOfTasks(tasks.size());
                            }
                            FileOperation.writeToFile(tasks);

                            Message.displayLineDivider();
                        }
                    } catch (NumberFormatException e) {
                        Message.showErrorMessage("delete", NONNUMERIC);
                    } catch (DukeException e) {
                        Message.showErrorMessage("delete", OUTOFBOUNDS);
                    }

                } else {

                    // Synthesized array after removing the command input.
                    StringBuilder synthesizedArr = new StringBuilder();
                    for (int i = 1; i < numOfWords; i += 1) {
                        synthesizedArr.append(parsedInput[i]).append(" ");
                    }
                    String taskDescription = synthesizedArr.toString();

                    switch (parsedInput[0]) {
                    case "todo":

                        tasks.add(new Todo("T", taskDescription, false));
                        FileOperation.writeToFile(tasks);
                        System.out.println(tasks.get(tasks.size() - 1));
                        Message.printNumOfTasks(tasks.size());

                        break;
                    case "deadline":
                        // find "/" break point before processing the description and the deadline
                        String deadline = taskDescription;
                        if (taskDescription.contains("/by")) {
                            //update taskDescription and deadline
                            String[] tempArray = taskDescription.split("/by");
                            taskDescription = tempArray[0];
                            deadline = tempArray[1];
                        }


                        tasks.add(new Deadline("D", taskDescription, false, deadline));
                        FileOperation.writeToFile(tasks);
                        System.out.println(tasks.get(tasks.size() - 1));
                        Message.printNumOfTasks(tasks.size());

                        break;

                    case "event":
                        String eventPeriod = taskDescription;
                        if (taskDescription.contains("/at")) {
                            //update taskDescription and deadline
                            String[] tempArray = taskDescription.split("/at");
                            taskDescription = tempArray[0];
                            eventPeriod = tempArray[1];
                        }

                        tasks.add(new Event("E", taskDescription, false, eventPeriod));
                        FileOperation.writeToFile(tasks);
                        System.out.println(tasks.get(tasks.size() - 1));
                        Message.printNumOfTasks(tasks.size());

                        break;
                    default:
                        Message.showWrongCommand();
                    }
                }
            } else {
                if (inData.equals("bye")) {
                    // exits the while loop if the user inputs is equal to "bye"
                    break;
                } else if (inData.equals("help")) {
                    Message.getHelp();
                } else {
                    Message.showWrongCommand();
                }
            }
        }
        Message.showExit();

    }
}
