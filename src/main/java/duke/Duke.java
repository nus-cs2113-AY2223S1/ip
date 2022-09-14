package duke;

import duke.taskings.Deadline;
import duke.taskings.Event;
import duke.taskings.Task;
import duke.taskings.Todo;
import duke.taskings.TaskManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            String command = "";
            inData = scan.nextLine();
            inData = inData.trim();
            numOfWords = getNumOfWords(inData);
            if (inData.equals("help")) {
                Message.getHelp();
            } else if (inData.equals("list")) {
                //print entire list if input is equal to "list"
                TaskManager.getList(tasks);
            } else if (inData.equals(("bye"))) {
                Message.showExit();
                break; //exit loop
            } else if (inData.isBlank() || inData.isEmpty()) {
                Message.showWrongCommand();
            } else if (inData.contains(" ")) {
                command = inData.split(" ")[0];
                String[] userInput = inData.split(" ");
                StringBuilder synthesizedArr = new StringBuilder();
                for (int i = 1; i < numOfWords; i += 1) {
                    synthesizedArr.append(userInput[i]).append(" ");
                }
                String taskDescription = synthesizedArr.toString();
                switch (command) {
                case "mark":
                    TaskManager.processCommand(tasks, numOfWords, "mark", userInput);
                    break;
                case "unmark":
                    TaskManager.processCommand(tasks, numOfWords, "unmark", userInput);
                    break;
                case "delete":
                    TaskManager.processCommand(tasks, numOfWords, "delete", userInput);
                    break;
                case "todo":
                    TaskManager.addTodo(tasks, taskDescription);
                    break;
                case "deadline":
                    TaskManager.addDeadline(tasks, taskDescription);
                    break;
                case "event":
                    TaskManager.addEvent(tasks, taskDescription);
                    break;
                default:
                    Message.showWrongCommand();

                }
            } else {
                Message.showWrongCommand();
            }
        }// while loop
    }

    public static class FileOperation {

        /**
         * to check if file exists, then start storing file inputs back into the current tasks array
         *
         * @param tasks the array which we would be inserting entries into
         */
        public static void initialiseFile(ArrayList<Task> tasks) {
            try {
                File file = manageFile();
                Scanner input = new Scanner(file);
                while (input.hasNext()) {
                    String[] parsedStoredTask = input.nextLine().split(" \\| ");
                    switch (parsedStoredTask[0]) {
                    case "T":
                        if (parsedStoredTask[1].equals("X")) {
                            tasks.add(new Todo("T", parsedStoredTask[2], true));
                        } else {
                            tasks.add(new Todo("T", parsedStoredTask[2], false));
                        }
                        break;
                    case "E":
                        if (parsedStoredTask[1].equals("X")) {
                            tasks.add(new Event("E", parsedStoredTask[2], true, parsedStoredTask[3]));
                        } else {
                            tasks.add(new Event("E", parsedStoredTask[2], false, parsedStoredTask[3]));
                        }
                        break;
                    case "D":
                        if (parsedStoredTask[1].equals("X")) {
                            tasks.add(new Deadline("D", parsedStoredTask[2], true, parsedStoredTask[3]));
                        } else {
                            tasks.add(new Deadline("D", parsedStoredTask[2], false, parsedStoredTask[3]));
                        }
                        break;
                    default:
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }

        }


        /**
         * called when file does not exist and creates a new file
         *
         * @return file when new file is created and null if no file is created.
         */
        public static File createFile() {
            try {
                File file = new File("data/dukefile.txt");
                file.createNewFile();
                return file;
            } catch (Exception e) {
                System.out.println("File creation error occurred.");
            }
            return null;
        }

        /**
         * called to check on the existance of directory and file. Function creates missing directory and files when necessary.
         *
         * @return file after ensuring making sure directory and file is set.
         */
        public static File manageFile() {

            File directory = new File("data");
            File file = new File("data/dukefile.txt");
            if (!directory.exists()) {
                directory.mkdir();
                System.out.println("Creating new directory");
            }
            if (!file.exists()) {
                file = createFile();
                System.out.println("* Created new file for use *");
            } else {
                System.out.println("* Existing file is loaded and ready to be used *");
            }
            return file;
        }

        /**
         * updates dukefile.txt when called for each valid user command which requires updating of the tasks array.
         *
         * @param tasks the array containing the entries which would be converted to text format.
         */
        public static void writeToFile(ArrayList<Task> tasks) {
            try {
                FileWriter file = new FileWriter("data/dukefile.txt");
                String textToWrite = "";
                for (Task task : tasks) {
                    switch (task.getTaskType()) {
                    case "D":
                        textToWrite = String.format(task.getTaskType() + " | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + task.getBy());

                        break;
                    case "E":
                        textToWrite = String.format(task.getTaskType() + " | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + task.getAt());

                        break;
                    case "T":
                        textToWrite = String.format(task.getTaskType() + " | " + task.getStatusIcon() + " | " + task.getDescription());
                        break;
                    default:
                        break;
                    }
                    file.write(textToWrite + System.lineSeparator());
                }
                file.close();

            } catch (IOException e) {
                System.out.println("Cannot write into file.");
            }

        }

    }
}
