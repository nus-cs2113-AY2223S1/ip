import exceptions.InvalidDeadlineException;
import exceptions.InvalidEventException;
import exceptions.InvalidMarkException;
import exceptions.InvalidTodoException;
import exceptions.InvalidUnmarkException;
import exceptions.UnrecognisedCommandException;
import tasks.Deadline;
import tasks.Task;
import tasks.Event;
import tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    // HELPER FUNCTIONS
    public static void greetUser() {
        String line = "------------------------\n";
        System.out.println(line + "Hello! I'm Juke!\nWhat can I do for you?\n" + line);
    }

    public static File getMakeFile() {
        try {
            File folder = new File("data/");
            File file = new File("data/stored.txt");
            
            if (!folder.exists() && !folder.isDirectory()) {
                // need to create folder as determined by the path string
                folder.mkdirs();
                file.createNewFile();
                return file;
            }
            else if (!file.exists() && folder.isDirectory()) {
                // need to create file as determined by the path string
                file.createNewFile();
                return file;
            }
            return file;

        } catch (Exception e) {
            System.out.println("File i/o error.");
            return null;
        }
    }

    // ArrayLists are passed by reference
    public static void insertPreviousItems(File f, ArrayList<Task> tasks) {
        try {
            Scanner fileScanner = new Scanner(f);
        
            while (fileScanner.hasNext()) {
                String lineInput = fileScanner.nextLine();
                String[] words = lineInput.split("\\|", 4);

                switch (words[0]) {
                case "T":
                    Todo newTodo = new Todo(words[2]);
                    if (words[1].equals("Y")) {
                        newTodo.markDone();
                    }
                    tasks.add(newTodo);
                    break;

                case "E":
                    Event newEvent = new Event(words[2], words[3]);
                    if (words[1].equals("Y")) {
                        newEvent.markDone();
                    }
                    tasks.add(newEvent);
                    break;

                case "D":
                    Deadline newDeadline = new Deadline(words[2], words[3]);
                    if (words[1].equals("Y")) {
                        newDeadline.markDone();
                    }
                    tasks.add(newDeadline);
                    break;

                default:
                    break;
                }
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("error accessing file");
        }    
    }

    public static void updateFile(ArrayList<Task> tasks) { 
        try {
            File f = new File("data/stored.txt");
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();

            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).createFileString() + "\n");
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("File i/o error");
        }
    }

    public static void listTasks(ArrayList<Task> tasks) {
        String line = "\t------------------------\n";
        System.out.println(line);
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            System.out.println("\t"+ j + ". " + tasks.get(i) + "\n");
        }
        System.out.println(line);
    }

    public static void printStatus(Task latest, int total){
        String line = "\t------------------------\n";
        System.out.println(line + "\tGot it. I've added this task:\n\t" + latest);
        System.out.println("\n\tNow you have " + total + " task(s) in the list.\n" + line);
    }

    public static void processUserInput() {
        // CONSTANTS
        final String LIST_TRIGGER = "list";
        final String TODO_TRIGGER = "todo";
        final String EVENT_TRIGGER = "event";
        final String DEADLINE_TRIGGER = "deadline";
        final String EXIT_TRIGGER = "bye";
        final String MARK_TRIGGER = "mark";
        final String UNMARK_TRIGGER = "unmark";

        // VARS
        String userInput = "";
        Scanner userInputScanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        // READ CURR ITEMS FROM FILE
        insertPreviousItems(getMakeFile(), tasks);

        // JUKE LOGIC
        while (!userInput.equals("bye")) {
            // Read user input, splits it into 2 strings- the command and the remaining (if exists)
            userInput = userInputScanner.nextLine();
            String[] words = userInput.split(" ", 2);
            
            // variables for Tasks with timestamps
            int dividerIndex;
            String timestamp;
            String taskDescription;

            switch (words[0]) {
            case LIST_TRIGGER:
                listTasks(tasks);
                break;

            case EXIT_TRIGGER:
                // save latest state to file
                updateFile(tasks);
                break;

            case TODO_TRIGGER:
                try {
                    if (words.length == 1) {
                        throw new InvalidTodoException();
                    } else {
                        Todo latestTodo = new Todo(words[1]);
                        tasks.add(latestTodo);
                        printStatus(latestTodo, tasks.size());
                        break;
                    }
                } catch (InvalidTodoException t) {
                    System.out.println("The description of a todo cannot be empty");
                    break;
                }

            case EVENT_TRIGGER:
                try {
                    dividerIndex = words[1].indexOf("/");

                    if (dividerIndex == -1) {
                        throw new InvalidEventException();
                    }
                    timestamp = words[1].substring(dividerIndex+3);
                    taskDescription = words[1].substring(0, dividerIndex-1);
                    
                    Event latestEvent = new Event(taskDescription, timestamp);
                    tasks.add(latestEvent);
                    printStatus(latestEvent, tasks.size());
                    break;
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("Missing details after 'event' keyword.");
                    break;
                } catch (InvalidEventException e) {
                    System.out.println("Missing /at field. Try again.");
                    break;
                }

            case DEADLINE_TRIGGER:
                try {
                    dividerIndex = words[1].indexOf("/");
                    
                    if (dividerIndex == -1) {
                        throw new InvalidDeadlineException();
                    }
                    timestamp = words[1].substring(dividerIndex+3);
                    taskDescription = words[1].substring(0, dividerIndex-1); 
                    
                    Deadline latestDeadline = new Deadline(taskDescription, timestamp);
                    tasks.add(latestDeadline);
                    printStatus(latestDeadline, tasks.size());
                    break;
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("Missing details after 'deadline' keyword.");
                    break;
                } catch (InvalidDeadlineException d) {
                    System.out.println("Missing /by field. Try again.");
                    break;
                }

            case MARK_TRIGGER:
                // making sure there is valid numerical input after mark/unmark
                try {
                    if (userInput.matches("mark [0-9]+")) {
                        int val = Integer.parseInt(words[1]);
    
                        if (val < 0 || val > tasks.size()) {
                            throw new InvalidMarkException();
                        } else {
                            tasks.get(val - 1).markDone();
                            listTasks(tasks);
                        }
                    } 
                    break;
                } catch (InvalidMarkException m){
                    System.out.println("Can't mark: Out of bounds value entered!");
                    break;
                } 

            case UNMARK_TRIGGER:
                try {
                    // making sure there is valid numerical input after mark/unmark
                    if (userInput.matches("unmark [0-9]+")) {    
                        int val = Integer.parseInt(words[1]);

                        if (val < 0 || val > tasks.size()) {
                            throw new InvalidUnmarkException();
                        } else {
                            tasks.get(val - 1).unmark();
                            listTasks(tasks);
                        }
                    } 
                    break;
                } catch (InvalidUnmarkException u) {
                    System.out.println("Can't unmark: Out-of-bounds value entered!");
                    break;
                }

            default:
                // unrecognised command
                try {
                    throw new UnrecognisedCommandException();
                } catch (UnrecognisedCommandException u) {
                    System.out.println("Unrecognised command!");
                    break;
                }
            }
        }

        userInputScanner.close();
    }

    public static void printExitGreeting() {
        System.out.println("\nSee you again!");
    }

    // MAIN FUNCTION
    public static void main(String[] args) {
        greetUser();
        processUserInput();
        printExitGreeting();
    }
}
