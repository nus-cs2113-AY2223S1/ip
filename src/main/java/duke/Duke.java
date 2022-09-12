package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String filePath = "data/duke.txt";
    private static final String fileFolder = "data";

    public static void main(String[] args) throws UnknownInputException, IOException {
        //create folder if needed
        File folder = new File(fileFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }

        //create file if needed
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }

        //create tasks arraylist from file
        //tasks is an array list collection of task objects
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File dataFile = new File(filePath);
            Scanner scanner = new Scanner(dataFile);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String category = line.substring(0, 1);
                String descriptor = line.substring(8);
                int dateIndex = descriptor.indexOf('|');
                switch (category) {
                    case "T":
                        tasks.add(new Todo(descriptor));
                        break;
                    case "E":
                        tasks.add(new Event(descriptor.substring(0, dateIndex - 1), descriptor.substring(dateIndex + 2)));
                        break;
                    case "D":
                        tasks.add(new Deadline(descriptor.substring(0, dateIndex - 1), descriptor.substring(dateIndex + 2)));
                        break;
                    default:
                        tasks.add(new Task(descriptor));
                        break;
                }
                if (line.charAt(4) == 'X') {
                    tasks.get(tasks.size() - 1).setStatus(Boolean.TRUE);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }



        //Duke
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        //some initialization
        String input = " ";
        String description, numericString;
        Integer markedNum;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        input = input.trim();



        // duke runs until a "bye" is entered
        while (!input.equals("bye")) {
            String [] splitInput = input.split(" ");

            // list commands duke to list all the tasks stored and their completion status
            // try at the start cos of the errors possibly
            try {
                switch (splitInput[0]) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + ". " + tasks.get(i));
                        }
                        System.out.println();
                        break;

                    case "mark":
                        // mark x commands duke to mark the corresponding task as completed
                        numericString = input.substring(input.indexOf(" ") + 1);
                        markedNum = Integer.parseInt(numericString) - 1;
                        tasks.get(markedNum).setStatus(Boolean.TRUE);
                        //print to CLI
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("  " + tasks.get(markedNum) + "\n");
                        //save
                        writeToFile(filePath,tasks);
                        break;

                    case "unmark":
                        // unmark x commands duke to mark the corresponding task as uncompleted
                        // Exceptions could occur
                        numericString = input.substring(input.indexOf(" ") + 1);
                        markedNum = Integer.parseInt(numericString) - 1;
                        tasks.get(markedNum).setStatus(Boolean.FALSE);
                        //print to CLI
                        System.out.println("Oh no :( I've marked it as uncompleted: ");
                        System.out.println("  " + tasks.get(markedNum) + "\n");
                        //save
                        writeToFile(filePath,tasks);
                        break;

                    case "todo":
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        }
                        description = input.substring(input.indexOf(" ") + 1);
                        Task td = new Todo(description);

                        // print to CLI
                        taskPrint(tasks, td);
                        //save
                        appendToFile(filePath,td);
                        break;

                    case "deadline":
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        }
                        description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /by "));
                        String by = input.substring(input.indexOf("/by ") + 4);
                        Task d = new Deadline(description, by);
                        // print to CLI
                        taskPrint(tasks, d);
                        //save
                        appendToFile(filePath,d);
                        break;

                    case "event":
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        }
                        description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /at "));
                        String at = input.substring(input.indexOf("/at ") + 4);
                        Task e = new Event(description, at);
                        // print to CLI
                        taskPrint(tasks, e);
                        // save
                        appendToFile(filePath,e);
                        break;

                    case "delete":
                        System.out.println("Noted. I've removed this task: \n  " + tasks.get(Integer.parseInt(splitInput[1]) - 1));
                        tasks.remove(Integer.parseInt(splitInput[1]) - 1);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
                        // save
                        writeToFile(filePath,tasks);
                        break;

                    case "blah":
                    case "idk":
                        throw new UnknownInputException();

                    default:
                        // other calls causes duke to add the user-input to tasks
                        Task t = new Task(input);
                        tasks.add(t);
                        //print to CLI
                        System.out.println("added: " + input + "\n");
                        //save
                        appendToFile(filePath,t);
                        break;
                }

            //EXCEPTIONS
            }
            catch (UnknownInputException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-( \n");
            }

            catch (StringIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! You must have a ' /at ' for events OR a ' /by ' for deadlines!!\n");
            }

            catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! You must input the right delete index number\n");
            }

            catch (EmptyDescriptionException e) {
                if (splitInput[0] == "event") {
                    System.out.println("OOPS!!! The description of an " + splitInput[0] + " cannot be empty.\n");
                } else {
                    System.out.println("OOPS!!! The description of a " + splitInput[0] + " cannot be empty.\n");
                }
            }

            // input after the try, catch and switch case to obtain the next input statement
            input = in.nextLine();
            input = input.trim();

        }

        System.out.println("Bye good friend! Hope to see you again soon!\n" + logo);
    }


    //OTHER HELPER FUNCTIONS
    /**
     * Method for printing Tasks adding
     * @param tasks
     * @param t
     * @return
     */
    public static void taskPrint(List<Task> tasks, Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task: \n  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
    }

    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        for (int i = 0; i < tasks.size(); i++) {
            textToAdd = textToAdd + tasks.get(i).getSavedString() + System.lineSeparator();
        }
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, Task t) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        String textToAppend = t.getSavedString() + System.lineSeparator();
        fw.write(textToAppend);
        fw.close();
    }

}
