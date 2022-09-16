package duke.taskings;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.messages.Ui;

import java.util.ArrayList;

public class TaskList {


    static final String OUT_OF_BOUNDS = "IndexBeyondBoundError";
    static final String NON_NUMERIC = "NonNumericError";
    static final String WRONG_FORMAT = "WrongFormatError";

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

    public static void getList(ArrayList<Task> tasks) {
        int indexNum = 1;
        Ui.getAcknowledgement("list");
        if (tasks.size() == 0) {
            Ui.printNumOfTasks(0);
        } else {
            for (Task task : tasks) {
                System.out.println(indexNum + ". " + task.printList());
                indexNum += 1;
            }
        }
        Ui.displayLineDivider();
    }

    public static void addTodo(ArrayList<Task> tasks, String taskDescription) {
        Ui.displayLineDivider();
        tasks.add(new Todo("T", taskDescription, false));
        Storage.writeToFile(tasks);
        System.out.println(tasks.get(tasks.size() - 1));
        Ui.printNumOfTasks(tasks.size());
    }

    public static void addDeadline(ArrayList<Task> tasks, String taskDescription) {
        Ui.displayLineDivider();
        // find "/" break point before processing the description and the deadline
        String deadline = taskDescription;
        if (taskDescription.contains("/by")) {
            //update taskDescription and deadline
            String[] tempArray = taskDescription.split("/by");
            taskDescription = tempArray[0];
            deadline = tempArray[1];
        }

        tasks.add(new Deadline("D", taskDescription, false, deadline));
        Storage.writeToFile(tasks);
        System.out.println(tasks.get(tasks.size() - 1));
        Ui.printNumOfTasks(tasks.size());
    }

    public static void addEvent(ArrayList<Task> tasks, String taskDescription) {
        Ui.displayLineDivider();
        String eventPeriod = taskDescription;
        if (taskDescription.contains("/at")) {
            //update taskDescription and deadline
            String[] tempArray = taskDescription.split("/at");
            taskDescription = tempArray[0];
            eventPeriod = tempArray[1];
        }

        tasks.add(new Event("E", taskDescription, false, eventPeriod));
        Storage.writeToFile(tasks);
        System.out.println(tasks.get(tasks.size() - 1));
        Ui.printNumOfTasks(tasks.size());
    }


    public static void processCommand(ArrayList<Task> tasks, int numOfWords, String command, String[] userInput) {
        try {
            if (numOfWords > 2) {
                Ui.showErrorMessage(command, WRONG_FORMAT);
            } else {
                int index = Integer.parseInt(userInput[1]) - 1;
                if (checkIfWithinBounds(index, tasks.size())) {
                    // checks if the user input command is within the current bound of the tasks array.
                    switch (command) {
                    case "mark":
                        tasks.get(index).setDone(true);
                        Ui.getAcknowledgement(command);
                        Ui.getEntryFullStatus(tasks, index);
                        Ui.displayLineDivider();
                        break;
                    case "unmark":
                        tasks.get(index).setDone(false);
                        Ui.getAcknowledgement(command);
                        Ui.getEntryFullStatus(tasks, index);
                        Ui.displayLineDivider();
                        break;
                    case "delete":
                        Ui.getAcknowledgement(command);
                        Ui.getEntryFullStatus(tasks, index);
                        tasks.remove(index);
                        if (tasks.size() == 0) {
                            Ui.printNumOfTasks(0);
                        } else {
                            Ui.printNumOfTasks(tasks.size());
                        }
                        break;
                    default:
                        break;
                    }
                    Storage.writeToFile(tasks); // to update the file with the updated tasks array
                }
            }
        } catch (NumberFormatException e) {
            Ui.showErrorMessage(command, NON_NUMERIC);
        } catch (DukeException e) {
            Ui.showErrorMessage(command, OUT_OF_BOUNDS);
        }
    }

}
