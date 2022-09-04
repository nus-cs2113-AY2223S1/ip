package duke;

import duke.exception.DukeException;
import duke.taskings.Deadline;
import duke.taskings.Event;
import duke.taskings.Task;
import duke.taskings.Todo;

import java.util.Scanner;

public class Duke {

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
     * Prints the correct number of task message when called.
     *
     * @param numOfTasks the current count of tasking
     */
    public static void printNumOfTasks(int numOfTasks) {
        if (numOfTasks == 0) {
            System.out.println("There are no tasks");
        } else if (numOfTasks == 1) {
            System.out.println("There is only 1 task!");
        } else {
            System.out.println("There are " + numOfTasks + " tasks left :( ");
        }
    }

    /**
     * Checks if the checked index is within the current taskIndex and if it is above 0.
     *
     * @param index the checked index
     * @param taskIndex the maximum index of the current number of tasks.
     *
     * @return true if the index is within the bounds. else return DukeException().
     */

    public static boolean checkIfWithinBounds(int index, int taskIndex) throws DukeException {
        if ((index <= 0) || (index >= taskIndex)) {
            throw new DukeException();
        }
        return true;
    }


    public static void main(String[] args) {

        /*
         * array storing the Task objects.
         */
        Task[] tasks = new Task[101];

        /*
         * stores the number of words in the user's string input.
         */
        int numOfWords;

        int taskIndex = 1;
        String inData;
        Scanner scan = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            // continuously receive user input
            inData = scan.nextLine();
            numOfWords = getNumOfWords(inData);

            if (inData.equals("list")) {
                //print entire list if input is equal to "list"
                System.out.println("Here are the tasks in your list:");
                if (taskIndex == 1) {
                    printNumOfTasks(0);
                } else {
                    for (int i = 1; i < taskIndex; i += 1) {
                        System.out.println(i + ". " + tasks[i].printList());
                    }
                }
                System.out.println("____________________________________________________________");

            } else if (numOfWords >= 2) {
                // check if the user input has 2 words before checking for commands "mark" and "unmark"
                String[] parsedInput = inData.split(" ");

                if (numOfWords == 2 && parsedInput[0].equals("unmark")) {
                    // checks if user input has entered "unmark" as the first word.
                    try {
                        int unmarkedIndex = Integer.parseInt(parsedInput[1]);
                        if (checkIfWithinBounds(unmarkedIndex, taskIndex)) {
                            //checks if the user input command is within the current bound of the tasks array.
                            tasks[unmarkedIndex].setDone(false);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[" + tasks[unmarkedIndex].getTaskType() + "]" + "[" + tasks[unmarkedIndex].getStatusIcon() + "] " + tasks[unmarkedIndex].getDescription());
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid unmark command - non numeric index detected.");
                        System.out.println("____________________________________________________________");
                    } catch (DukeException e) {
                        System.out.println("Invalid unmark command - Index out of bounds.");
                        System.out.println("____________________________________________________________");
                    }

                } else if (numOfWords == 2 && parsedInput[0].equals("mark")) {
                    // checks if user input has entered "mark" as the first word.
                    try {
                        int markedIndex = Integer.parseInt(parsedInput[1]);
                        if (checkIfWithinBounds(markedIndex, taskIndex)) {
                            // checks if the user input command is within the current bound of the tasks array.
                            tasks[markedIndex].setDone(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[" + tasks[markedIndex].getTaskType() + "]" + "[" + tasks[markedIndex].getStatusIcon() + "] " + tasks[markedIndex].getDescription());
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark command - non numeric index detected.");
                        System.out.println("____________________________________________________________");
                    } catch (DukeException e) {
                        System.out.println("Invalid unmark command - Index out of bounds.");
                        System.out.println("____________________________________________________________");
                    }

                } else if (taskIndex < 101) {
                    // Synthesized array after removing the command input.
                    StringBuilder synthesizedArr = new StringBuilder();
                    for (int i = 1; i < numOfWords; i += 1) {
                        synthesizedArr.append(parsedInput[i]).append(" ");
                    }
                    String taskDescription = synthesizedArr.toString();

                    switch (parsedInput[0]) {
                    case "todo":
                        tasks[taskIndex] = new Todo(taskDescription);
                        tasks[taskIndex].setTaskType("T");
                        System.out.println(tasks[taskIndex]);
                        printNumOfTasks(taskIndex);
                        taskIndex += 1;
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
                        tasks[taskIndex] = new Deadline(taskDescription, deadline);
                        tasks[taskIndex].setTaskType("D");

                        System.out.println(tasks[taskIndex]);
                        printNumOfTasks(taskIndex);
                        taskIndex += 1;
                        break;

                    case "event":
                        String eventPeriod = taskDescription;
                        if (taskDescription.contains("/at")) {
                            //update taskDescription and deadline
                            String[] tempArray = taskDescription.split("/at");
                            taskDescription = tempArray[0];
                            eventPeriod = tempArray[1];
                        }
                        tasks[taskIndex] = new Event(taskDescription, eventPeriod);
                        tasks[taskIndex].setTaskType("E");
                        System.out.println(tasks[taskIndex]);
                        printNumOfTasks(taskIndex);
                        taskIndex += 1;
                        break;
                    default:
                        System.out.println("Invalid command");
                        System.out.println("____________________________________________________________");
                        break;
                    }
                }
            } else {
                if (inData.equals("bye")) {
                    // exits the while loop if the user inputs is equal to "bye"
                    break;
                } else {
                    System.out.println("Invalid command");
                    System.out.println("____________________________________________________________");
                }
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
