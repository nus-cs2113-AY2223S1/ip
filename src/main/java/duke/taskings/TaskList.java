package duke.taskings;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.messages.Ui;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TaskList {


    static final String OUT_OF_BOUNDS = "IndexBeyondBoundError";
    static final String NON_NUMERIC = "NonNumericError";
    static final String WRONG_FORMAT = "WrongFormatError";
    static final String DEADLINE = "D";
    static final String EVENT = "E";
    static final String TODO = "T";

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

    /**
     * Finds the keyword that the user requested.
     *
     * @param tasks      the array that would be searched
     * @param keyword    the search keyword defined by the user
     * @param numOfWords the number of words in the user command. If > 2, means invalid command
     */
    public static void findKeyword(ArrayList<Task> tasks, String keyword, int numOfWords) {
        if (numOfWords > 2) {
            System.out.println("Please enter only 1 search item");
        } else {
            int numOfMatchedTasks = 0;
            keyword = keyword.toLowerCase();
            ArrayList<Task> matchedTasks = new ArrayList<>();
            Ui.displayLineDivider();
            for (Task task : tasks) {
                if (task.getDescription().toLowerCase().contains(keyword)) {
                    matchedTasks.add(task);
                    numOfMatchedTasks += 1;

                }
            }
            Ui.printMatchedTasks(matchedTasks);
            if (numOfMatchedTasks == 1) {
                System.out.println("There is 1 task that contains your keyword!");
            } else if (numOfMatchedTasks == 0) {
                System.out.println("There are no matching tasks!!");
            } else {
                System.out.println("There are " + numOfMatchedTasks + " tasks that has your keyword!");
            }
            Ui.displayLineDivider();
        }
    }


    /**
     * Prints out all the entries in the Arraylist.
     *
     * @param tasks the array which would have it's entries printed out
     */

    public static void getList(ArrayList<Task> tasks) {
        int indexNum = 1;
        Ui.getAcknowledgement("list");
        if (tasks.size() == 0) {
            Ui.printNumOfTasks(0);
        } else {
            for (Task task : tasks) {
                System.out.println(indexNum + ". " + task.toString());
                indexNum += 1;
            }
        }
        Ui.displayLineDivider();
    }


    /**
     * add a new todo entry to the array before storing the updated changes.
     *
     * @param tasks           the array which would receive a new todo entry
     * @param taskDescription The parsed task description from the user.
     */
    public static void addTodo(ArrayList<Task> tasks, String taskDescription) {
        Ui.displayLineDivider();
        tasks.add(new Todo(taskDescription, false));
        Storage.writeToFile(tasks);
        System.out.print("New Todo task added: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        Ui.printNumOfTasks(tasks.size());
    }

    /**
     * add a new Deadline entry to the array before storing the updated changes.
     *
     * @param tasks           the array which would receive a new deadline entry
     * @param taskDescription The parsed task description from the user.
     */
    public static void addDeadline(ArrayList<Task> tasks, String taskDescription) {
        Locale locale = new Locale("en", "UK");
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        Ui.displayLineDivider();
        // find "/" break point before processing the description and the deadline
        String deadline = taskDescription;
        try {
            if (taskDescription.contains("/by")) {
                //update taskDescription and deadline
                String[] tempArray = taskDescription.split("/by");
                taskDescription = tempArray[0];
                deadline = tempArray[1];
                deadline = deadline.trim();

                if (deadline.contains("/") && deadline.contains(" ")) {
                    String[] dateAndTime = deadline.split(" ");
                    if (dateAndTime.length > 2) {
                        System.out.println("Wrong Timing format. Should be < d/mm/yyyy 2359 > format");
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
                        tasks.add(new Deadline(taskDescription, dateTime.toString(), false));
                        System.out.print("New Deadline task added: ");
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        Ui.printNumOfTasks(tasks.size());
                    }
                } else {
                    System.out.println("Wrong Timing format. Should be < d/mm/yyyy 2359 > format");
                }
                Storage.writeToFile(tasks);
            } else {
                System.out.println("Wrong format !");
                System.out.println("Example: deadline food preparation /by 1/09/2022 0900");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Wrong Timing format. Should be < d/mm/yyyy 2359 > format");
        }
    }

    /**
     * add a new event entry to the array before storing the updated changes.
     *
     * @param tasks           the array which would receive a new event entry
     * @param taskDescription The parsed task description from the user.
     */
    public static void addEvent(ArrayList<Task> tasks, String taskDescription) {
        Locale locale = new Locale("en", "UK");
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);

        Ui.displayLineDivider();
        try {
            String eventPeriod = taskDescription;
            if (taskDescription.contains("/at")) {
                //update taskDescription and deadline
                String[] tempArray = taskDescription.split("/at");
                taskDescription = tempArray[0];
                eventPeriod = tempArray[1];
                eventPeriod = eventPeriod.trim();

                if (eventPeriod.contains("/") && eventPeriod.contains(" ")) {
                    String[] dateAndTime = eventPeriod.split(" ");
                    if (dateAndTime.length > 2) {
                        System.out.println("Wrong Timing format. Should be < d/mm/yyyy 2359 > format");
                    } else {

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(eventPeriod, formatter);
                        System.out.print("New Event task added: ");
                        tasks.add(new Event(taskDescription, dateTime.toString(), false));
                        System.out.println(tasks.get(tasks.size() - 1));
                        Ui.printNumOfTasks(tasks.size());
                    }
                } else {
                    System.out.println("Wrong Timing format. Should be < d/mm/yyyy 2359 > format");
                }

                Storage.writeToFile(tasks);

            } else {
                System.out.println("Wrong format !");
                System.out.println("Example: event food tasting /at 1/09/2022 0900");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Wrong Timing format. Should be < d/mm/yyyy 2359 > format");
        }

    }


    /**
     * further processes the parsed user commands and allocate the correct Ui messages to respond to the user.
     *
     * @param tasks     the array which would have it's entries retrieved.
     * @param command   the 1st string given by the user.
     * @param userInput the array containing 2 Strings. With the first being the "command" and the second being the "index" which the command will act on
     */
    public static void processCommand(ArrayList<Task> tasks, String command, String[] userInput) {
        try {
            if (userInput.length > 2) {
                Ui.showErrorMessage(command, WRONG_FORMAT);
            } else {
                int index = Integer.parseInt(userInput[1]) - 1;
                if (checkIfWithinBounds(index, tasks.size())) {
                    // checks if the user input command is within the current bound of the tasks array.
                    switch (command) {
                    case "mark":
                        tasks.get(index).setDone(true);
                        Ui.getAcknowledgement(command);
                        Ui.getEntryStatus(tasks, index);
                        Ui.displayLineDivider();
                        break;
                    case "unmark":
                        tasks.get(index).setDone(false);
                        Ui.getAcknowledgement(command);
                        Ui.getEntryStatus(tasks, index);
                        Ui.displayLineDivider();
                        break;
                    case "delete":
                        Ui.getAcknowledgement(command);
                        Ui.getEntryStatus(tasks, index);
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
