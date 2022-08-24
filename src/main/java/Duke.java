import java.util.Scanner;

public class Duke {

    /**
     * Function checks if the user's 2nd word input is a numeral value whenever the 1st word input is "mark" or "unmark".
     *
     * @param str the 2nd word input by the user whenever the 1st word input is "mark" or "unmark"
     * @return boolean true if the string is a numeral, and false if it contains any other characters.
     */
    public static boolean checkIsNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

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

    public static void main(String[] args) {
        Task[] tasks = new Task[101];
        /**/
        int numOfWords = 0;

        int taskIndex = 1;
        String inData;
        Scanner scan = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) { /* continuously receive user input */
            inData = scan.nextLine();
            numOfWords = getNumOfWords(inData);
            if (inData.equals("list")) { /* print entire list if input is equal to "list" */
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < taskIndex; i += 1) {
                    System.out.println(i + ". " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
                System.out.println("____________________________________________________________");

            } else if ( numOfWords == 2) { /* check if the user input has 2 words before checking for commands "mark" and "unmark" */
                String[] arr = inData.split(" ");
                if (arr[0].equals("unmark")) { /* checks if user input has entered "unmark" as the first word */
                    if (checkIsNumeric(arr[1])) { /* check if the 2nd word is indeed a numeric value , else the input would be stored as a tasking */
                        int unmarkedIndex = Integer.parseInt(arr[1]);
                        if ((unmarkedIndex > 0) && (unmarkedIndex < taskIndex)) { /* checks if the user input command is within the current bound of the tasks array. */
                            tasks[unmarkedIndex].setDone(false);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[" + tasks[unmarkedIndex].getStatusIcon() + "] " + tasks[unmarkedIndex].getDescription());
                        } else {
                            System.out.println("Invalid unmark command");
                        }
                    } else { /* add a new tasking */
                        if (taskIndex < 101) {
                            tasks[taskIndex] = new Task(inData);
                            taskIndex += 1;
                            System.out.println("____________________________________________________________");
                            System.out.println("added: " + tasks[taskIndex - 1].description);
                            System.out.println("____________________________________________________________");
                        }
                    }
                } else if (arr[0].equals("mark")) { /* checks if user input has entered "mark" as the first word. */
                    if (checkIsNumeric(arr[1])) {
                        int markedIndex = Integer.parseInt(arr[1]);
                        if ((markedIndex > 0) && (markedIndex < taskIndex)) { /* checks if the user input command is within the current bound of the tasks array. */
                            tasks[markedIndex].setDone(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[" + tasks[markedIndex].getStatusIcon() + "] " + tasks[markedIndex].getDescription());
                        } else {
                            System.out.println("Invalid mark command");
                        }
                    } else { /* add a new tasking */
                        if (taskIndex < 101) { /* prevents the insertion of new tasks into the tasks array */
                            tasks[taskIndex] = new Task(inData);
                            taskIndex += 1;
                            System.out.println("____________________________________________________________");
                            System.out.println("added: " + tasks[taskIndex - 1].description);
                            System.out.println("____________________________________________________________");
                        }
                    }
                } else { /* add a new tasking */
                    if (taskIndex < 101) { /* prevents the insertion of new tasks into the tasks array */
                        tasks[taskIndex] = new Task(inData);
                        taskIndex += 1;
                        System.out.println("____________________________________________________________");
                        System.out.println("added: " + tasks[taskIndex - 1].description);
                        System.out.println("____________________________________________________________");
                    }
                }
            } else {
                if (inData.equals("bye")) { /* exits the while loop if the user inputs is equal to "bye" */
                    break;
                } else { /* add a new tasking */
                    if (taskIndex < 101) { /* prevents the insertion of new tasks into the tasks array */
                        tasks[taskIndex] = new Task(inData);
                        taskIndex += 1;
                        System.out.println("____________________________________________________________");
                        System.out.println("added: " + tasks[taskIndex - 1].description);
                        System.out.println("____________________________________________________________");
                    }
                }
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

