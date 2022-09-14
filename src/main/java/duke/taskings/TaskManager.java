package duke.taskings;

import duke.FileOperation;
import duke.Message;
import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskManager {

    static final String OUTOFBOUNDS = "IndexBeyondBoundError";
    static final String NONNUMERIC = "NonNumericError";
    static final String WRONGFORMAT = "WrongFormatError";

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
    }

    public static void addTodo(ArrayList<Task> tasks, String taskDescription) {
        tasks.add(new Todo("T", taskDescription, false));
        FileOperation.writeToFile(tasks);
        System.out.println(tasks.get(tasks.size() - 1));
        Message.printNumOfTasks(tasks.size());
    }

    public static void addDeadline(ArrayList<Task> tasks, String taskDescription) {
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
    }

    public static void addEvent(ArrayList<Task> tasks, String taskDescription) {
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
    }

    public static void processDeleteCommand(ArrayList<Task> tasks, int numOfWords, String command, String[] userInput) {

        try {
            if (numOfWords > 2) {
                Message.showErrorMessage("delete", WRONGFORMAT);
            } else {
                int deleteIndex = Integer.parseInt(userInput[1]) - 1;
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
            }
        } catch (NumberFormatException e) {
            Message.showErrorMessage("delete", NONNUMERIC);
        } catch (DukeException e) {
            Message.showErrorMessage("delete", OUTOFBOUNDS);
        }
    }

    public static void processMarkingCommand(ArrayList<Task> tasks, int numOfWords, String command, String[] userInput) {
        try {
            if (numOfWords > 2) {
                Message.showErrorMessage(command, WRONGFORMAT);
            } else {
                int index = Integer.parseInt(userInput[1]) - 1;
                if (checkIfWithinBounds(index, tasks.size())) {
                    // checks if the user input command is within the current bound of the tasks array.
                    if (command.equals("mark")) {
                        tasks.get(index).setDone(true);
                    } else {
                        tasks.get(index).setDone(false);
                    }

                    Message.getAcknowledgement(command);
                    Message.getEntryFullStatus(tasks, index);
                    Message.displayLineDivider();
                    FileOperation.writeToFile(tasks);
                }
            }
        } catch (NumberFormatException e) {
            Message.showErrorMessage(command, NONNUMERIC);
        } catch (DukeException e) {
            Message.showErrorMessage(command, OUTOFBOUNDS);
        }
    }

}
